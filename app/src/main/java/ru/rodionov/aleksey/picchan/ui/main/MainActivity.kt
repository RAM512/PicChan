package ru.rodionov.aleksey.picchan.ui.main

import ru.rodionov.aleksey.picchan.R
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("onCreate")
        setContentView(R.layout.main_activity)

        supportFragmentManager.beginTransaction()
                .replace(R.id.image_source_frame, ImageSourceFragment.newInstance())
                .replace(R.id.image_result_frame, ImageResultFragment.newInstance())
                .commitNow()
    }

}
