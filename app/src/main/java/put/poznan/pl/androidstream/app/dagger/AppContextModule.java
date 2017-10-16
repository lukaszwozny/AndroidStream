package put.poznan.pl.androidstream.app.dagger;

import android.content.Context;
import dagger.Module;
import dagger.Provides;

@Module
public class AppContextModule {
    private final Context context;

    public AppContextModule(Context context) {
        this.context = context;
    }

    @AppScope
    @Provides
    Context provideContext(){
        return context;
    }
}
