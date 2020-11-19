package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.util

import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageFromUri", "placeholder")
fun loadImage(view: ImageView, url: String?, placeholder: Drawable) {

    if (!url.isNullOrEmpty()) {
        Glide.with(view)
            .load(Uri.parse(url))
            .into(view)
    } else {
        view.setImageDrawable(placeholder)
    }
}

//@BindingAdapter("imageFromUri")
//fun loadImageFromUriString(view: ImageView, uriString: String) {
//    val uri: Uri = Uri.parse(uriString)
//    Picasso.get().load(uri).into(view)
//}

