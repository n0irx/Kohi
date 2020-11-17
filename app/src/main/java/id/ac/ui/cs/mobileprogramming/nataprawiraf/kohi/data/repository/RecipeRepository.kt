package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.repository

import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.dao.RecipeDao
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.model.Recipe
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.model.RecipeStepCrossRef
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.model.RecipeWithSteps
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.model.Step

class RecipeRepository(private val dao: RecipeDao) {

    val recipes: LiveData<List<RecipeWithSteps>> = dao.getRecipesWithSteps()

    private suspend fun insertRecipe(recipe: Recipe): Long {
        return dao.insertRecipe(recipe)
    }

    private suspend fun insertStep(steps: Step): Long {
        return dao.insertStep(steps)
    }

    private suspend fun insertRecipeXStep(recipeXStep: RecipeStepCrossRef) {
       dao.insertRecipeXStep(recipeXStep)
    }

    suspend fun insertRecipeWithSteps(recipeWithSteps: RecipeWithSteps) {
        val recipeId: Long = insertRecipe(recipeWithSteps.recipe)
        for (step in recipeWithSteps.steps) {
            val stepId: Long = insertStep(step)
            insertRecipeXStep(RecipeStepCrossRef(recipeId, stepId))
        }
    }

    private var dataset: ArrayList<Recipe> =  ArrayList()

    fun getDummyRecipeData(): List<Recipe> {
        setDummyRecipeData()
        return dataset
    }

    private var recipesWithStepsDummyData: ArrayList<RecipeWithSteps> = ArrayList()

    private fun setRecipesWithStepsDummyData() {
        recipesWithStepsDummyData.add(RecipeWithSteps(
            Recipe(0,
                "Mugen Happiness", "Tanamera Kintamani Wine Process", 10,
                "Coarse", "Aeropress",210,
                80,
                "https://www.healthifyme.com/blog/wp-content/uploads/2019/09/Black-coffee-feature-image.jpg",
                "-"),
            arrayOf(
                Step(0, "blooming", 1, 20),
                Step( 0, "brewing", 2, 30),
                Step(0, "watering", 1, 60)
            ).toList())
        )

        recipesWithStepsDummyData.add(RecipeWithSteps(
            Recipe(0,
                "Gayo Madness", "Aceh Gayo", 10,
                "Coarse", "V60",210,
                80,
                "https://image.freepik.com/free-photo/top-view-black-coffee-arrangement-cloth_23-2148623238.jpg",
                "-"),
            arrayOf(
                Step(0, "blooming", 1, 20),
                Step( 0, "brewing", 2, 30),
                Step(0, "watering", 1, 60)
            ).toList())
        )

        recipesWithStepsDummyData.add(RecipeWithSteps(
            Recipe(0,
                "Eternal Fruity", "Ciwidey", 10,
                "Coarse", "Chemex",210,
                80,
                "https://i.ndtvimg.com/i/2015-10/coffee-625_625x350_61444896779.jpg",
                "-"),
            arrayOf(
                Step(0, "blooming", 1, 20),
                Step( 0, "brewing", 2, 30),
                Step(0, "watering", 1, 60)
            ).toList())
        )

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