package ru.rodionov.aleksey.picchan.presenter

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import ru.rodionov.aleksey.picchan.R
import ru.rodionov.aleksey.picchan.contract.ImageChangeContract
import ru.rodionov.aleksey.picchan.extensions.toBitmap
import timber.log.Timber

class ImagePresenter(private val view: ImageChangeContract.View, private val context: Context) : ImageChangeContract.Presenter {

    override fun useImageSource(imageUri: Uri, contentResolver: ContentResolver) {
        Timber.d("useImageSource uri $imageUri")
        val bitmap = imageUri.toBitmap(contentResolver)
        if (bitmap != null)
            view.setImageSource(bitmap)
        else
            view.showErrorMessage(context.getString(R.string.error_couldnt_use_image))
    }

    override fun useImageSource(bitmap: Bitmap) {
        Timber.d("useImageSource bitmap $bitmap")
        view.setImageSource(bitmap)
    }

    override fun removeResult() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun rotateImage() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun grayScaleImage() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun mirrorImage() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}