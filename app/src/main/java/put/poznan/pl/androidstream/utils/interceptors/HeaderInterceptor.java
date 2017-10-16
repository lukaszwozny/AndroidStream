package put.poznan.pl.androidstream.utils.interceptors;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import put.poznan.pl.androidstream.app.AppSettings;
import timber.log.Timber;

import java.io.IOException;

public class HeaderInterceptor implements Interceptor {
    private AppSettings settings;

    public HeaderInterceptor(AppSettings settings) {
        this.settings = settings;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request()
                .newBuilder()
//                .addHeader("Authorization", "JWT " + settings.getAccessToken())
                .build();
        Timber.i("Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers());
        return chain.proceed(request);
    }
}
