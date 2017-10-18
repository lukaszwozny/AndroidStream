package put.poznan.pl.androidstream.screens.stream.core;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import put.poznan.pl.androidstream.api.YoutubeApi;
import put.poznan.pl.androidstream.utils.RxSchedulers;
import timber.log.Timber;

public class StreamPresenter extends MvpBasePresenter<StreamView> {

    private RxSchedulers schedulers;
    private StreamModel model;

    public StreamPresenter(RxSchedulers schedulers, StreamModel model) {
        this.schedulers = schedulers;
        this.model = model;
    }

    public void getVideoUrl(){
        model.provideVideoUrl()
                .subscribeOn(schedulers.internet())
                .observeOn(schedulers.androidThread())
                .subscribe(v -> {
                    getView().setVideoUrlView(v.getVidUrl());
                },throwable -> {
                    Timber.e(throwable.getMessage());
                });
    }
}
