package in.codeshuffle.xkcd.di.component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import in.codeshuffle.xkcd.MyApp;
import in.codeshuffle.xkcd.data.DataManager;
import in.codeshuffle.xkcd.data.db.DbHelper;
import in.codeshuffle.xkcd.data.network.ApiHelper;
import in.codeshuffle.xkcd.data.pref.PrefHelper;
import in.codeshuffle.xkcd.di.annotation.ApplicationContext;
import in.codeshuffle.xkcd.di.module.ApplicationModule;
import in.codeshuffle.xkcd.di.module.NetworkModule;

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {

    void inject(MyApp myApp);

    @ApplicationContext
    Context appContext();

    MyApp application();

    DataManager dataManager();

    PrefHelper prefHelper();

    ApiHelper apiHelper();

    DbHelper dbHelper();
}
