package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "tasting_note")
data class TastingNote(
    @PrimaryKey(autoGenerate = true)
    val tastingNoteId: Long,
    var recipeId: Long,
    var note: String
): Serializable