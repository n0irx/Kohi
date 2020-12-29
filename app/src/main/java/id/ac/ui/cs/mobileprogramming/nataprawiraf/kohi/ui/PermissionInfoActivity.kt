package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.R
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.ui.list.ListRecipeActivity

class PermissionInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission_info)
    }

    override fun onBackPressed() {
        val intent: Intent = Intent(this, ListRecipeActivity::class.java)
        startActivity(intent)
    }
}