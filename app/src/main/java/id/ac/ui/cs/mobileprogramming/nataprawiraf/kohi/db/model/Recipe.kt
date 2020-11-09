package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe")
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "recipe_id")
    val recipeId: Long,

    @ColumnInfo(name = "recipe_name")
    var recipeName: String,

    @ColumnInfo(name = "recipe_author_name")
    var authorName: String,

    @ColumnInfo(name = "recipe_image_source")
    var imageSource: String,

    @ColumnInfo(name = "recipe_brew_method")
    var brewMethod: String = "",

    @ColumnInfo(name = "recipe_preparation_note")
    var note: String
)
