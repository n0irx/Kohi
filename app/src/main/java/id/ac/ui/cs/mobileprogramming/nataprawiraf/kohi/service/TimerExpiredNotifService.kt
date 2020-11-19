package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.R

class TimerExpiredNotifService : Service() {

    companion object {
        private const val CHANNEL_ID = "kohi_brew_finished"
        private const val NOTIFICATION_ID = 200
    }

    private lateinit var message: String
    private lateinit var context: Context

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        message = getString(R.string.notification_label)
        context = applicationContext
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotificationChannel()
        sendNotification()
        Log.i("TIMER_TAG", "SENDIN NOTIFICATION")
        return super.onStartCommand(intent, flags, startId)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Kohi Timer Notification"
            val descriptionText = "Kohi Timer Expired Notification"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =  getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification() {
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("Kohi Timer")
            .setContentText("Kohi Timer Awaw")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(context)) {
           notify(NOTIFICATION_ID, builder.build())
        }
    }
}
