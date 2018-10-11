package ru.rodionov.aleksey.picchan.extensions

import android.graphics.*

fun Bitmap.applyMatrix(matrix: Matrix): Bitmap =
    Bitmap.createBitmap(this,
            0, 0,
            this.width, this.height,
            matrix, true)

fun Bitmap.toGrayscale(): Bitmap {
    val grayscaleBitmap = Bitmap.createBitmap(
            width, height, Bitmap.Config.ARGB_8888
    )
    val c = Canvas(grayscaleBitmap)
    val paint = Paint()
    val colorMatrix = ColorMatrix()
    colorMatrix.setSaturation(0f)
    val f = ColorMatrixColorFilter(colorMatrix)
    paint.colorFilter = f
    c.drawBitmap(this, 0f, 0f, paint)
    return grayscaleBitmap
}