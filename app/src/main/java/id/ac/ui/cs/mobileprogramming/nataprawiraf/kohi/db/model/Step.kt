package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "step")
data class Step(
    @PrimaryKey(autoGenerate = true)
    val stepId: Long,
    var stepName: String,
    var stepNumber: Int,
    var stepTimeInSeconds: Long
)