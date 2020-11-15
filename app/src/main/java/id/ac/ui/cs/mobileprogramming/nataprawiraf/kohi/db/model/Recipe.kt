package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe")
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    val recipeId: Long,
    var name: String,
    var coffeeBlend: String,
    var coffeeAmount: Int,
    var waterAmount: Int,
    var waterTemperature: Int,
    var imageSource: String,
    var brewMethod: String,
    var note: String
)
