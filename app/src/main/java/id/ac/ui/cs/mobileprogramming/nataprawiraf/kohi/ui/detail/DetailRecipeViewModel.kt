package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.model.RecipeWithSteps
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.repository.RecipeRepository


class DetailRecipeViewModel(private val repository: RecipeRepository): ViewModel() {

    val name = MutableLiveData("")
    val coffeeAmount = MutableLiveData("0")
    val coffeeBlend = MutableLiveData("")
    val waterAmount = MutableLiveData("0")
    val grindSize = MutableLiveData("")
    val waterTemperature = MutableLiveData("0")
    val brewMethod = MutableLiveData("")
    var imageSource = MutableLiveData("")
    var recipeNote = MutableLiveData("")
    var preparationTimeMinutes = MutableLiveData("0")
    var preparationTimeSeconds = MutableLiveData("0")

    private var _preparationTimeTextView: MutableLiveData<String> = MutableLiveData("${preparationTimeMinutes.value}:${preparationTimeSeconds.value}")

    var preparationTimeTextView: MutableLiveData<String>
        get() = _preparationTimeTextView
        set(value) {
            _preparationTimeTextView = value
        }

    fun getTotalPreparationTimeInSeconds(): Long {
        return preparationTimeSeconds.value?.toLong()!! + preparationTimeMinutes.value?.toLong()!! * 60
    }

    fun setRecipeWithSteps(recipeWithSteps: RecipeWithSteps) {
        name.value = recipeWithSteps.recipe.name
        coffeeAmount.value = recipeWithSteps.recipe.coffeeAmount.toString()
        coffeeBlend.value = recipeWithSteps.recipe.coffeeBlend
        waterAmount.value = recipeWithSteps.recipe.waterAmount.toString()
        grindSize.value = recipeWithSteps.recipe.grindSize
        waterTemperature.value = recipeWithSteps.recipe.waterTemperature.toString()
        brewMethod.value = recipeWithSteps.recipe.brewMethod
        imageSource.value = recipeWithSteps.recipe.imageSource
        recipeNote.value = recipeWithSteps.recipe.note
        preparationTimeMinutes.value = recipeWithSteps.getPreparationTimeMinutes().toString()
        preparationTimeSeconds.value = recipeWithSteps.getPreparationTimeSeconds().toString()

    }
}