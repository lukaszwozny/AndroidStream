package put.poznan.pl.androidstream.screens.stream.core;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
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

    public void getVideo1() {
        getVideoUrl(model.VIDEO_1);
    }

    public void getVideo2() {
        getVideoUrl(model.VIDEO_2);
    }

    public void getVideo3() {
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

    public void useDownloadManager(DownloadManager manager) {
        if (full_vid_url != null) {
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(full_vid_url));
            request.setDescription("Some descrition");
            request.setTitle("Some title");
            // in order for this if to run, you must use the android 3.2 to compile your app
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            }
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "video.mp4");

            manager.enqueue(request);
        } else {
            Timber.e("Url to vid is null");
        }
    }
}
