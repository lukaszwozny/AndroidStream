package put.poznan.pl.androidstream.screens;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import put.poznan.pl.androidstream.R;
import put.poznan.pl.androidstream.api.StreamApi;
import put.poznan.pl.androidstream.app.AppController;
import put.poznan.pl.androidstream.utils.RxSchedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;

import java.io.IOException;

public class MeetupActivity extends AppCompatActivity {
    private static final String TAG = "MeetupApi";

    private Unbinder unbinder;

    // the interface to the stream endpoint
    interface MeetupAPI {
        @GET("rsvps")
        @Streaming
        Observable<ResponseBody> meetupStream();
    }

    StreamApi api;
    RxSchedulers schedulers;

    Subscription mSubs = null;

    // Gson for conversion to object
    Gson mGson = new GsonBuilder().create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        injectDependencies();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetup);
        unbinder = ButterKnife.bind(this);
    }

    private void injectDependencies() {
        api = AppController.getAppComponent().streamApi();
        schedulers = AppController.getAppComponent().schedulers();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    void listen() {
        // only subscribe to the stream if we're not already listening
        if (mSubs == null || mSubs.isUnsubscribed()) {
            mSubs = api.meetupStream()
                    .subscribeOn(Schedulers.newThread())
                    .flatMap(responseBody -> events(responseBody.source()))
                    .map(item -> mGson.fromJson(item, RSVP.class))
                    .observeOn(schedulers.androidThread())
                    .subscribe(item -> Log.d(TAG, item.toString()),
                            error -> Log.e(TAG, error.getMessage()),
                            () -> Log.d(TAG, "onComplete"));
        }
    }

    public Observable<String> events(final BufferedSource source) {
        // an observable to read events one by one
        return Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                // TODO: unsubscribe gracefully
                try {
                    while (!source.exhausted()) {
                        subscriber.onNext(source.readUtf8Line());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
                subscriber.onCompleted();
            }
        });
    }

    @OnClick(R.id.listen)
    void listenClick() {
        listen();
    }

    @OnClick(R.id.enough)
    void enoughClick() {
        if (mSubs != null && !mSubs.isUnsubscribed()) {
            mSubs.unsubscribe();
        }
    }

    // this class represents a RSVP
    public class RSVP {
        @SerializedName("response")
        String mResponse;

        @SerializedName("member")
        Member mMember;

        @Override
        public String toString() {
            return mMember.mName + " says " + mResponse + "!";
        }
    }

    // this class represents a meetup member
    public class Member {
        @SerializedName("member_name")
        String mName;
    }
}
