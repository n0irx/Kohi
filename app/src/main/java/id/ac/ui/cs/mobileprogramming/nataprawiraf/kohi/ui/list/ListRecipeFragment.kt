package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.R
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.RecipeDatabase
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.repository.RecipeRepository
import kotlinx.android.synthetic.main.fragment_list_recipe.*

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
        val dao = RecipeDatabase.getInstance(requireActivity()).recipeDao
        val repository = RecipeRepository(dao)
        factory = ListRecipeViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(ListRecipeViewModel::class.java)
        viewModel.setRecipes()
        viewModel.recipes.observe(viewLifecycleOwner, Observer { recipes ->
            rv_list_recipe.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = ListRecipeAdapter(recipes)
            }
        })
    }
}