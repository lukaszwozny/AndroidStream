package put.poznan.pl.androidstream.api;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import rx.Observable;

public interface YoutubeApi {
    @GET("rsvps")
    Observable<ResponseBody> meetupStream();
}
