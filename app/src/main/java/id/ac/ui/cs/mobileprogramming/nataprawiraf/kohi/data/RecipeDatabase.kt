package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.dao.RecipeDao
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.model.Recipe
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.model.Step
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.data.model.TastingNote

@Database(entities = [
    Recipe::class,
    Step::class,
    TastingNote::class
], version = 1, exportSchema = false)
abstract class RecipeDatabase: RoomDatabase() {

    abstract val recipeDao: RecipeDao

    companion object {
        @Volatile
        private var INSTANCE: RecipeDatabase? = null
        fun getInstance(context: Context): RecipeDatabase {
            synchronized(this) {
                var instance: RecipeDatabase? = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RecipeDatabase::class.java,
                        "recipe_database"
                    ).build()
                }
                return instance
            }
        }
    }
}