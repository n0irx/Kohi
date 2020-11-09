package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "step")
data class Step(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="step_id")
    val stepId: Long,

    @ColumnInfo(name="step_name")
    var stepName: String,

    @ColumnInfo(name="step_number")
    var stepNumber: Int,

    @ColumnInfo(name="step_time_in_seconds")
    var stepTimeInSeconds: Long
)