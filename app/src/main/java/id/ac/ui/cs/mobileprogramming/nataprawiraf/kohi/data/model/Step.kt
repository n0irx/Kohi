package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "step")
data class Step(
    @PrimaryKey(autoGenerate = true)
    val stepId: Long,
    var name: String,
    var number: Int,
    var timeInSeconds: Long
)