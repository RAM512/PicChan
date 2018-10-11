package ru.rodionov.aleksey.picchan.presenter

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.content.ContentResolver
import android.graphics.*
import android.net.Uri
import ru.rodionov.aleksey.picchan.R
import ru.rodionov.aleksey.picchan.contract.ImageChangeContract
import ru.rodionov.aleksey.picchan.extensions.applyMatrix
import ru.rodionov.aleksey.picchan.extensions.toBitmap
import ru.rodionov.aleksey.picchan.extensions.toGrayscale
import timber.log.Timber


class ImagePresenter(application: Application) :
        ImageChangeContract.Presenter, AndroidViewModel(application) {

    val imageSource: MutableLiveData<Bitmap> by lazy { MutableLiveData<Bitmap>() }

    private val results: MutableList<Bitmap> = ArrayList()
    val imageResults: MutableLiveData<List<Bitmap>> by lazy { MutableLiveData<List<Bitmap>>() }

    var view: ImageChangeContract.View? = null

    override fun useImageSource(imageUri: Uri, contentResolver: ContentResolver) {
        Timber.d("useImageSource uri $imageUri")
        val bitmap = imageUri.toBitmap(contentResolver)
        if (bitmap != null)
            useImageSource(bitmap)
        else
            view?.showErrorMessage(getContext().getString(R.string.error_couldnt_use_image))
    }

    override fun useImageSource(bitmap: Bitmap) {
        Timber.d("useImageSource bitmap $bitmap")
        imageSource.value = bitmap
    }

    override fun removeResult(result: Bitmap) {
        Timber.d("removeResult")
        results.remove(result)
        imageResults.value = results
    }

    private fun addResult(result: Bitmap) {
        Timber.d("addResult")
        results.add(result)
        imageResults.value = results
    }

    override fun rotateImage() {
        Timber.d("rotateImage")
        imageSource.value?.let {
            val matrix = Matrix().apply { setRotate(90f) }
            val rotatedBitmap = it.applyMatrix(matrix)
            addResult(rotatedBitmap)
        }
    }

    override fun grayscaleImage() {
        Timber.d("grayscaleImage")
        imageSource.value?.let {
            addResult( it.toGrayscale() )
        }
    }

    override fun mirrorImage() {
        Timber.d("mirrorImage")
        imageSource.value?.let {
            val matrix = Matrix().apply { setScale(-1f, 1f) }
            val mirroredBitmap = it.applyMatrix(matrix)
            addResult(mirroredBitmap)
        }
    }

    private fun getContext() = getApplication<Application>()
}