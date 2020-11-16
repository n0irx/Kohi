package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.model.Recipe
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.repository.RecipeRepository
import kotlinx.coroutines.launch


class CreateRecipeViewModel(private val repository: RecipeRepository): ViewModel() {

    var inputName = MutableLiveData<String>()
    var inputCoffeeAmount = MutableLiveData<Int>()
    var inputCoffeeBlend = MutableLiveData<String>()
    var inputWaterAmount = MutableLiveData<Int>()
    var inputGrindSize = MutableLiveData<String>()
    var inputWaterTemperature = MutableLiveData<Int>()
    var inputImageSrc = MutableLiveData<String>()
    var inputBrewMethod = MutableLiveData<String>()
    var inputRecipeNote = MutableLiveData<String>()

    fun createRecipe() {
        val recipeName = inputName.value!!
        val recipeCoffeeBlend = inputCoffeeBlend.value!!
        val recipeCoffeeAmount = inputCoffeeAmount.value!!
        val recipeGrindSize = inputGrindSize.value!!
        val recipeBrewMethod = inputBrewMethod.value!!
        val recipeWater = inputWaterAmount.value!!
        val recipeWaterTemperature = inputWaterTemperature.value!!
        val recipeImageSrc = inputImageSrc.value!!
        val recipeNote = inputRecipeNote.value!!

        insertRecipe(Recipe(
            0,
            recipeName,
            recipeCoffeeBlend,
            recipeCoffeeAmount,
            recipeGrindSize,
            recipeBrewMethod,
            recipeWater,
            recipeWaterTemperature,
            recipeImageSrc,
            recipeNote
        ))

    }

    fun insertRecipe(recipe: Recipe) = viewModelScope.launch { repository.insertRecipe(recipe) }

}