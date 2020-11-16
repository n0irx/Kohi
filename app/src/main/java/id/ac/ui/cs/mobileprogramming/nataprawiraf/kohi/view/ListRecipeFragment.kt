package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.view

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.R
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.RecipeDatabase
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.repository.RecipeRepository
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.viewmodel.ListRecipeViewModel
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.viewmodel.ListRecipeViewModelFactory

class ListRecipeFragment : Fragment() {

    private lateinit var factory: ListRecipeViewModelFactory
    private lateinit var viewModel: ListRecipeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_recipe, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val dao = RecipeDatabase.getInstance(activity!!).recipeDao
        val repository = RecipeRepository(dao)
        factory = ListRecipeViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(ListRecipeViewModel::class.java)
    }
}