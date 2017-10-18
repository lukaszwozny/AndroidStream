package put.poznan.pl.androidstream.screens.opengl.dagger;

import dagger.Component;
import put.poznan.pl.androidstream.app.dagger.AppComponent;
import put.poznan.pl.androidstream.screens.opengl.DemoRenderer;
import put.poznan.pl.androidstream.screens.opengl.MyGLRenderer;

@OpenGLScope
@Component(dependencies = {AppComponent.class}, modules = {OpenGLModule.class})
public interface OpenGLComponent {
    MyGLRenderer renderer();
    DemoRenderer demoRenderer();
}
