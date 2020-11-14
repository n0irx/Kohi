package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_addNewRecipe.setOnClickListener {
            val intent = Intent(this, AddRecipeActivity::class.java)
            // start your next activity
            startActivity(intent)
        }
    }
}