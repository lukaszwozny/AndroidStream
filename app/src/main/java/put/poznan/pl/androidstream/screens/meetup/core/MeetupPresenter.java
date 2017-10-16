package put.poznan.pl.androidstream.screens.meetup.core;

import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import okio.BufferedSource;
import put.poznan.pl.androidstream.api.StreamApi;
import put.poznan.pl.androidstream.models.RSVP;
import put.poznan.pl.androidstream.utils.RxSchedulers;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import timber.log.Timber;

import java.io.IOException;

public class MeetupPresenter extends MvpBasePresenter<MeetupView> {
    private static final String TAG = "presenter";
    private RxSchedulers schedulers;
    private StreamApi api;

    private Subscription mSubs = null;

    // Gson for conversion to object
    private Gson mGson = new GsonBuilder().create();

    public MeetupPresenter(StreamApi api, RxSchedulers schedulers) {
        this.api = api;
        this.schedulers = schedulers;
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

    public void listen() {
        // only subscribe to the stream if we're not already listening
        if (mSubs == null || mSubs.isUnsubscribed()) {
            mSubs = api.meetupStream()
                    .subscribeOn(Schedulers.newThread())
                    .flatMap(responseBody -> events(responseBody.source()))
                    .map(item -> mGson.fromJson(item, RSVP.class))
                    .observeOn(schedulers.androidThread())
                    .subscribe(item -> {
                                getView().setStreamText(item.toString());
                                Log.d(TAG, item.toString());
                            },
                            error -> Log.e(TAG, error.getMessage()),
                            () -> Log.d(TAG, "onComplete"));
        }
    }

    public void stopListen() {
        if (mSubs != null && !mSubs.isUnsubscribed()) {
            mSubs.unsubscribe();
        }
    }

    public void test() {
        Timber.i("Test :)");
    }
}
