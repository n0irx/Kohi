package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.utils

import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import java.net.URI

@BindingAdapter("image")
fun loadImage(view: ImageView, url: String) {
    Glide.with(view)
        .load(url)
        .into(view)
}

@BindingAdapter("imageFromUri")
fun loadImageFromUriString(view: ImageView, uriString: String) {
    val uri: Uri = Uri.parse(uriString)
    if (uriString == "") {
        Picasso.get().load("https://www.healthifyme.com/blog/wp-content/uploads/2019/09/Black-coffee-feature-image.jpg")
    } else {
        Picasso.get().load(uri).into(view)
    }
}

