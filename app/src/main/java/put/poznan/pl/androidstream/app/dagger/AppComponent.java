package put.poznan.pl.androidstream.app.dagger;

import dagger.Component;

@AppScope
@Component(modules = {
        AppContextModule.class,
        AppSettingsModule.class
})
public interface AppComponent {
}
