package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(primaryKeys = ["recipeId", "stepId"],
    tableName = "recipe_x_step",
    indices = [
        Index(value = ["recipeId"]),
        Index(value = ["stepId"])
    ],
    foreignKeys = [
        ForeignKey(entity = Recipe::class,
            parentColumns = ["recipeId"],
            childColumns = ["recipeId"]),
        ForeignKey(entity = Step::class,
            parentColumns = ["stepId"],
            childColumns = ["stepId"])
    ])
data class RecipeStepCrossRef(
    @ColumnInfo(name = "recipeId") val recipeId: Long,
    @ColumnInfo(name = "stepId") val stepId: Long
)