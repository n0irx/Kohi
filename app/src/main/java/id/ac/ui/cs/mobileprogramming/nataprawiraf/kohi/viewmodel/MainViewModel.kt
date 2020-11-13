package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.viewmodel

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.model.Recipe
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.repository.RecipeRepository


class MainViewModel(private val repository: RecipeRepository): ViewModel() {
    val recipes: LiveData<List<Recipe>> = repository.recipes
    val inputName = MutableLiveData<String>();
    val inputImageSrc = MutableLiveData<String>();
    val inputBrewMethod = MutableLiveData<String>();
    val inputPreparationTime = MutableLiveData<Long>();
}