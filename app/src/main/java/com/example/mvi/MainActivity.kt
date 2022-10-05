package com.example.mvi

import android.Manifest.permission.*
import android.app.ActivityManager
import android.app.AlarmManager
import android.app.AlarmManager.RTC_WAKEUP
import android.app.PendingIntent
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.work.*
import com.example.mvi.broadcastReceiver.AlarmBroadCastReceiver
import com.example.mvi.searchFeature.SearchManager
import com.example.mvi.searchFeature.SearchViewModel
import com.example.mvi.databinding.ActivityMainBinding
import com.example.mvi.mvi.*
import com.example.mvi.services.ForegroundNotificationsReader
import com.example.mvi.services.ServiceJob
import com.example.mvi.workmanager.LocationWorkManager
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MVIInterface<MainState, MainSideEffect> {

    lateinit var binding: ActivityMainBinding
    private lateinit var exoPlayer: ExoPlayer
    private lateinit var mainViewModel: MainViewModel
    lateinit var name: String
    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { isGranted ->
            //     createWorkRequest()
            //   setupJobService()

        }

    val viewModel: MainViewModel by viewModels()
    lateinit var searchViewModel: SearchViewModel

    private fun createWorkRequest() {
        val workRequest =
            PeriodicWorkRequest.Builder(LocationWorkManager::class.java, 15, TimeUnit.MINUTES)
                .build()
        setupWorkManager(workRequest)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        binding.playButton.setOnClickListener {
            mainViewModel.handleUserActions(MainIntent.PlayVideo)
        }
        setupSearchView()
        //mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // viewModel.observe(this, ::handleState, ::handleSideEffect)
        //  requestPermission()
        // requestNotificationListenerPermission()
        // setupNotificationListenerService()
    }

    private fun MainActivity.setupSearchView() {
        SearchManager(
            binding.root.context as? LifecycleOwner,
            binding.searchRecyclerView,
            binding.searchView, searchViewModel
        )
    }

    private fun requestNotificationListenerPermission() {
        if (!NotificationManagerCompat.getEnabledListenerPackages(this)
                .contains(packageName)
        ) {
            startActivity(Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"))
        }
    }

    private fun setupNotificationListenerService() {

        if (!foregroundServiceRunning()) {
            val foreGroundNotificationsReader = ForegroundNotificationsReader()
            val intent = Intent(this@MainActivity, foreGroundNotificationsReader::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(intent)
            }
        }
    }

    private fun requestPermission() {
        requestPermission.launch(
            arrayOf(
//                WRITE_EXTERNAL_STORAGE,
//                ACCESS_COARSE_LOCATION,
//                ACCESS_FINE_LOCATION,
                SEND_SMS,
                BIND_NOTIFICATION_LISTENER_SERVICE
            )
        )
    }

    private fun setupWorkManager(workRequest: PeriodicWorkRequest) {
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "download",
            ExistingPeriodicWorkPolicy.KEEP, workRequest
        )
    }

    private fun setupJobService() {
        val comp = ComponentName(this, ServiceJob::class.java)
        val jobInfo = JobInfo.Builder(12, comp).setPeriodic(900000)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY).build()

        val jobScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
        jobScheduler.schedule(jobInfo)
    }


    override fun onStart() {
        initializePlayer()
        super.onStart()
    }

    private fun initializePlayer() {
        exoPlayer = ExoPlayer.Builder(this).build()
        binding.playerView.player = exoPlayer
        preparePlayer("https://html5demos.com/assets/dizzy.mp4", "dash")
    }

    private fun buildMediaSource(uri: Uri, type: String): MediaSource {
        return ProgressiveMediaSource.Factory(DefaultHttpDataSource.Factory())
            .createMediaSource(MediaItem.fromUri(uri))
    }

    private fun preparePlayer(videoUrl: String, type: String) {
        val uri = Uri.parse(videoUrl)
        val mediaSource = buildMediaSource(uri, type)
        exoPlayer.setMediaSource(mediaSource)
        exoPlayer.prepare()

    }

    private fun setupAlarmManager() {
        val intent = Intent(this, AlarmBroadCastReceiver::class.java)
        val pendingIntent =
            PendingIntent.getBroadcast(this, 422435, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setRepeating(
            RTC_WAKEUP,
            System.currentTimeMillis() + 10000,
            40000,
            pendingIntent
        )
        Toast.makeText(this, "Alarm Set$pendingIntent", Toast.LENGTH_LONG).show()

    }

    private fun foregroundServiceRunning(): Boolean {
        val activityManager = getSystemService(ACTIVITY_SERVICE) as ActivityManager
        for (service in activityManager.getRunningServices(Int.MAX_VALUE)) {
            if (ForegroundNotificationsReader::class.java.name == service.service.className) {
                return true
            }
        }
        return false
    }

    override fun handleState(state: MainState) {
        lifecycleScope.launch {
            mainViewModel.stateFlow().collect {
                when (it) {
                    MainState.Initial -> TODO()
                    MainState.Loading -> TODO()
                    MainState.Paused -> TODO()
                    MainState.Playing -> TODO()
                    MainState.VideoLoaded -> TODO()
                }
            }
        }
    }

    override fun handleSideEffect(effect: MainSideEffect) {
        lifecycleScope.launch {
            mainViewModel.sideFlow().collect { sideEffect ->
                when (sideEffect) {
                    is MainSideEffect.Toast -> {
                        Toast.makeText(
                            this@MainActivity,
                            sideEffect.text,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

}