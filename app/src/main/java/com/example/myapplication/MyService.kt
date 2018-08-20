package com.example.myapplication

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.*
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat

class MyService : Service() {

    private var serviceLooper: Looper? = null
    private var serviceHandler: ServiceHandler? = null

    inner class LocalBinder {
        fun getService(): MyService = this@MyService
    }

    // Handler that receives messages from the thread
    private inner class ServiceHandler(looper: Looper) : Handler(looper) {

        override fun handleMessage(msg: Message) {
            // Normally we would do some work here, like download a file.
            // For our sample, we just sleep for 5 seconds.
            try {
                Thread.sleep(5000)
            } catch (e: InterruptedException) {
                // Restore interrupt status.
                Thread.currentThread().interrupt()
            }

            // Stop the service using the startId, so that we don't stop
            // the service in the middle of handling another job
            stopSelf(msg.arg1)
        }
    }

    override fun onCreate() {
        Log.d("SERVICE", "on create")
        startNotification()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.d("SERVICE", "on start command")
        Handler(handlerThread.looper).post {
            repeat(1000) {
                Log.d("SERVICE", "service running")
                Thread.sleep(1000)
            }
        }

        return START_STICKY
    }

    var handlerThread = HandlerThread("bka").apply {
        start()
    }

    override fun onBind(intent: Intent): IBinder? {
        Log.d("SERVICE", "on bind")
        Handler(handlerThread.looper).post {
            repeat(1000) {
                Log.d("SERVICE", "service running")
                Thread.sleep(1000)
            }
        }
        return null
    }

    private fun startNotification() {
        val channel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(
                "bla",
                "My Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(channel)
        val notification: Notification = NotificationCompat.Builder(this, "bla")
            .setContentTitle("A service is running in the background")
            .setContentText("Generating random number").build()
        startForeground(1, notification)
    }

    override fun onDestroy() {
        Log.d("SERVICE", "on destroy")

        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show()
    }
}