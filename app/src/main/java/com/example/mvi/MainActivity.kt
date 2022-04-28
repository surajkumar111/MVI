package com.example.mvi

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mvi.databinding.ActivityMainBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.extractor.ExtractorsFactory
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var exoPlayer: ExoPlayer
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.playButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                mainViewModel.mainIntent.send(MainIntent.PlayVideo)
            }
        }
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.setupIntentObserver()
        setupObserveState()
    }

    private fun setupObserveState() {
        CoroutineScope(Dispatchers.IO).launch {
            mainViewModel.mainState.collect {
                when (it) {
                    MainState.Initial -> Unit
                    MainState.Loading -> Unit
                    MainState.Paused -> exoPlayer.pause()
                    MainState.Playing -> withContext(Dispatchers.Main) { exoPlayer.play() }
                    MainState.VideoLoaded -> Unit
                }
            }
        }

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

}