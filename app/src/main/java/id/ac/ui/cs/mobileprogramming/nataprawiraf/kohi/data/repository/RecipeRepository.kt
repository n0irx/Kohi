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
        return dataset
    }

    private fun setDummyRecipeData() {
        dataset.add(
           Recipe(0, "Gayo Madness", "Aceh Gayo", 10, "coarse", "Aeropress",210, 80, "https://www.squarebarcafe.com/wp-content/uploads/2016/05/coffee.jpg", "-")
        )
        dataset.add(
            Recipe(0, "Insomnia Killer", "Robust Blend", 10, "coarse", "V60",300, 90, "https://www.squarebarcafe.com/wp-content/uploads/2016/05/coffee.jpg", "-")
        )
        dataset.add(
            Recipe(0, "Anti Latte", "Sumatra", 10, "coarse", "French Press",200, 78, "https://www.squarebarcafe.com/wp-content/uploads/2016/05/coffee.jpg", "-")
        )
        dataset.add(
            Recipe(0, "Gayo Madness", "Aceh Gayo", 10, "coarse", "Aeropress",210, 80, "https://www.squarebarcafe.com/wp-content/uploads/2016/05/coffee.jpg", "-")
        )
        dataset.add(
            Recipe(0, "Insomnia Killer", "Robust Blend", 10, "coarse", "V60",300, 90, "https://www.squarebarcafe.com/wp-content/uploads/2016/05/coffee.jpg", "-")
        )
        dataset.add(
            Recipe(0, "Anti Latte", "Sumatra", 10, "coarse", "French Press",200, 78, "https://www.squarebarcafe.com/wp-content/uploads/2016/05/coffee.jpg", "-")
        )
        dataset.add(
            Recipe(0, "Gayo Madness", "Aceh Gayo", 10, "coarse", "Aeropress",210, 80, "https://www.squarebarcafe.com/wp-content/uploads/2016/05/coffee.jpg", "-")
        )
        dataset.add(
            Recipe(0, "Insomnia Killer", "Robust Blend", 10, "coarse", "V60",300, 90, "https://www.squarebarcafe.com/wp-content/uploads/2016/05/coffee.jpg", "-")
        )
        dataset.add(
            Recipe(0, "Anti Latte", "Sumatra", 10, "coarse", "French Press",200, 78, "https://www.squarebarcafe.com/wp-content/uploads/2016/05/coffee.jpg", "-")
        )
    }


}