package put.poznan.pl.androidstream.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Streaming;

public interface StreamApi {
    @GET("/{url}/")
    @Streaming
    Call<ResponseBody> getEventDetail(
            @Path("url") String url
    );
}
