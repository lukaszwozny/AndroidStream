package put.poznan.pl.androidstream.screens.opengl.dagger;

import dagger.Module;
import dagger.Provides;
import put.poznan.pl.androidstream.screens.opengl.DemoRenderer;
import put.poznan.pl.androidstream.screens.opengl.MyGLRenderer;
import put.poznan.pl.androidstream.screens.opengl.OpenGLFragment;

@Module
public class OpenGLModule {

    private OpenGLFragment context;

    public OpenGLModule(OpenGLFragment context) {
        this.context = context;
    }

    @OpenGLScope
    @Provides
    MyGLRenderer provideMyGLRenderer(){
        return new MyGLRenderer();
    }

    @OpenGLScope
    @Provides
    DemoRenderer provideDemoRenderer(){
        return new DemoRenderer();
    }
}
