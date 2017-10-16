package put.poznan.pl.androidstream.app.dagger;

import dagger.Component;
import put.poznan.pl.androidstream.app.AppSettings;
import put.poznan.pl.androidstream.utils.RxSchedulers;

@AppScope
@Component(modules = {
        AppContextModule.class,
        AppSettingsModule.class,
        NetworkModule.class,
        RxModule.class,
        StreamApiServiceModule.class
})
public interface AppComponent {
    AppSettings settings();
    RxSchedulers schedulers();
}
