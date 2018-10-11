package ru.rodionov.aleksey.picchan.extensions

import android.content.ContentResolver
import android.net.Uri
import android.provider.MediaStore
import java.io.FileNotFoundException
import java.io.IOException


fun Uri.toBitmap(contentResolver: ContentResolver) =
        try {
            MediaStore.Images.Media.getBitmap(contentResolver, this)
        } catch (e: FileNotFoundException) {
            null
        } catch (e: IOException) {
            null
        }