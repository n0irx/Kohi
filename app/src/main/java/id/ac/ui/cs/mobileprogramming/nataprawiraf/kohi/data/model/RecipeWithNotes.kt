package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.model

import android.provider.ContactsContract
import androidx.room.Embedded
import androidx.room.Relation

data class RecipeWithNotes(
    @Embedded val user: Recipe,
    @Relation(
        parentColumn = "recipeId",
        entityColumn = "recipeId"
    )
    val tastingNotes: List<TastingNote>
)