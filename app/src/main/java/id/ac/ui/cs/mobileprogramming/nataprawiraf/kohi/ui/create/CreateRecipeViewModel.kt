package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.ui.create

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.model.Recipe
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.model.RecipeWithSteps
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.model.Step
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.repository.RecipeRepository
import kotlinx.coroutines.launch


class CreateRecipeViewModel(private val repository: RecipeRepository): ViewModel() {

    private fun insertRecipeWithSteps(recipeWithSteps: RecipeWithSteps) = viewModelScope.launch { repository.insertRecipeWithSteps(recipeWithSteps) }

    var inputName = MutableLiveData("")
    var inputCoffeeAmount = MutableLiveData("0")
    var inputCoffeeBlend = MutableLiveData("")
    var inputWaterAmount = MutableLiveData("0")
    var inputGrindSize = MutableLiveData("")
    var inputWaterTemperature = MutableLiveData("0")
    var inputBrewMethod = MutableLiveData("")
    var inputImageSource = MutableLiveData("")
    var inputRecipeNote = MutableLiveData("")
    var inputPreparationTimeMinutes = MutableLiveData("0")
    var inputPreparationTimeSeconds = MutableLiveData("0")

    private fun resetForm() {
        inputName = MutableLiveData("")
        inputCoffeeAmount = MutableLiveData("0")
        inputCoffeeBlend = MutableLiveData("")
        inputWaterAmount = MutableLiveData("0")
        inputGrindSize = MutableLiveData("")
        inputWaterTemperature = MutableLiveData("0")
        inputBrewMethod = MutableLiveData("")
        inputImageSource = MutableLiveData("")
        inputRecipeNote = MutableLiveData("")
        inputPreparationTimeMinutes = MutableLiveData("0")
        inputPreparationTimeSeconds = MutableLiveData("0")
    }

    fun save() {
        val recipeName = inputName.value!!
        val recipeCoffeeBlend = inputCoffeeBlend.value!!
        val recipeCoffeeAmount = inputCoffeeAmount.value!!.toInt()
        val recipeGrindSize = inputGrindSize.value!!
        val recipeBrewMethod = inputBrewMethod.value!!
        val recipeWater = inputWaterAmount.value!!.toInt()
        val recipeWaterTemperature = inputWaterTemperature.value!!.toInt()
        val recipeImageSrc = inputImageSource.value!!
        val recipeNote = inputRecipeNote.value!!
        val preparationMinutes : Int = inputPreparationTimeMinutes.value!!.toInt() * ONE_MINUTES_IN_SECONDS
        val preparationSeconds : Int = inputPreparationTimeSeconds.value!!.toInt()
        val totalPreparationTimeInSeconds: Int = preparationMinutes + preparationSeconds

        val recipe = Recipe(0, recipeName, recipeCoffeeBlend, recipeCoffeeAmount,
            recipeGrindSize, recipeBrewMethod, recipeWater, recipeWaterTemperature,
            recipeImageSrc, recipeNote
        )

        val step = Step(0, "Brew", 1, totalPreparationTimeInSeconds)

        insertRecipeWithSteps(RecipeWithSteps(recipe, arrayListOf(step)))

        resetForm()
    }

    companion object {
        const val ONE_MINUTES_IN_SECONDS = 60
    }

}