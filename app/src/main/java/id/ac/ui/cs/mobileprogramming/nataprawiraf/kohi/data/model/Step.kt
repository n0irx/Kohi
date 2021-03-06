package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "step")
data class Step(
    @PrimaryKey(autoGenerate = true)
    val stepId: Long,
    var recipeId: Long,
    var name: String,
    var number: Int,
    var timeInSeconds: Int
): Serializable