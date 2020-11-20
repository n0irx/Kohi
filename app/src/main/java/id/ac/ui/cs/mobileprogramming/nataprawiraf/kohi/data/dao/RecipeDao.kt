package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.model.*


@Dao
abstract class RecipeDao {

    @Query("SELECT * FROM recipe")
    abstract fun getAllRecipes(): LiveData<List<Recipe>>

    @Query("SELECT * FROM step")
    abstract fun getAllSteps(): LiveData<List<Step>>

    @Insert
    abstract suspend fun insertRecipe(recipe: Recipe): Long

    @Insert
    abstract suspend fun insertStep(step: Step): Long

    @Insert
    abstract suspend fun insertSteps(step: List<Step>): List<Long>

    @Insert
    abstract suspend fun insertNotes(notes: List<TastingNote>): List<Long>

    @Transaction
    @Query("SELECT * FROM Recipe")
    abstract fun getRecipesWithSteps(): LiveData<List<RecipeWithSteps>>

    @Transaction
    @Query("SELECT * FROM Recipe")
    abstract fun getRecipesWithNotes(): LiveData<List<RecipeWithNotes>>

}