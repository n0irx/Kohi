package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.ui.list

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.R
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.KohiDatabase
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.model.RecipeWithSteps
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.repository.RecipeRepository
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.ui.create.CreateRecipeActivity
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.ui.detail.DetailRecipeActivity
import kotlinx.android.synthetic.main.fragment_list_recipe.*

class ListRecipeFragment : Fragment(), ListRecipeAdapter.OnRecipeCickListener {

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
        val dao = KohiDatabase.getInstance(requireActivity()).recipeDao
        val repository = RecipeRepository(dao)
        factory = ListRecipeViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(ListRecipeViewModel::class.java)
        viewModel.recipesWithSteps.observe(viewLifecycleOwner, Observer { recipesWithSteps ->
            rv_list_recipe.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = ListRecipeAdapter(recipesWithSteps, this)
            }
        })

        btn_add_recipe.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, CreateRecipeActivity::class.java)
            activity?.startActivity(intent)
            activity?.finish()
        })
    }

    override fun onRecipeClick(position: Int) {
        val clickedRecipe: RecipeWithSteps = viewModel.recipesWithSteps.value?.get(position)!!
        val intent = Intent(activity, DetailRecipeActivity::class.java)
        intent.putExtra("recipeWithSteps", clickedRecipe)
        startActivity(intent)
        activity?.finish()
    }
}

