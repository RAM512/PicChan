package ru.rodionov.aleksey.picchan.model

import ru.rodionov.aleksey.picchan.contract.ImageChangeContract
import ru.rodionov.aleksey.picchan.ImageLoadCallback
import android.graphics.Bitmap
import android.net.Uri
import timber.log.Timber

class ImageModel : ImageChangeContract.Model {

    override fun loadImage(imageUri: Uri): ImageLoadCallback {
        Timber.d("loadImage $imageUri")
        return object : ImageLoadCallback {
            override fun onProgressChanged(progress: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onLoadComplete(image: Bitmap) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onLoadFailed() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
    }

    override fun getId() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}