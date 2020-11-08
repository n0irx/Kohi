package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.model.Author
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.model.Recipe
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.model.TastingNote
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.repository.RecipeRepository


class RecipeViewModel(private val repository: RecipeRepository): ViewModel() {

    val recipes: LiveData<List<Recipe>> = repository.recipes

    // for input data
    @Bindable
    val inputName = MutableLiveData<String>();

    @Bindable
    val inputAuthor = MutableLiveData<Author>();

    @Bindable
    val inputImageSrc = MutableLiveData<String>();

    @Bindable
    val inputBrewMethod = MutableLiveData<String>();

    @Bindable
    val inputTastingNotes = MutableLiveData<List<TastingNote>>();

    @Bindable
    val inputPreparationTime = MutableLiveData<Long>();
    // end of input data



}