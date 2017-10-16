package put.poznan.pl.androidstream.app.dagger;

import android.content.Context;
import android.content.SharedPreferences;
import dagger.Module;
import dagger.Provides;
import put.poznan.pl.androidstream.app.AppSettings;

@Module
public class AppSettingsModule {
    private final String PREFERENCE_FILE_KEY = "global_preferences";

    @AppScope
    @Provides
    SharedPreferences provideSharedPreferences(Context context) {
        return context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
    }

    @AppScope
    @Provides
    AppSettings provideSettings(SharedPreferences preferences) {
        return new AppSettings(preferences);
    }
}
