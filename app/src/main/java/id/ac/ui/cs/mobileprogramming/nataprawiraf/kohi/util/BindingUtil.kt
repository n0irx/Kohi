package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.util

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageFromUri")
fun loadImage(view: ImageView, url: String) {
    Glide.with(view)
        .load(Uri.parse(url))
        .into(view)
}

//@BindingAdapter("imageFromUri")
//fun loadImageFromUriString(view: ImageView, uriString: String) {
//    val uri: Uri = Uri.parse(uriString)
//    Picasso.get().load(uri).into(view)
//}

