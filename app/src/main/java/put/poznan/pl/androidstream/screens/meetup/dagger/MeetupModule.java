package put.poznan.pl.androidstream.screens.meetup.dagger;

import dagger.Module;
import dagger.Provides;
import put.poznan.pl.androidstream.api.StreamApi;
import put.poznan.pl.androidstream.screens.meetup.MeetupFragment;
import put.poznan.pl.androidstream.screens.meetup.core.MeetupPresenter;
import put.poznan.pl.androidstream.utils.RxSchedulers;

@Module
public class MeetupModule {

    MeetupFragment context;

    public MeetupModule(MeetupFragment context) {
        this.context = context;
    }

    @MeetupScope
    @Provides
    MeetupPresenter providePresenter(StreamApi api, RxSchedulers schedulers){
        return new MeetupPresenter(api, schedulers);
    }
}
