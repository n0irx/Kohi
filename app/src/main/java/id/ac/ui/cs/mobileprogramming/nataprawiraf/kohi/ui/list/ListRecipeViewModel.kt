package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.model.RecipeWithSteps
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.repository.RecipeRepository


class ListRecipeViewModel(private val repository: RecipeRepository): ViewModel() {
    val _recipesWithSteps = repository.recipes

    val recipesWithSteps: LiveData<List<RecipeWithSteps>>
        get() = _recipesWithSteps

}