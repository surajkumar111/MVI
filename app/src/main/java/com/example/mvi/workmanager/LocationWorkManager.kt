package com.example.mvi.workmanager

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.io.File


class LocationWorkManager(private val context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {
    override fun doWork(): Result {
        Log.i("WorkManager", "doWork")
        download()
        return Result.success()
    }


    private fun download() {
        try {
            Log.i("WorkManager", "downloading")
            val downloadManager =
                context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val request =
                DownloadManager.Request(Uri.parse("https://sp-ao.shortpixel.ai/client/to_auto,q_glossy,ret_img,w_1280/https://www.simplifiedcoding.net/wp-content/uploads/2018/11/android-workmanager-tutorial.jpg"))
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI)
                .setMimeType("images/jpeg")
                .setTitle("myimage")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationInExternalPublicDir(
                    Environment.DIRECTORY_PICTURES,
                    File.separator + "myimage" + ".jpeg"
                )
            downloadManager.enqueue(request)
        } catch (e: Exception) {
            Log.i("WorkManager", e.message.toString())
        }
    }
}