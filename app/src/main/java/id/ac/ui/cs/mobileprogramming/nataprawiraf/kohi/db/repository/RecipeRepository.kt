package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.repository

import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.RecipeDatabase
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.dao.RecipeDao
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.model.Recipe

class RecipeRepository(private val dao: RecipeDao) {
    val recipes: LiveData<List<Recipe>> = dao.getAllRecipes()

    suspend fun insertRecipe(recipe: Recipe) {
        dao.insertRecipe(recipe)
    }

    suspend fun updateRecipe(recipe: Recipe) {
        dao.updateRecipe(recipe)
    }
    
    suspend fun deleteRecipe(recipe: Recipe) {
        dao.deleteRecipe(recipe)
    }


}