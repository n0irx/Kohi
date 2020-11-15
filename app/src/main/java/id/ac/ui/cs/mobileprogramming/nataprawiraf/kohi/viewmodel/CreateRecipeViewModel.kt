package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.viewmodel

import android.view.View
import androidx.databinding.Bindable
import androidx.databinding.BindingConversion
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.model.Recipe
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.repository.RecipeRepository
import kotlinx.coroutines.launch


class CreateRecipeViewModel(private val repository: RecipeRepository): ViewModel() {

    var inputName = MutableLiveData<String>();
    var inputCoffeeWeight = MutableLiveData<Int>();
    var inputCoffeeBlend = MutableLiveData<String>();
    var inputWater = MutableLiveData<Int>();
    var inputWaterTemperature = MutableLiveData<Int>();
    var inputImageSrc = MutableLiveData<String>();
    var inputBrewMethod = MutableLiveData<String>();
    var inputPreparationTime = MutableLiveData<Long>();

    fun createRecipe() {
        val recipeName = inputName.value!!
        val recipeCoffee = inputCoffeeWeight.value!!
        val recipeWater = inputWater.value!!
        val recipeTemperature = inputWaterTemperature.value!!
    }


}