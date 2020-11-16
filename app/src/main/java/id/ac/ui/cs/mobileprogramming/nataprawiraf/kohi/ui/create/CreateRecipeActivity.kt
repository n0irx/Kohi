package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.ui

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


class CreateRecipeActivity : AppCompatActivity() {
    private val minWeightInGram = 0
    private val maxWeightInGram = 100
    private val stepInGram = 1
    private val minDegreeInCelsius = 0
    private val maxDegreeInCelsius = 100
    private val stepInCelsius = 1
    
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
        coffeeSeekBar!!.max = (maxWeightInGram - minWeightInGram) / stepInGram
        coffeeSeekBar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(coffeeSeekBar: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                val progressCustom = minWeightInGram + ( progress * stepInGram )
                coffeeSeekBarTv.setText("$progressCustom gram")
            }
            override fun onStartTrackingTouch(coffeeSeekBar: SeekBar) {}
            override fun onStopTrackingTouch(coffeeSeekBar: SeekBar) {}
        })

        // Water weight field
        val waterSeekBar = findViewById<SeekBar>(R.id.sb_create_recipeWater_input)
        val waterSeekBarTv = findViewById<TextView>(R.id.tv_create_recipeWater_input)
        waterSeekBar!!.max = (maxWeightInGram - minWeightInGram) / stepInGram
        waterSeekBar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(waterSeekBar: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                val progressCustom = minWeightInGram + ( progress * stepInGram )
                waterSeekBarTv.setText("$progressCustom gram")
            }
            override fun onStartTrackingTouch(waterSeekBar: SeekBar) {}
            override fun onStopTrackingTouch(waterSeekBar: SeekBar) {}
        })

        // Water temperature weight field
        val temperatureSeekBar = findViewById<SeekBar>(R.id.sb_create_recipeTemperature_input)
        val temperatureSeekBarTv = findViewById<TextView>(R.id.tv_create_recipeTemperature_input)
        temperatureSeekBar!!.max = (maxDegreeInCelsius - minDegreeInCelsius) / stepInCelsius
        temperatureSeekBar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(temperatureSeekBar: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                val progressCustom = minDegreeInCelsius + ( progress * stepInCelsius )
                temperatureSeekBarTv.setText("$progressCustom °C")
            }
            override fun onStartTrackingTouch(temperatureSeekBar: SeekBar) {}
            override fun onStopTrackingTouch(temperatureSeekBar: SeekBar) {}
        })
    }

}