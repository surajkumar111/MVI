package com.example.mvi.broadcastReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.provider.Settings
import android.widget.Toast

class AlarmBroadCastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val mediaPlayer = MediaPlayer.create(context, Settings.System.DEFAULT_ALARM_ALERT_URI)
        mediaPlayer.start()
        Toast.makeText(context, "Receiver Triggered", Toast.LENGTH_LONG).show()
    }
}