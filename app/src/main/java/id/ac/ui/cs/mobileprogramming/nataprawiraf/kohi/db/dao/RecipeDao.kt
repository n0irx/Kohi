package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.model.Recipe
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.model.RecipeWithSteps

@Dao
interface RecipeDao {

    @Insert
    suspend fun insertRecipe(recipe: Recipe)

    @Update
    suspend fun updateRecipe(recipe: Recipe)

    @Delete
    suspend fun deleteRecipe(recipe: Recipe)

    @Query("SELECT * FROM recipe")
    fun getAllRecipes(): LiveData<List<Recipe>>

    @Transaction
    @Query("SELECT * FROM recipe")
    fun getRecipesWithSteps(): LiveData<List<RecipeWithSteps>>
}