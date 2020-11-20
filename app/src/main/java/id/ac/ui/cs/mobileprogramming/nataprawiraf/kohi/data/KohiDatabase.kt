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
abstract class KohiDatabase: RoomDatabase() {

    abstract val recipeDao: RecipeDao

    companion object {
        @Volatile
        private var INSTANCE: KohiDatabase? = null
        fun getInstance(context: Context): KohiDatabase {
            synchronized(this) {
                var instance: KohiDatabase? = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        KohiDatabase::class.java,
                        "recipe_database"
                    ).build()
                }
                return instance
            }
        }
    }
}