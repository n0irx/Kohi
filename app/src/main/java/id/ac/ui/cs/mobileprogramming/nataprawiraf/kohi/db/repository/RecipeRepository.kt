package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.dao.RecipeDao
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.model.Recipe

class RecipeRepository(private val dao: RecipeDao) {
    val recipes: LiveData<List<Recipe>> = dao.getAllRecipes()

    fun insertRecipe(recipe: Recipe) {
        dao.insertRecipe(recipe)
    }

    fun updateRecipe(recipe: Recipe) {
        dao.updateRecipe(recipe)
    }
    
    fun deleteRecipe(recipe: Recipe) {
        dao.deleteRecipe(recipe)
    }

    private var dataset: ArrayList<Recipe> =  ArrayList()

    fun getDummyRecipeData(): List<Recipe> {
        setDummyRecipeData()
        var data: List<Recipe> = ArrayList()
        data = dataset
        return data
    }

    private fun setDummyRecipeData() {
        dataset.add(
           Recipe(0, "coffee1", "", 10, "coarse", "Aeropress",10, 78, "https://www.squarebarcafe.com/wp-content/uploads/2016/05/coffee.jpg", "-")
        )
        dataset.add(
            Recipe(0, "coffee2", "", 10, "coarse", "Aeropress",10, 78, "https://www.squarebarcafe.com/wp-content/uploads/2016/05/coffee.jpg", "-")
        )
        dataset.add(
            Recipe(0, "coffee3", "", 10, "coarse", "Aeropress",10, 78, "https://www.squarebarcafe.com/wp-content/uploads/2016/05/coffee.jpg", "-")
        )
    }


}