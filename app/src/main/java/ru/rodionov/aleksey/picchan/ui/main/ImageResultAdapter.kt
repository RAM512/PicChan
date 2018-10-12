package ru.rodionov.aleksey.picchan.ui.main

import android.graphics.Bitmap
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import ru.rodionov.aleksey.picchan.R


import ru.rodionov.aleksey.picchan.ui.main.ImageResultFragment.OnImageSelectedListener

import kotlinx.android.synthetic.main.result_item.view.*
import timber.log.Timber

class ImageResultAdapter(
        private val mImages: List<Bitmap>,
        private val mListener: OnImageSelectedListener?)
    : RecyclerView.Adapter<ImageResultAdapter.ViewHolder>() {

    private var mColorEvenRow: Int = 0
    private var mColorOddRow: Int = 0

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val image = v.tag as Bitmap
            mListener?.onImageSelected(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.result_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (mColorEvenRow == 0 && mColorOddRow == 0) {
            val context = holder.mContainer.context
            mColorEvenRow = ContextCompat.getColor(context, R.color.colorEvenRow)
            mColorOddRow = ContextCompat.getColor(context, R.color.colorOddRow)
        }

        holder.mContainer.setBackgroundColor(if (position % 2 == 0) mColorEvenRow else mColorOddRow)

        val image = mImages[position]
        holder.mImageResult.setImageBitmap(image)

        with(holder.mView) {
            tag = image
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mImages.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mContainer: View = mView.result_item_container
        val mImageResult: ImageView = mView.image_result
    }
}
