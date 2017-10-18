package put.poznan.pl.androidstream.screens.stream.dagger;

import dagger.Module;
import dagger.Provides;
import put.poznan.pl.androidstream.api.YoutubeApi;
import put.poznan.pl.androidstream.screens.stream.core.StreamModel;
import put.poznan.pl.androidstream.screens.stream.core.StreamPresenter;
import put.poznan.pl.androidstream.utils.RxSchedulers;

@Module
public class StreamModule {

    @StreamScope
    @Provides
    StreamModel provideStreamModel(YoutubeApi api){
        return new StreamModel(api);
    }

    @StreamScope
    @Provides
    StreamPresenter provideStreamPresenter(RxSchedulers schedulers, StreamModel model){
        return new StreamPresenter(schedulers, model);
    }
}
