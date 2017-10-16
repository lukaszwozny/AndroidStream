package put.poznan.pl.androidstream.app.dagger;

import dagger.Module;
import dagger.Provides;
import put.poznan.pl.androidstream.utils.AppRxSchedulers;
import put.poznan.pl.androidstream.utils.RxSchedulers;

@Module
public class RxModule {
    @Provides
    RxSchedulers provideRxSchedulers(){
        return new AppRxSchedulers();
    }
}
