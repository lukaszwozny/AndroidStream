package put.poznan.pl.androidstream.screens.opengl;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.*;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import put.poznan.pl.androidstream.R;
import put.poznan.pl.androidstream.app.AppController;
import put.poznan.pl.androidstream.screens.opengl.dagger.DaggerOpenGLComponent;
import put.poznan.pl.androidstream.screens.opengl.dagger.OpenGLComponent;
import put.poznan.pl.androidstream.screens.opengl.dagger.OpenGLModule;
import timber.log.Timber;

public class OpenGLFragment extends Fragment {
    @BindView(R.id.view_surface)
    GLSurfaceView surfaceView;

    private OpenGLComponent component;

    private MyGLRenderer renderer;
    private DemoRenderer demoRenderer;

    private Unbinder unbinder;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_opengl, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        injectDependencies();
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this,view);

        if (renderer != null){
            Timber.i("Not null :)");
        } else {
            Timber.i("Null :(");
        }

        surfaceView.setRenderer(demoRenderer);

        surfaceView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        getActivity().setTitle("OpenGL");
    }

    private void injectDependencies() {
        component = DaggerOpenGLComponent.builder()
                .appComponent(AppController.getAppComponent())
                .openGLModule(new OpenGLModule(this))
                .build();

        renderer = component.renderer();
        demoRenderer = component.demoRenderer();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
