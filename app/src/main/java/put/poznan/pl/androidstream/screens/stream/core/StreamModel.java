package put.poznan.pl.androidstream.screens.stream.core;

import put.poznan.pl.androidstream.api.YoutubeApi;
import put.poznan.pl.androidstream.models.VideoUrl;
import rx.Observable;

public class StreamModel {
    public final String VIDEO_1 = "https://www.youtube.com/watch?v=jruKwPpKIv0";
    public final String VIDEO_2 = "https://www.youtube.com/watch?v=-xU7xBjmyaw";
    public final String VIDEO_3 = "https://www.youtube.com/watch?v=FXf46N217Wg";

    private YoutubeApi api;

    public StreamModel(YoutubeApi api) {
        this.api = api;
    }

    public Observable<VideoUrl> provideVideoUrl(final String video_url){
        return api.getYtVidUrl(video_url);
    }
}
