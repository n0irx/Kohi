package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.ui.create

import android.Manifest
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.R
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.ui.PermissionInfoActivity
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.ui.list.ListRecipeActivity


class CreateRecipeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "${getString(R.string.app_name)} | ${getString(R.string.create_recipe_title)}"
        setContentView(R.layout.activity_create_recipe)

        permissionProcess()
    }

    override fun onBackPressed() {
        val intent: Intent = Intent(this, ListRecipeActivity::class.java)
        startActivity(intent)
    }

    fun permissionProcess() {
        if (Build.VERSION.SDK_INT < 23) {
        } else {
            val hasReadExternalStoragePermission = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
            if (hasReadExternalStoragePermission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    101)
                return
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            101 -> if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted, do your stuff
            } else {
                // Permission Denied
                Toast.makeText(this, "READ_EXTERNAL_STORAGE Denied", Toast.LENGTH_SHORT)
                    .show()

                val dialogBuilder = AlertDialog.Builder(this)

                dialogBuilder.setMessage("You have to enable READ_EXTERNAL_STORAGE Permission from App setting to use this feature")
                    // if the dialog is cancelable
                    .setCancelable(false)
                    // positive button text and action
                    .setPositiveButton("OK", DialogInterface.OnClickListener {
                           dialog, id -> run {
//                            val intent = Intent(this, ListRecipeActivity::class.java)
//                            startActivity(intent)
                            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 101)
                        }
                    })
                    // negative button text and action
                    .setNegativeButton("No", DialogInterface.OnClickListener {
                            dialog, id -> run {
                                val intent = Intent(this, PermissionInfoActivity::class.java)
                                startActivity(intent)
                        }
                    })

                val alert = dialogBuilder.create()
                // set title for alert dialog box
                alert.setTitle("Permission Needed")
                // show alert dialog
                alert.show()
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }
}