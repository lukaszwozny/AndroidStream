package put.poznan.pl.androidstream.screens.stream;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.VideoView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.hannesdorfmann.mosby3.mvp.MvpFragment;
import put.poznan.pl.androidstream.R;
import put.poznan.pl.androidstream.api.StreamApi;
import put.poznan.pl.androidstream.api.YoutubeApi;
import put.poznan.pl.androidstream.app.AppController;
import put.poznan.pl.androidstream.screens.meetup.MeetupActivity;
import put.poznan.pl.androidstream.screens.stream.core.StreamPresenter;
import put.poznan.pl.androidstream.screens.stream.core.StreamView;
import put.poznan.pl.androidstream.screens.stream.dagger.DaggerStreamComponent;
import put.poznan.pl.androidstream.screens.stream.dagger.StreamComponent;
import timber.log.Timber;

public class StreamFragment extends MvpFragment<StreamView, StreamPresenter>
        implements StreamView {

    @BindView(R.id.video_stream)
    VideoView videoView;

    private YoutubeApi api;
    private Unbinder unbinder;

    private StreamComponent component;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stream, container, false);
    }

    @Override
    public StreamPresenter createPresenter() {
        return component.presenter();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        injectDependencies();
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, getActivity());

        getActivity().setTitle("Stream :)");

        // add simple touch listener
        videoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(videoView.isPlaying()){
                    videoView.pause();
                }else {
                    videoView.start();
                }
                return false;
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        component = null;
    }

    @OnClick(R.id.button_get_video_1)
    void getVideo1() {
        presenter.getVideo1();
    }

    @OnClick(R.id.button_get_video_2)
    void getVideo2() {
        presenter.getVideo2();
    }

    @OnClick(R.id.button_get_video_3)
    void getVideo3() {
        presenter.getVideo3();
    }

    @Override
    public void playVideoFromUrl(String url) {
        if(!videoView.isPlaying()){
            videoView.start();
        }
        videoView.setVideoURI(Uri.parse(url));
    }

    private void injectDependencies() {
        component = DaggerStreamComponent.builder()
                .appComponent(AppController.getAppComponent())
                .build();
        api = AppController.getAppComponent().youtubeApi();
    }
}
