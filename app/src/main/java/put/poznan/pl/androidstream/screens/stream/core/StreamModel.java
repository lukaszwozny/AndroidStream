package put.poznan.pl.androidstream.screens.stream.core;

import put.poznan.pl.androidstream.api.YoutubeApi;
import put.poznan.pl.androidstream.models.VideoUrl;
import rx.Observable;

public class StreamModel {
    private final String VIDEO = "https://www.youtube.com/watch?v=jruKwPpKIv0";

    private YoutubeApi api;

    public StreamModel(YoutubeApi api) {
        this.api = api;
    }

    public Observable<VideoUrl> provideVideoUrl(){
        return api.getYtVidUrl(VIDEO);
    }
}
