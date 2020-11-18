package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.ui.detail

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.provider.AlarmClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.RecipeDatabase
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.model.RecipeWithSteps
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.repository.RecipeRepository
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.databinding.FragmentDetailRecipeBinding
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.receiver.TimerExpiredReceiver
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.utils.PrefUtils
import kotlinx.android.synthetic.main.fragment_detail_recipe.*
import java.util.*


class DetailRecipeFragment : Fragment() {

    private lateinit var factory: DetailRecipeViewModelFactory
    private lateinit var viewModel: DetailRecipeViewModel
    private lateinit var binding: FragmentDetailRecipeBinding

    enum class TimerState {
        Stopped, Paused, Running
    }

    private lateinit var timer: CountDownTimer
    private var timerLengthSeconds: Long = 0L
    private var timerState = TimerState.Stopped
    private var secondsRemaining = 0L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val dao = RecipeDatabase.getInstance(requireActivity()).recipeDao
        val repository = RecipeRepository(dao)

        val recipeWithStep: RecipeWithSteps? = activity?.intent?.getSerializableExtra("recipeWithSteps") as? RecipeWithSteps

        factory = DetailRecipeViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(DetailRecipeViewModel::class.java)

        binding.lifecycleOwner = this
        binding.detailRecipeViewModel = viewModel

        if (recipeWithStep != null) {
            viewModel.setRecipeWithSteps(recipeWithStep)
        }

        initTimerActions()
    }

    override fun onResume() {
        super.onResume()

        initTimer()
        activity?.let { removeAlarm(it) }
        //hide notification
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onPause() {
        super.onPause()
        if (timerState == TimerState.Running) {
            timer.cancel()
            val wakeUpTime = activity?.let { setAlarm(it, nowSeconds, secondsRemaining) }
            // todo: show notification
        } else if (timerState == TimerState.Paused){
            //show notification
        }

        activity?.let { PrefUtils.setPreviousTimerLengthSeconds(timerLengthSeconds, it) }
        activity?.let { PrefUtils.setSecondsRemaining(secondsRemaining, it) }
        activity?.let { PrefUtils.setTimerState(timerState, it) }
    }

    private fun initTimerActions() {
        btn_play_recipe_timer.setOnClickListener {
            startTimer()
            timerState = TimerState.Running
            updateButtons()
        }

        btn_pause_recipe_timer.setOnClickListener {
            timer.cancel()
            timerState = TimerState.Paused
            updateButtons()
        }

        btn_stop_recipe_timer.setOnClickListener {
            timer.cancel()
            onTimerFinished()
        }
    }

    private fun initTimer() {
        timerState = activity?.let { PrefUtils.getTimerState(it) }!!

        if (timerState == TimerState.Stopped) {
            setNewTimerLength()
        } else {
            setPreviousTimerLength()
        }

        secondsRemaining = if (timerState == TimerState.Running || timerState == TimerState.Paused)
            PrefUtils.getSecondsRemaining(requireActivity())
        else
            timerLengthSeconds

        val alarmSetTime = PrefUtils.getAlarmSetTime(requireActivity())

        if (alarmSetTime > 0) secondsRemaining -= nowSeconds - alarmSetTime

        if (secondsRemaining <= 0) {
            onTimerFinished()
        } else if (timerState == TimerState.Running) {
            startTimer()
        }

        updateButtons()
        updateCountDownUI()
    }

    private fun onTimerFinished() {
        timerState = TimerState.Stopped

        setNewTimerLength()

//        progress

        activity?.let { PrefUtils.setSecondsRemaining(timerLengthSeconds, it) }
        secondsRemaining = timerLengthSeconds

        updateButtons()
        updateCountDownUI()
    }

    private fun startTimer() {
        timerState = TimerState.Running

        timer = object : CountDownTimer(secondsRemaining * 1000, 1000) {
            override fun onFinish() = onTimerFinished()
            override fun onTick(millisUntilFinished: Long) {
                secondsRemaining = millisUntilFinished / 1000
                updateCountDownUI()
            }
        }.start()
    }

    private fun setNewTimerLength() {
       val lengthInMinutes = activity?.let { PrefUtils.getTimerLength(it) }
        if (lengthInMinutes != null) {
            timerLengthSeconds = (lengthInMinutes * 60L)
//            timerLengthSeconds = (viewModel.preparationTimeMinutes.value?.toLong() ?: 0) * 60 + (viewModel.preparationTimeSeconds.value?.toLong()
//                ?: 0)
        }
    }

    private fun setPreviousTimerLength() {
        timerLengthSeconds = activity?.let { PrefUtils.getPreviousTimerLengthSeconds(it) }!!
//        timerLengthSeconds = (viewModel.preparationTimeMinutes.value?.toLong() ?: 0) * 60 + (viewModel.preparationTimeSeconds.value?.toLong()
//                ?: 0)
    }

    private fun updateCountDownUI() {
        val minutesUntilFinished = secondsRemaining / 60
        val secondsUntilFinished = secondsRemaining - minutesUntilFinished * 60
        val secondsStr = secondsUntilFinished.toString()
        viewModel.preparationTimeTextView.value = "$minutesUntilFinished:${if (secondsStr.length == 2) secondsStr else "0$secondsStr"}"
    }

    private fun updateButtons() {
        when (timerState) {
            TimerState.Running ->{
                btn_play_recipe_timer.isEnabled = false
                btn_pause_recipe_timer.isEnabled = true
                btn_stop_recipe_timer.isEnabled = true
            }
            TimerState.Stopped -> {
                btn_play_recipe_timer.isEnabled = true
                btn_pause_recipe_timer.isEnabled = false
                btn_stop_recipe_timer.isEnabled = false
            }
            TimerState.Paused -> {
                btn_play_recipe_timer.isEnabled = true
                btn_pause_recipe_timer.isEnabled = false
                btn_stop_recipe_timer.isEnabled = true
            }
        }
    }

    companion object {
        @RequiresApi(Build.VERSION_CODES.KITKAT)
        fun setAlarm(context: Context, nowSeconds: Long, secondsRemaining: Long): Long {
            val wakeUpTime = (nowSeconds + secondsRemaining) * 1000
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context, TimerExpiredReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, wakeUpTime, pendingIntent)
            PrefUtils.setAlarmSetTime(nowSeconds, context)

            return wakeUpTime
        }

        fun removeAlarm(context: Context) {
            val intent = Intent(context, TimerExpiredReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.cancel(pendingIntent)
            PrefUtils.setAlarmSetTime(0, context)
        }

        val nowSeconds: Long
            get() = Calendar.getInstance().timeInMillis / 1000
    }

}