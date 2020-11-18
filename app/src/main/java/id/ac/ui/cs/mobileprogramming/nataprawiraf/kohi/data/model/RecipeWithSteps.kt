package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import java.io.Serializable

data class RecipeWithSteps(
    @Embedded val recipe: Recipe,
    @Relation(
        parentColumn = "recipeId",
        entityColumn = "stepId",
        associateBy = Junction(RecipeStepCrossRef::class)
    )
    val steps: List<Step>
) : Serializable {
    private fun getTotalPreparationTime(): Int {
        return steps.sumBy { it.timeInSeconds.toInt() }
    }

    fun getPreparationTimeSeconds(): Int {
       return getTotalPreparationTime() % 60
    }

    fun getPreparationTimeMinutes(): Int {
        return getTotalPreparationTime() / 60
    }
}