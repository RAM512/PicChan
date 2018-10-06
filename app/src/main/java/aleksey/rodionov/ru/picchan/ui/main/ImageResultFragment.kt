package aleksey.rodionov.ru.picchan.ui.main

import aleksey.rodionov.ru.picchan.MainActivity
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import aleksey.rodionov.ru.picchan.R

class ImageResultFragment : Fragment() {

    companion object {
        fun newInstance() = ImageResultFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.image_result_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = MainActivity.obtainViewModel(activity!!)
        // TODO: Use the ViewModel
    }

}
