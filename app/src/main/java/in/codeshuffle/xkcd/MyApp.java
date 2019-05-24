package in.codeshuffle.xkcd;

import android.app.Application;

import in.codeshuffle.xkcd.di.component.ApplicationComponent;
import in.codeshuffle.xkcd.di.component.DaggerApplicationComponent;
import in.codeshuffle.xkcd.di.module.ApplicationModule;
import in.codeshuffle.xkcd.util.AppLogger;

public class MyApp extends Application {

    private ApplicationComponent mApplicationComponent;

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //DI
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        mApplicationComponent.inject(this);


        //Logger
        AppLogger.init();
    }
}
