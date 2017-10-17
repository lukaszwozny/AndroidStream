package put.poznan.pl.androidstream.screens.stream.dagger;

import dagger.Module;
import dagger.Provides;
import put.poznan.pl.androidstream.api.YoutubeApi;
import put.poznan.pl.androidstream.screens.stream.core.StreamPresenter;
import put.poznan.pl.androidstream.utils.RxSchedulers;

@Module
public class StreamModule {

    @StreamScope
    @Provides
    StreamPresenter provideStreamPresenter(YoutubeApi api, RxSchedulers schedulers){
        return new StreamPresenter(api,schedulers);
    }
}
