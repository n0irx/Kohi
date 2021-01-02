package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.ui

import MyGLSurfaceView
import android.content.*
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.opengl.GLSurfaceView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AlertDialog
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.ui.list.ListRecipeActivity

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var gLView: GLSurfaceView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gLView = MyGLSurfaceView(this)
        setContentView(gLView)

        Handler().postDelayed({
            val intent = Intent(this, ListRecipeActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}