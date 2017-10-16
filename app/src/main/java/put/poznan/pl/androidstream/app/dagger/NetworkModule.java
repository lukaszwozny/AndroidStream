package put.poznan.pl.androidstream.app.dagger;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import put.poznan.pl.androidstream.app.AppSettings;
import put.poznan.pl.androidstream.utils.AppRxSchedulers;
import put.poznan.pl.androidstream.utils.interceptors.HeaderInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Named;
import java.io.File;

@Module
public class NetworkModule {

    //    private static final String BASE_URL = "http://www.youtube.com/";
    private static final String BASE_URL = "http://stream.meetup.com/2/";

    @AppScope
    @Provides
    @Named("BASE_URL")
    String provideBaseUrl() {
        return BASE_URL;
    }

    @AppScope
    @Provides
    OkHttpClient provideHttpClient(
            HttpLoggingInterceptor logger,
            HeaderInterceptor headerInterceptor,
            Cache cache
    ) {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.addInterceptor(logger);
        builder.addInterceptor(headerInterceptor);
        builder.cache(cache);
        return builder.build();
    }

    @AppScope
    @Provides
    HeaderInterceptor provideHeaderInterceptor(AppSettings settings){
        return new HeaderInterceptor(settings);
    }

    @AppScope
    @Provides
    HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    @AppScope
    @Provides
    Cache provideCache(File file) {
        return new Cache(file, 10 * 10 * 1000);
    }

    @AppScope
    @Provides
    File provideCacheFile(Context context) {
        return context.getFilesDir();
    }

    @AppScope
    @Provides
    RxJavaCallAdapterFactory provideRxAdapter() {
        return RxJavaCallAdapterFactory.createWithScheduler(AppRxSchedulers.INTERNET_SCHEDULERS);
    }

    @Provides
    GsonConverterFactory provideGsonClient() {
        return GsonConverterFactory.create();
    }

    @AppScope
    @Provides
    Retrofit provideRetrofit(OkHttpClient client, GsonConverterFactory gson, RxJavaCallAdapterFactory rxAdapter){
        return new Retrofit.Builder().client(client)
                .baseUrl(BASE_URL).addConverterFactory(gson)
                .addCallAdapterFactory(rxAdapter).build();
    }

}
