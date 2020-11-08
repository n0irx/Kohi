package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.dao.RecipeDao
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.db.model.Recipe

@Database(entities = [Recipe::class], version = 1)
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