package put.poznan.pl.androidstream.screens.stream.core;

import android.app.DownloadManager;
import android.net.Uri;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import put.poznan.pl.androidstream.utils.RxSchedulers;
import timber.log.Timber;

public class StreamPresenter extends MvpBasePresenter<StreamView> {

    private RxSchedulers schedulers;
    private StreamModel model;

    private String full_vid_url = null;

    public StreamPresenter(RxSchedulers schedulers, StreamModel model) {
        this.schedulers = schedulers;
        this.model = model;
    }

    public void getVideo1(){
        getVideoUrl(model.VIDEO_1);
    }

    public void getVideo2(){
        getVideoUrl(model.VIDEO_2);
    }

    public void getVideo3(){
        getVideoUrl(model.VIDEO_3);
    }

    private void getVideoUrl(final String video_url) {
        model.provideVideoUrl(video_url)
                .subscribeOn(schedulers.internet())
                .observeOn(schedulers.androidThread())
                .subscribe(v -> {
                    full_vid_url = v.getVidUrl();
                    getView().playVideoFromUrl(v);
                }, throwable -> {
                    Timber.e(throwable.getMessage());
                });
    }

    public void useDownloadManager(){
        if(full_vid_url != null){
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(full_vid_url));
            request.setDescription("Some descrition");
            request.setTitle("Some title");

        } else {
            Timber.e("Url to vid is null");
        }
    }
}
