package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.repository

import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.dao.RecipeDao
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.model.Recipe
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.model.RecipeWithSteps
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.model.Step
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.model.TastingNote

class RecipeRepository(private val dao: RecipeDao) {

    val recipes: LiveData<List<RecipeWithSteps>> = dao.getRecipesWithSteps()

    private suspend fun insertRecipe(recipe: Recipe): Long {
        return dao.insertRecipe(recipe)
    }

    private suspend fun insertSteps(steps: List<Step>): List<Long> {
        return dao.insertSteps(steps)
    }

    private suspend fun insertNotes(notes: List<TastingNote>): List<Long> {
        return dao.insertNotes(notes)
    }

    suspend fun deleteRecipeWithSteps(recipeWithSteps: RecipeWithSteps) {
        for (step in recipeWithSteps.steps) dao.deleteStep(step)
        dao.deleteRecipe(recipeWithSteps.recipe)
    }

    suspend fun insertRecipeWithSteps(recipeWithSteps: RecipeWithSteps, notes: List<TastingNote>) {
        val recipeId = insertRecipe(recipeWithSteps.recipe)
        for (step in recipeWithSteps.steps) step.recipeId = recipeId
        for (note in notes) note.recipeId = recipeId
        insertSteps(recipeWithSteps.steps)
        insertNotes(notes)
    }
}