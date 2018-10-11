package ru.rodionov.aleksey.picchan.ui.main

import android.app.AlertDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.rodionov.aleksey.picchan.R
import ru.rodionov.aleksey.picchan.presenter.ImagePresenter
import timber.log.Timber

class ImageResultFragment : Fragment() {

    private var mListener: OnImageSelectedListener? = null
    private lateinit var mImagePresenter: ImagePresenter
    private lateinit var imageResultAdapter: ImageResultAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("onCreate")
        mImagePresenter = getPresenter()
        mImagePresenter.imageResults.observe(this, Observer {
            Timber.d("onChanged list size ${it?.size}")
            imageResultAdapter.notifyDataSetChanged()
        })
    }

    private fun getPresenter() =
            activity?.run {
                ViewModelProviders.of(this).get(ImagePresenter::class.java)
            } ?: throw Exception("Invalid Activity")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Timber.d("onCreateView")

        val view = inflater.inflate(R.layout.image_result_fragment, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            val list = mImagePresenter.imageResults.value
            if (list != null) {
                imageResultAdapter = ImageResultAdapter(list, mListener)
                with(view) {
                    layoutManager = LinearLayoutManager(context)
                    adapter = imageResultAdapter
                }
            } else {
                Timber.e("onCreateView: image result list is null")
            }
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Timber.d("onAttach")

        mListener = object: OnImageSelectedListener {
            override fun onImageSelected(image: Bitmap) {
                Timber.d("onImageSelected")
                showSelectActionDialog(image)
            }
        }
    }

    private fun showSelectActionDialog(image: Bitmap) {
        val items = resources.getStringArray(R.array.result_actions_array)
        activity?.also {
            AlertDialog.Builder(activity)
                    .setTitle(getString(R.string.select_source))
                    .setItems(items) { _, i ->
                        when (i) {
                            0 -> useAsSource(image)
                            1 -> removeResult(image)
                        }
                    }
                    .show()
        }
    }

    private fun useAsSource(image: Bitmap) {
        Timber.d("useAsSource")
        mImagePresenter.useImageSource(image)
    }

    private fun removeResult(image: Bitmap) {
        Timber.d("removeResult")
        mImagePresenter.removeResult(image)
    }

    interface OnImageSelectedListener {
        fun onImageSelected(image: Bitmap)
    }

    companion object {
        fun newInstance() = ImageResultFragment()
    }
}
