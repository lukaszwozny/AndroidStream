package put.poznan.pl.androidstream.app.dagger;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import put.poznan.pl.androidstream.api.StreamApi;
import put.poznan.pl.androidstream.api.YoutubeApi;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Named;

@Module
public class YoutubeApiServiceModule {

    //    private static final String BASE_URL = "http://www.youtube.com/";
    private static final String YT_BASE_URL = "https://www.youtube.com/";

    @AppScope
    @Provides
    @Named("YT_BASE_URL")
    String provideBaseUrl() {
        return YT_BASE_URL;
    }

    @AppScope
    @Provides
    YoutubeApi provideYoutubeApi(OkHttpClient client, GsonConverterFactory gson, RxJavaCallAdapterFactory rxAdapter) {
        Retrofit retrofit = new Retrofit.Builder().client(client)
                .baseUrl(YT_BASE_URL).addConverterFactory(gson)
                .addCallAdapterFactory(rxAdapter).build();

        return retrofit.create(YoutubeApi.class);
    }

}
