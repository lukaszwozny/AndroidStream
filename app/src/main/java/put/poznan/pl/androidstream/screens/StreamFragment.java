package put.poznan.pl.androidstream.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import put.poznan.pl.androidstream.R;
import put.poznan.pl.androidstream.api.StreamApi;
import put.poznan.pl.androidstream.app.AppController;
import put.poznan.pl.androidstream.screens.meetup.MeetupActivity;

public class StreamFragment extends Fragment {

    StreamApi api;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stream, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        injectDependencies();
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this,getActivity());

        getActivity().setTitle("Stream :)");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick(R.id.button_goto_stream)
    void showStreamScreen(){
        Intent intent = new Intent(getActivity(), MeetupActivity.class);
        startActivity(intent);
    }

    private void injectDependencies() {
        api = AppController.getAppComponent().streamApi();
    }
}
