package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.model.Recipe
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.repository.RecipeRepository


class ListRecipeViewModel(private val repository: RecipeRepository): ViewModel() {
    private lateinit var _recipes: MutableLiveData<List<Recipe>>
    val recipes: LiveData<List<Recipe>>
        get() = _recipes

    fun getAllRecipes() {
        val recipes = repository.getDummyRecipeData()
        _recipes.value = recipes
    }
}