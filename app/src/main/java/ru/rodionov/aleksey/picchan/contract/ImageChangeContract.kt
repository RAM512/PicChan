package ru.rodionov.aleksey.picchan.contract

import ru.rodionov.aleksey.picchan.ImageLoadCallback
import android.content.ContentResolver
import android.graphics.Bitmap
import android.net.Uri

interface ImageChangeContract {
    interface View {
        fun setImageSource(bitmap: Bitmap)
        fun showErrorMessagee(message: String)
    }

    interface Presenter {
        fun getImageSource(bitmap: Bitmap)
        fun getImageSource(imageUri: Uri, contentResolver: ContentResolver)
        fun useAsSource(image: Bitmap)
        fun errorWhilePickImage()
    }

    interface Model {
        fun getId()
        fun loadImage(imageUri: Uri): ImageLoadCallback
    }
}