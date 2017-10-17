package put.poznan.pl.androidstream.screens.stream.dagger;

import dagger.Component;
import put.poznan.pl.androidstream.app.dagger.AppComponent;

@StreamScope
@Component(dependencies = {AppComponent.class}, modules = {StreamModule.class})
public interface StreamComponent {
}
