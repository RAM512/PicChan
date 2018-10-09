package ru.rodionov.aleksey.picchan.ui.main

import ru.rodionov.aleksey.picchan.R
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    companion object {
        private const val IMAGE_SOURCE_TAG = "image_source"
        private const val IMAGE_RESULT_TAG = "image_result"
    }

    private var mImageSourceFragment: ImageSourceFragment? = null
    private var mImageResultFragment: ImageResultFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("onCreate")
        setContentView(R.layout.main_activity)

        val fm = supportFragmentManager
        mImageSourceFragment = fm.findFragmentByTag(IMAGE_SOURCE_TAG) as ImageSourceFragment?
        mImageResultFragment = fm.findFragmentByTag(IMAGE_RESULT_TAG) as ImageResultFragment?

        val fragmentTransaction = fm.beginTransaction()
        if (mImageSourceFragment == null) {
            Timber.d("onCreate: create source fragment instance")
            mImageSourceFragment = ImageSourceFragment.newInstance()
            fragmentTransaction.add(R.id.image_source_frame, mImageSourceFragment, IMAGE_SOURCE_TAG)
        }
        if (mImageResultFragment == null) {
            Timber.d("onCreate: create result fragment instance")
            mImageResultFragment = ImageResultFragment.newInstance()
            fragmentTransaction.add(R.id.image_result_frame, mImageResultFragment, IMAGE_RESULT_TAG)
        }
        fragmentTransaction.commitNow()
    }

}
