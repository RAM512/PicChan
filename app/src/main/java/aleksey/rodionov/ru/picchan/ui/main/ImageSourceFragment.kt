package aleksey.rodionov.ru.picchan.ui.main

import aleksey.rodionov.ru.picchan.MainActivity
import aleksey.rodionov.ru.picchan.R
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ImageSourceFragment : Fragment() {

    companion object {
        fun newInstance() = ImageSourceFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.image_source_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = MainActivity.obtainViewModel(activity!!)
        // TODO: Use the ViewModel
    }

}
