package put.poznan.pl.androidstream.screens.texture;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import put.poznan.pl.androidstream.R;
import put.poznan.pl.androidstream.screens.opengl.MyGLSurfaceView;
import timber.log.Timber;

public class TextureActivity extends AppCompatActivity {

    // Our OpenGL Surfaceview
    private GLSurfaceView glSurfaceView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // Turn off the window's title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Super
        super.onCreate(savedInstanceState);

        // Fullscreen mode
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // We create our Surfaceview for our OpenGL here.
        glSurfaceView = new GLSurf(this);

        // Set our view.
        setContentView(R.layout.activity_texture);

        // Retrieve our Relative layout from our main layout we just set to our view.
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.texture_layout);

        // Attach our surfaceview to our relative layout from our main layout.
        RelativeLayout.LayoutParams glParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        layout.addView(glSurfaceView, glParams);
    }

    @Override
    protected void onPause() {
        super.onPause();
        glSurfaceView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        glSurfaceView.onResume();
    }
}
