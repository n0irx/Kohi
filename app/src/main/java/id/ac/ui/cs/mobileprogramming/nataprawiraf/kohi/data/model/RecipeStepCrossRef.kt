package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.model

import androidx.room.Entity

@Entity(primaryKeys = ["recipeId", "stepId"], tableName = "recipe_x_step")
data class RecipeStepCrossRef(
    val recipeId: Long,
    val stepId: Long
)
