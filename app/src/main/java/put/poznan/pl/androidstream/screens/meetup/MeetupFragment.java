package put.poznan.pl.androidstream.screens.meetup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.hannesdorfmann.mosby3.mvp.MvpFragment;
import put.poznan.pl.androidstream.R;
import put.poznan.pl.androidstream.app.AppController;
import put.poznan.pl.androidstream.screens.meetup.core.MeetupPresenter;
import put.poznan.pl.androidstream.screens.meetup.core.MeetupView;
import put.poznan.pl.androidstream.screens.meetup.dagger.DaggerMeetupComponent;
import put.poznan.pl.androidstream.screens.meetup.dagger.MeetupComponent;
import put.poznan.pl.androidstream.screens.meetup.dagger.MeetupModule;

import java.util.ArrayList;
import java.util.List;

public class MeetupFragment extends MvpFragment<MeetupView, MeetupPresenter>
        implements MeetupView {

    @BindView(R.id.text_item)
    TextView textView;

    private Unbinder unbinder;

    List<String> messeges = new ArrayList<>();

    private MeetupComponent component;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_meetup, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        injectDependencies();
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, getActivity());
    }

    private void injectDependencies() {
        component = DaggerMeetupComponent.builder()
                .appComponent(AppController.getAppComponent())
                .meetupModule(new MeetupModule(this))
                .build();
    }

    @Override
    public MeetupPresenter createPresenter() {
        return component.presenter();
    }

    @Override
    public void setStreamText(String text) {
        textView.setText(text);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        component =null;
    }

    @OnClick(R.id.listen)
    void listenClick() {
        presenter.listen();
    }

    @OnClick(R.id.enough)
    void enoughClick() {
        presenter.stopListen();
    }
}
