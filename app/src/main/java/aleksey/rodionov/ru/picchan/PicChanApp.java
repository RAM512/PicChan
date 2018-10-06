package aleksey.rodionov.ru.picchan;

import android.app.Application;

import timber.log.Timber;

public class PicChanApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
