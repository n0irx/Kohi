package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.model.Recipe
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.model.RecipeStepCrossRef
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.model.RecipeWithSteps
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.model.Step


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
    abstract suspend fun insertRecipeXStep(recipeXStep: RecipeStepCrossRef)

    @Transaction
    @Query("SELECT * FROM Recipe")
    abstract fun getRecipesWithSteps(): LiveData<List<RecipeWithSteps>>

}