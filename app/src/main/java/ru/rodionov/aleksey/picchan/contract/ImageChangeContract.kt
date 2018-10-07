package ru.rodionov.aleksey.picchan.contract

import ru.rodionov.aleksey.picchan.ImageLoadCallback
import android.content.ContentResolver
import android.graphics.Bitmap
import android.net.Uri

interface ImageChangeContract {
    interface View {
        fun setImageSource(bitmap: Bitmap)
        fun showErrorMessage(message: String)

        fun addResult(bitmap: Bitmap)
        fun removeResult(bitmap: Bitmap)
    }

    interface Presenter {
        fun useImageSource(imageUri: Uri, contentResolver: ContentResolver)
        fun useImageSource(bitmap: Bitmap)
        fun removeResult()

        fun rotateImage()
        fun grayScaleImage()
        fun mirrorImage()
    }

    interface Model {
        fun getId()
        fun loadImage(imageUri: Uri): ImageLoadCallback
    }
}