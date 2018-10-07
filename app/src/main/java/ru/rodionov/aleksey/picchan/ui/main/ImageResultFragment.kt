package ru.rodionov.aleksey.picchan.ui.main

import ru.rodionov.aleksey.picchan.R
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import timber.log.Timber

class ImageResultFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Timber.d("onCreateView")
        return inflater.inflate(R.layout.image_result_fragment, container, false)
    }

    companion object {
        fun newInstance() = ImageResultFragment()
    }

}
