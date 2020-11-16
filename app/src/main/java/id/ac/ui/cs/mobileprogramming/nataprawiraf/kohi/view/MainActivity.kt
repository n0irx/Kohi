package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.R
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.databinding.ActivityMainBinding
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.RecipeDatabase
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.repository.RecipeRepository
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.viewmodel.ListRecipeViewModel
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.viewmodel.ListRecipeViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var listRecipeViewModel: ListRecipeViewModel
    private lateinit var listRecipeAdapter: ListRecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val dao = RecipeDatabase.getInstance(application).recipeDao
        val repository = RecipeRepository(dao)
        val factory = ListRecipeViewModelFactory(repository)

        listRecipeViewModel = ViewModelProvider(this, factory).get(ListRecipeViewModel::class.java)
        binding.listRecipeViewModel =  listRecipeViewModel
        binding.lifecycleOwner = this

        btn_addNewRecipe.setOnClickListener {
            val intent = Intent(this, CreateRecipeActivity::class.java)
            startActivity(intent)
        }

        displayRecipeList()
    }

    private fun displayRecipeList() {
    }

    private fun initRecycleView() {
        rv_recipe_list.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            listRecipeAdapter = ListRecipeAdapter()
            adapter = listRecipeAdapter
        }
    }
}