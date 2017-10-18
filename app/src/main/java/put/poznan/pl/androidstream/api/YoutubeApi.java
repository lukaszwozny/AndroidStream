package put.poznan.pl.androidstream.api;

import okhttp3.ResponseBody;
import put.poznan.pl.androidstream.models.VideoUrl;
import retrofit2.Call;
import retrofit2.http.*;
import rx.Observable;

public interface YoutubeApi {
    @GET("/")
    Observable<VideoUrl> getYtVidUrl(
            @Query("url") String yt_url
    );
}
