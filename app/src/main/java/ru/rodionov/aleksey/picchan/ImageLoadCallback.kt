package ru.rodionov.aleksey.picchan

import android.graphics.Bitmap

interface ImageLoadCallback {
    fun onProgressChanged(progress: Int)
    fun onLoadComplete(image: Bitmap)
    fun onLoadFailed()
}
