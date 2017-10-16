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

    @AppScope
    @Provides
    StreamApi provideQuizApi(Retrofit retrofit) {
        return retrofit.create(StreamApi.class);
    }

}
