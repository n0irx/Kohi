package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.ui.create

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.R
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.ui.list.ListRecipeActivity


class CreateRecipeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_recipe)
    }

    override fun onBackPressed() {
        val intent: Intent = Intent(this, ListRecipeActivity::class.java)
        startActivity(intent)
    }

}