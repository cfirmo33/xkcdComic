package in.codeshuffle.xkcd.di.component;

import dagger.Component;
import in.codeshuffle.xkcd.di.annotation.PerActivity;
import in.codeshuffle.xkcd.di.module.ActivityModule;
import in.codeshuffle.xkcd.ui.main.MainActivity;
import in.codeshuffle.xkcd.ui.splash.SplashActivity;

@PerActivity
@Component(modules = {ActivityModule.class},
        dependencies = {ApplicationComponent.class})
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(SplashActivity splashActivity);
}
