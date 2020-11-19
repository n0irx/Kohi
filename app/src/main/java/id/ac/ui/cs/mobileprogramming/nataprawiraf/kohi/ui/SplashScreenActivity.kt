package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.R
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.ui.list.ListRecipeActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            val intent = Intent(this, ListRecipeActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}