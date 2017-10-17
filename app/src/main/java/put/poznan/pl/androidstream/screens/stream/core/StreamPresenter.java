package put.poznan.pl.androidstream.screens.stream.core;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import put.poznan.pl.androidstream.api.YoutubeApi;
import put.poznan.pl.androidstream.utils.RxSchedulers;

public class StreamPresenter extends MvpBasePresenter<StreamView> {

    private YoutubeApi api;
    private RxSchedulers schedulers;

    public StreamPresenter(YoutubeApi api, RxSchedulers schedulers) {
        this.api = api;
        this.schedulers = schedulers;
    }
}
