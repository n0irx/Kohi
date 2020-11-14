package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe")
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    val recipeId: Long,
    var recipeName: String,
    var authorName: String,
    var imageSource: String,
    var brewMethod: String = "",
    var note: String
)
