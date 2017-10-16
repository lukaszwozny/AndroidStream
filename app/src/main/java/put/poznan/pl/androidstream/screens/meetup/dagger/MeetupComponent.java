package put.poznan.pl.androidstream.screens.meetup.dagger;

import dagger.Component;
import put.poznan.pl.androidstream.app.dagger.AppComponent;
import put.poznan.pl.androidstream.screens.meetup.core.MeetupPresenter;

@MeetupScope
@Component(dependencies = {AppComponent.class}, modules = {MeetupModule.class})
public interface MeetupComponent {
    MeetupPresenter presenter();
}
