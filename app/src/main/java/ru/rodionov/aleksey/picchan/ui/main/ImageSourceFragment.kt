package ru.rodionov.aleksey.picchan.ui.main

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.support.v4.content.FileProvider
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import ru.rodionov.aleksey.picchan.R
import timber.log.Timber
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class ImageSourceFragment : Fragment() {

    private lateinit var mImageSource: ImageView
    private var mPhotoFile: File? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        Timber.d("onCreateView")
        return inflater.inflate(R.layout.image_source_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("onViewCreated")
        mImageSource = view.findViewById(R.id.image_source)
        val setImageButton = view.findViewById<Button>(R.id.button_set_image)
        setImageButton.setOnClickListener { showSelectSourceDialog() }
    }

    private fun showSelectSourceDialog() {
        val items = resources.getStringArray(R.array.sources_array)
        AlertDialog.Builder(activity)
                .setTitle(getString(R.string.select_source))
                .setItems(items) { _, i ->
                    when (i) {
                        0 -> requestLocalImage()
                        1 -> requestCameraImage()
                    }
                }
                .show()

    }

    private fun requestLocalImage() {
        val mimeImage = "image/*"

        val pickIntent = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        pickIntent.type = mimeImage

        startActivityForResult(pickIntent, REQUEST_PICK_IMAGE)
    }

    private fun requestCameraImage() {
        fun createImageFile(): File? {
            // Create an image file name
            val timeStamp: String = SimpleDateFormat("ddMMyyyy_HHmmss").format(Date())
            val storageDir: File? = activity?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)

            return try {
                File.createTempFile(
                        "IMG_$timeStamp", /* prefix */
                        ".jpg", /* suffix */
                        storageDir /* directory */
                )
            } catch (e: IOException) {
                Timber.e("createImageFile IOException")
                null
            } catch (e: SecurityException) {
                Timber.e("createImageFile SecurityException")
                null
            }

        }

        if (context == null) {
            Timber.w("requestCameraImage: context is null!")
            return
        }
        val ctx: Context = context!!

        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            takePictureIntent.resolveActivity(ctx.packageManager)?.also {
                // Create the File where the photo should go
                mPhotoFile = createImageFile()
                Timber.d("requestCameraImage temp file $mPhotoFile exist ${mPhotoFile?.exists()}")
                // Continue only if the File was successfully created
                mPhotoFile?.also { file ->
                    val photoURI = FileProvider.getUriForFile(
                            ctx,
                            FILEPROVIDER_AUTHORITY,
                            file
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_CAMERA_IMAGE)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Timber.d("onActivityResult data $data")
        if (resultCode != Activity.RESULT_OK && data?.data == null)
            return

        val imageUri = when (requestCode) {
            REQUEST_PICK_IMAGE -> data?.data
            REQUEST_CAMERA_IMAGE -> Uri.fromFile(mPhotoFile)
            else -> return
        }

        Timber.d("onActivityResult final uri $imageUri")
    }

    companion object {
        private const val REQUEST_PICK_IMAGE = 1
        private const val REQUEST_CAMERA_IMAGE = 2
        private const val FILEPROVIDER_AUTHORITY = "ru.rodionov.aleksey.picchan.fileprovider"

        fun newInstance() = ImageSourceFragment()
    }

}
