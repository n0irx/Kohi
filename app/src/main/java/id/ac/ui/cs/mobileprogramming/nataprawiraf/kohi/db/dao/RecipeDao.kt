package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.model.Recipe
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.model.RecipeStepCrossRef
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.model.Step

@Dao
interface RecipeDao {

    @Insert
    fun insertRecipe(recipe: Recipe)

    @Update
    fun updateRecipe(recipe: Recipe)

    @Delete
    fun deleteRecipe(recipe: Recipe)

    @Query("SELECT * FROM recipe")
    fun getAllRecipes(): LiveData<List<Recipe>>

    @Query("""
        SELECT * FROM step INNER JOIN recipe_x_step ON
        step.stepId = recipe_x_step.stepId WHERE
        recipe_x_step.recipeId = :recipeId
        """)
    fun getStepsWithRecipeId(recipeId: String): List<Step>

    @Insert
    fun insert(join: RecipeStepCrossRef)

}