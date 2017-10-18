package put.poznan.pl.androidstream.screens.stream.core;

import com.hannesdorfmann.mosby3.mvp.MvpView;
import put.poznan.pl.androidstream.models.VideoUrl;

public interface StreamView extends MvpView {
    void playVideoFromUrl(VideoUrl videoUrl);
}
