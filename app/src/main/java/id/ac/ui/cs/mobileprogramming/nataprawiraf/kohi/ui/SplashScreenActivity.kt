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

        if(isNetworkAvailable(this)){

            Handler().postDelayed({
                val intent = Intent(this, ListRecipeActivity::class.java)
                startActivity(intent)
                finish()
            }, 3000)
        } else {
            showAlertMenu()
        }
    }

    fun isNetworkAvailable(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var activeNetworkInfo: NetworkInfo? = null
        activeNetworkInfo = cm.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
    }

    private fun showAlertMenu() {
        val builder =
                AlertDialog.Builder(this)

        builder.setMessage("Kohi need Internet Connection for better user experience, please enable it")
        builder.setTitle("Warning!")
        builder.setCancelable(false)

        builder
            .setPositiveButton(
                "Back",
                DialogInterface.OnClickListener { dialog, which ->
                    finish()
                })

        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }
}