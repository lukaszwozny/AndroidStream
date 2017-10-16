package put.poznan.pl.androidstream.app.dagger;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import put.poznan.pl.androidstream.api.StreamApi;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Named;

@Module
public class StreamApiServiceModule {

    private static final String BASE_URL = "http://www.youtube.com/";

    @AppScope
    @Provides
    @Named("BASE_URL")
    String provideBaseUrl(){
        return BASE_URL;
    }

    @AppScope
    @Provides
    StreamApi provideQuizApi(OkHttpClient client, GsonConverterFactory gson, RxJavaCallAdapterFactory rxAdapter){

        Retrofit retrofit =   new Retrofit.Builder().client(client)
                .baseUrl(BASE_URL).addConverterFactory(gson).
                        addCallAdapterFactory(rxAdapter).build();

        return  retrofit.create(StreamApi.class);
    }

}
