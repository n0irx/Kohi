package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class RecipeWithSteps(
    @Embedded val recipe: Recipe,
    @Relation(
        parentColumn = "recipeId",
        entityColumn = "stepId",
        associateBy = Junction(RecipeStepCrossRef::class)
    )
    val steps: List<Step>
)