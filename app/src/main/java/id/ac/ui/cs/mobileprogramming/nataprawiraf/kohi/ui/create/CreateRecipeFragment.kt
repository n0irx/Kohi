package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.ui.create

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.KohiDatabase
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.repository.RecipeRepository
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.databinding.FragmentCreateRecipeBinding
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.ui.list.ListRecipeActivity
import kotlinx.android.synthetic.main.fragment_create_recipe.*


class CreateRecipeFragment : Fragment() {

    private lateinit var factory: CreateRecipeViewModelFactory
    private lateinit var viewModel: CreateRecipeViewModel
    private lateinit var binding: FragmentCreateRecipeBinding

    private val minWeightInGram = 0
    private val maxWeightInGram = 100
    private val stepInGram = 1
    private val minDegreeInCelsius = 0
    private val maxDegreeInCelsius = 100
    private val stepInCelsius = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val dao = KohiDatabase.getInstance(requireActivity()).recipeDao
        val repository = RecipeRepository(dao)

        factory = CreateRecipeViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(CreateRecipeViewModel::class.java)

        binding.lifecycleOwner = this
        binding.createRecipeViewModel = viewModel

        // Coffee weight field
        sb_create_coffee_amount_input!!.max = (maxWeightInGram - minWeightInGram) / stepInGram
        sb_create_coffee_amount_input.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                coffeeSeekBar: SeekBar,
                progress: Int, fromUser: Boolean
            ) {
                val progressCustom = minWeightInGram + (progress * stepInGram)
                et_create_coffee_amount_input.setText("$progressCustom")
            }

            override fun onStartTrackingTouch(coffeeSeekBar: SeekBar) {}
            override fun onStopTrackingTouch(coffeeSeekBar: SeekBar) {}
        })

        // Water weight field
        sb_create_recipe_water_input!!.max = (maxWeightInGram - minWeightInGram) / stepInGram
        sb_create_recipe_water_input.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                waterSeekBar: SeekBar,
                progress: Int, fromUser: Boolean
            ) {
                val progressCustom = minWeightInGram + (progress * stepInGram)
                et_create_recipe_water_input.setText("$progressCustom")
            }

            override fun onStartTrackingTouch(waterSeekBar: SeekBar) {}
            override fun onStopTrackingTouch(waterSeekBar: SeekBar) {}
        })

        // Water temperature weight field
        sb_create_recipe_temperature_input!!.max = (maxDegreeInCelsius - minDegreeInCelsius) / stepInCelsius
        sb_create_recipe_temperature_input.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                temperatureSeekBar: SeekBar,
                progress: Int, fromUser: Boolean
            ) {
                val progressCustom = minDegreeInCelsius + (progress * stepInCelsius)
                et_create_recipe_temperature_input.setText("$progressCustom")
            }

            override fun onStartTrackingTouch(temperatureSeekBar: SeekBar) {}
            override fun onStopTrackingTouch(temperatureSeekBar: SeekBar) {}
        })

        recipe_image.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(gallery, 100)
        }

        btn_create_submit_recipe.setOnClickListener {
            viewModel.save()
            val intent = Intent(activity, ListRecipeActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100) {
            if (data != null) {
                val uri = data.data
                viewModel.inputImageSource.value = uri.toString()
            }
        }
    }
}