package put.poznan.pl.androidstream.screens.meetup.core;

import com.hannesdorfmann.mosby3.mvp.MvpView;
import okio.BufferedSource;

public interface MeetupView extends MvpView{
    void setStreamText(String text);
}
