package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.model.Recipe
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.repository.RecipeRepository


class ListRecipeViewModel(private val repository: RecipeRepository): ViewModel() {
//    private var _recipes: MutableLiveData<List<Recipe>> = repository.getDummyRecipeData()
    private val _recipes = MutableLiveData<List<Recipe>>()

    val recipes: LiveData<List<Recipe>>
        get() = _recipes

    fun setRecipes() {
        _recipes.value = repository.getDummyRecipeData()
    }
}