package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.service.TimerExpiredNotifService
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.ui.detail.DetailRecipeFragment
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.util.PrefUtil

class TimerExpiredReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        PrefUtil.setTimerState(DetailRecipeFragment.TimerState.Stopped, context)
        PrefUtil.setAlarmSetTime(0, context)

        Log.i("BROADCAST", "RECEIVED")

        val toServiceIntent = Intent(context, TimerExpiredNotifService::class.java)
        toServiceIntent.putExtra("kohiMessage", intent.getStringExtra("kohiMessage"))

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(toServiceIntent)
        } else {
            context.startService(toServiceIntent)
        }
    }
}
