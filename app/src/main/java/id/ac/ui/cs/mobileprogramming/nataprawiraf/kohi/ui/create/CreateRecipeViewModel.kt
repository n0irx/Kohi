package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.ui.create

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.model.Recipe
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.model.RecipeWithSteps
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.model.Step
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.model.TastingNote
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.repository.RecipeRepository
import kotlinx.coroutines.launch


class CreateRecipeViewModel(private val repository: RecipeRepository): ViewModel() {

    init {
        System.loadLibrary("native-lib")
    }

    external fun timeSeconds(a: Int): Int

    private fun insertRecipeWithSteps(recipeWithSteps: RecipeWithSteps, notes: List<TastingNote>) = viewModelScope.launch { repository.insertRecipeWithSteps(recipeWithSteps, notes) }

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
        val recipeName = inputName.value ?: "-"
        val recipeCoffeeBlend = inputCoffeeBlend.value ?: "-"
        val recipeCoffeeAmount = inputCoffeeAmount.value?.toIntOrNull() ?: 0
        val recipeGrindSize = inputGrindSize.value ?: "-"
        val recipeBrewMethod = inputBrewMethod.value ?: "-"
        val recipeWater = inputWaterAmount.value?.toIntOrNull() ?: 0
        val recipeWaterTemperature = inputWaterTemperature.value?.toIntOrNull() ?: 0
        val recipeImageSrc = inputImageSource.value ?: ""
        val recipeNote = inputRecipeNote.value ?: ""
        val preparationMinutes : Int = inputPreparationTimeMinutes.value?.toIntOrNull() ?: timeSeconds(0)
        val preparationSeconds : Int = inputPreparationTimeSeconds.value?.toIntOrNull() ?: 0
        val totalPreparationTimeInSeconds: Int = preparationMinutes + preparationSeconds

        val recipe = Recipe(0, recipeName, recipeCoffeeBlend, recipeCoffeeAmount,
            recipeGrindSize, recipeBrewMethod, recipeWater, recipeWaterTemperature,
            recipeImageSrc, recipeNote
        )

        val step = Step(0, recipe.recipeId, "Brew", 1, totalPreparationTimeInSeconds)

        val stringNotes = recipeNote.split(",")
        val notes = ArrayList<TastingNote>()

        for (note in stringNotes) {
            notes.add(TastingNote(0, recipe.recipeId, note))
        }

        insertRecipeWithSteps(RecipeWithSteps(recipe, arrayListOf(step)), notes)
        resetForm()
    }

    companion object {
        const val ONE_MINUTES_IN_SECONDS = 60
    }

}