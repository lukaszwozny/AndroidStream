package put.poznan.pl.androidstream.app;

import android.app.Application;
import com.squareup.leakcanary.LeakCanary;
import put.poznan.pl.androidstream.BuildConfig;
import put.poznan.pl.androidstream.app.dagger.AppComponent;
import put.poznan.pl.androidstream.app.dagger.AppContextModule;
import put.poznan.pl.androidstream.app.dagger.DaggerAppComponent;
import timber.log.Timber;

public class AppController extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initialiseLogger();
        initAppComponent();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }

    private void initAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .appContextModule(new AppContextModule(this))
                .build();
    }

    private void initialiseLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new Timber.Tree() {
                @Override
                protected void log(int priority, String tag, String message, Throwable t) {
                    //TODO  decide what to log in release version
                }
            });
        }
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
