package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.repository.RecipeRepository
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class DetailRecipeViewModelFactory(private val repository: RecipeRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailRecipeViewModel::class.java)) {
            return DetailRecipeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }

}
