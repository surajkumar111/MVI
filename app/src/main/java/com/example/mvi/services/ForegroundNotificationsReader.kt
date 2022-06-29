package com.example.mvi.services


import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.telephony.SmsManager
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi


class ForegroundNotificationsReader : NotificationListenerService() {

    private val TAG = "ReadNotifications"

    override fun onCreate() {
        super.onCreate()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val CHANNELID = "Foreground Service ID";
        val channel = NotificationChannel(
            CHANNELID,
            CHANNELID,
            NotificationManager.IMPORTANCE_LOW
        )

        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        val notification = Notification.Builder(this, CHANNELID)
            .setContentText("Service is running")
            .setContentTitle("Service enabled")
            .setSmallIcon(com.example.mvi.R.drawable.ic_launcher_foreground)
        /**** as of Android 9 set small Icon is req otherwise text will not set *****/
         startForeground(10001, notification.build())
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)
        Log.i(TAG, "********** onNotificationPosted");
        Log.i(
            TAG,
            "ID :" + sbn?.id + " \t " + sbn?.notification?.tickerText + " \t " + sbn?.packageName
        )
        for (key in sbn?.notification?.extras?.keySet() ?: emptySet()) {
            Log.i(TAG, key + "=" + sbn!!.notification.extras[key].toString())
        }

        if (sbn?.notification?.extras?.get("android.text")?.equals("location") == true) {
            sendSMS("8578080554", "this is a test message")
        }
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        super.onNotificationRemoved(sbn)
        Log.i(TAG, "********** onNotificationRemoved");
        Log.i(
            TAG,
            "ID :" + sbn?.id + " \t " + sbn?.notification?.tickerText + " \t " + sbn?.packageName
        )
    }

     fun sendSMS(phoneNo: String?, msg: String?) {
        try {
            val smsManager: SmsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(phoneNo, null, msg, null, null)
            Toast.makeText(
                applicationContext, "Message Sent",
                Toast.LENGTH_LONG
            ).show()
        } catch (ex: Exception) {
            Toast.makeText(
                applicationContext, ex.message.toString(),
                Toast.LENGTH_LONG
            ).show()
            ex.printStackTrace()
        }
    }
}