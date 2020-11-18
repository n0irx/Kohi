package id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.utils

import android.icu.number.NumberFormatter.with
import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import id.ac.ui.cs.mobileprogramming.nataprawiraf.kohi.R

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

