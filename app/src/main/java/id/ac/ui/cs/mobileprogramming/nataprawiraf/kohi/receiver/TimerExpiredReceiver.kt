package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.ui.detail.DetailRecipeFragment
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.utils.PrefUtils

class TimerExpiredReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        PrefUtils.setTimerState(DetailRecipeFragment.TimerState.Stopped, context)
        PrefUtils.setAlarmSetTime(0, context)
    }
}
