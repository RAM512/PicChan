package aleksey.rodionov.ru.picchan

import aleksey.rodionov.ru.picchan.ui.main.ImageResultFragment
import aleksey.rodionov.ru.picchan.ui.main.ImageSourceFragment
import aleksey.rodionov.ru.picchan.ui.main.MainViewModel
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    companion object {
        fun obtainViewModel(activity: FragmentActivity) =
                ViewModelProviders.of(activity).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("onCreate")
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.image_source_frame, ImageSourceFragment.newInstance())
                    .replace(R.id.image_result_frame, ImageResultFragment.newInstance())
                    .commitNow()
        }
    }

}
