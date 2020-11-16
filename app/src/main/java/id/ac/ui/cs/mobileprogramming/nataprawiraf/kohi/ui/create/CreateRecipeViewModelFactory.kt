package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.repository.RecipeRepository
import java.lang.IllegalArgumentException

class CreateRecipeViewModelFactory(private val repository: RecipeRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CreateRecipeViewModel::class.java)) {
            return CreateRecipeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }

}
