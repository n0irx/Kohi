package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.repository.RecipeRepository
import java.lang.IllegalArgumentException

class ListRecipeViewModelFactory(private val repository: RecipeRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ListRecipeViewModel::class.java)) {
            return ListRecipeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }

}
