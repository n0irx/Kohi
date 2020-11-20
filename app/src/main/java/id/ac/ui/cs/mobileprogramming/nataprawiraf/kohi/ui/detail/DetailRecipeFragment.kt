package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.ui.detail

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.R
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.KohiDatabase
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.model.RecipeWithSteps
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.repository.RecipeRepository
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.databinding.FragmentDetailRecipeBinding
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.receiver.TimerExpiredReceiver
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.service.TimerExpiredNotifService
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.util.PrefUtil
import kotlinx.android.synthetic.main.fragment_detail_recipe.*
import java.util.*


class DetailRecipeFragment : Fragment() {

    private lateinit var factory: DetailRecipeViewModelFactory
    private lateinit var viewModel: DetailRecipeViewModel
    private lateinit var binding: FragmentDetailRecipeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val dao = KohiDatabase.getInstance(requireActivity()).recipeDao
        val repository = RecipeRepository(dao)

        val recipeWithStep: RecipeWithSteps? = activity?.intent?.getSerializableExtra("recipeWithSteps") as? RecipeWithSteps

        factory = DetailRecipeViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(DetailRecipeViewModel::class.java)

        binding.lifecycleOwner = this
        binding.detailRecipeViewModel = viewModel

        if (recipeWithStep != null) viewModel.setRecipeWithSteps(recipeWithStep)

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

        activity?.let { PrefUtil.setPreviousTimerLengthSeconds(timerLengthSeconds, it) }
        activity?.let { PrefUtil.setSecondsRemaining(secondsRemaining, it) }
        activity?.let { PrefUtil.setTimerState(timerState, it) }
    }

    // Timer Section
    enum class TimerState {
        Stopped, Paused, Running
    }

    private lateinit var timer: CountDownTimer
    private var timerLengthSeconds: Long = 0L
    private var timerState = TimerState.Stopped
    private var secondsRemaining = 0L

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
            if (this::timer.isInitialized) {
                timer.cancel()
                onTimerFinished()
            } else {
                setNewTimerLength()
            }
        }
    }

    private fun initTimer() {
        timerState = activity?.let { PrefUtil.getTimerState(it) }!!

        if (timerState == TimerState.Stopped || timerState == TimerState.Paused) {
            setNewTimerLength()
        } else {
            setPreviousTimerLength()
        }

        secondsRemaining = if (timerState == TimerState.Running || timerState == TimerState.Paused)
            PrefUtil.getSecondsRemaining(requireActivity())
        else
            timerLengthSeconds

        val alarmSetTime = PrefUtil.getAlarmSetTime(requireActivity())

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

        activity?.let { PrefUtil.setSecondsRemaining(timerLengthSeconds, it) }
        secondsRemaining = timerLengthSeconds

        updateButtons()
        updateCountDownUI()

        val intent = Intent(context, TimerExpiredNotifService::class.java)
        activity?.startService(intent)
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
       val lengthInSeconds: Long? = activity?.let { PrefUtil.getTimerLength(it, viewModel.getTotalPreparationTimeInSeconds()) }
        if (lengthInSeconds != null) {
            timerLengthSeconds = lengthInSeconds
        }
    }

    private fun setPreviousTimerLength() {
        val lengthInSeconds: Long? = activity?.let { PrefUtil.getTimerLength(it, viewModel.getTotalPreparationTimeInSeconds()) }
        if (lengthInSeconds != null) {
            timerLengthSeconds = lengthInSeconds
        }
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

        val nowSeconds: Long
            get() = Calendar.getInstance().timeInMillis / 1000

        @RequiresApi(Build.VERSION_CODES.KITKAT)
        fun setAlarm(context: Context, nowSeconds: Long, secondsRemaining: Long): Long {
            val wakeUpTime = (nowSeconds + secondsRemaining) * 1000
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context, TimerExpiredReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, wakeUpTime, pendingIntent)
            PrefUtil.setAlarmSetTime(nowSeconds, context)
            return wakeUpTime
        }

        fun removeAlarm(context: Context) {
            val intent = Intent(context, TimerExpiredReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.cancel(pendingIntent)
            PrefUtil.setAlarmSetTime(0, context)
        }

    } // end of companion object

} // end of Detail Recipe Fragment class