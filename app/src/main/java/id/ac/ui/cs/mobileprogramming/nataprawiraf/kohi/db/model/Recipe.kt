package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe")
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "recipe_id")
    val id: Long,

    @ColumnInfo(name = "recipe_name")
    var name: String,

    @ColumnInfo(name = "recipe_author")
    var author: Author,

    @ColumnInfo(name = "recipe_image_source")
    var imageSrc: String,

    @ColumnInfo(name = "recipe_brew_method")
    var brewMethod: String = "",

    @ColumnInfo(name = "recipe_tasting_notes")
    var tastingNotes: MutableList<TastingNote>,

    @ColumnInfo(name = "recipe_preparation_time")
    var preparationTime: Long
)
