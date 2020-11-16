package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.ui.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.R

class ListRecipeActivity : AppCompatActivity() {
    private lateinit var listRecipeViewModel: ListRecipeViewModel
    private lateinit var listRecipeAdapter: ListRecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}