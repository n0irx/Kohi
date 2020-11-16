package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.repository

import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.dao.RecipeDao
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.model.Recipe
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.model.RecipeWithSteps
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.model.Step

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

    private var recipesWithStepsDummyData: ArrayList<RecipeWithSteps> = ArrayList()

    private fun setRecipesWithStepsDummyData() {
        val recipe: Recipe = Recipe(0,
            "Gayo Madness", "Aceh Gayo", 10,
            "coarse", "Aeropress",210,
            80,
            "https://www.squarebarcafe.com/wp-content/uploads/2016/05/coffee.jpg",
            "-")

        val steps: ArrayList<Step> = ArrayList()

        steps.add(Step(0, "blooming", 1, 20))
        steps.add(Step( 0, "brewing", 2, 40))
        steps.add(Step(0, "watering", 1, 20))

        recipesWithStepsDummyData.add(RecipeWithSteps(recipe, steps))
    }

    fun getRecipesWithStepsDummyData(): List<RecipeWithSteps> {
        setRecipesWithStepsDummyData()
        return recipesWithStepsDummyData
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