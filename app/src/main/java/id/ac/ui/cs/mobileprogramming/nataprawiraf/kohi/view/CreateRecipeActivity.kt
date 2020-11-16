package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.view

import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.R
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.databinding.ActivityCreateRecipeBinding
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.RecipeDatabase
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.repository.RecipeRepository
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.viewmodel.CreateRecipeViewModel
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.viewmodel.CreateRecipeViewModelFactory

const val MIN_WEIGHT_IN_GRAM = 0
const val MAX_WEIGHT_IN_GRAM = 100
const val STEP_IN_GRAM = 1

const val MIN_DEGREE_IN_CELSIUS = 0
const val MAX_DEGREE_IN_CELSIUS = 100
const val STEP_IN_CELSIUS = 1

class CreateRecipeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateRecipeBinding
    private lateinit var createRecipeViewModel: CreateRecipeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_recipe)

        val dao = RecipeDatabase.getInstance(application).recipeDao
        val repository = RecipeRepository(dao)
        val factory = CreateRecipeViewModelFactory(repository)

        createRecipeViewModel = ViewModelProvider(this, factory).get(CreateRecipeViewModel::class.java)
        binding.createRecipeViewModel = createRecipeViewModel
        binding.lifecycleOwner = this

        // Coffee weight field
        val coffeeSeekBar = findViewById<SeekBar>(R.id.sb_create_recipeCoffee_input)
        val coffeeSeekBarTv = findViewById<TextView>(R.id.tv_create_recipeCoffee_input)
        coffeeSeekBar!!.max = (MAX_WEIGHT_IN_GRAM - MIN_WEIGHT_IN_GRAM) / STEP_IN_GRAM
        coffeeSeekBar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(coffeeSeekBar: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                val progressCustom = MIN_WEIGHT_IN_GRAM + ( progress * STEP_IN_GRAM )
                coffeeSeekBarTv.setText("$progressCustom gram")
            }
            override fun onStartTrackingTouch(coffeeSeekBar: SeekBar) {}
            override fun onStopTrackingTouch(coffeeSeekBar: SeekBar) {}
        })

        // Water weight field
        val waterSeekBar = findViewById<SeekBar>(R.id.sb_create_recipeWater_input)
        val waterSeekBarTv = findViewById<TextView>(R.id.tv_create_recipeWater_input)
        waterSeekBar!!.max = (MAX_WEIGHT_IN_GRAM - MIN_WEIGHT_IN_GRAM) / STEP_IN_GRAM
        waterSeekBar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(waterSeekBar: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                val progressCustom = MIN_WEIGHT_IN_GRAM + ( progress * STEP_IN_GRAM )
                waterSeekBarTv.setText("$progressCustom gram")
            }
            override fun onStartTrackingTouch(waterSeekBar: SeekBar) {}
            override fun onStopTrackingTouch(waterSeekBar: SeekBar) {}
        })

        // Water temperature weight field
        val temperatureSeekBar = findViewById<SeekBar>(R.id.sb_create_recipeTemperature_input)
        val temperatureSeekBarTv = findViewById<TextView>(R.id.tv_create_recipeTemperature_input)
        temperatureSeekBar!!.max = (MAX_DEGREE_IN_CELSIUS - MIN_DEGREE_IN_CELSIUS) / STEP_IN_CELSIUS
        temperatureSeekBar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(temperatureSeekBar: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                val progressCustom = MIN_DEGREE_IN_CELSIUS + ( progress * STEP_IN_CELSIUS )
                temperatureSeekBarTv.setText("$progressCustom °C")
            }
            override fun onStartTrackingTouch(temperatureSeekBar: SeekBar) {}
            override fun onStopTrackingTouch(temperatureSeekBar: SeekBar) {}
        })
    }

}