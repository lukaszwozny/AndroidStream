package put.poznan.pl.androidstream.screens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import put.poznan.pl.androidstream.R;
import put.poznan.pl.androidstream.api.StreamApi;
import put.poznan.pl.androidstream.app.AppController;

public class StreamFragment extends Fragment {

    StreamApi api;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stream, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        injectDependencies();
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Stream :)");
    }

    private void injectDependencies() {
        api = AppController.getAppComponent().streamApi();
    }
}
