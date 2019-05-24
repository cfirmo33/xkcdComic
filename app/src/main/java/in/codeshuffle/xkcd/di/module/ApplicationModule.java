package in.codeshuffle.xkcd.di.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import in.codeshuffle.xkcd.MyApp;
import in.codeshuffle.xkcd.R;
import in.codeshuffle.xkcd.data.DataManager;
import in.codeshuffle.xkcd.data.DataManagerImpl;
import in.codeshuffle.xkcd.data.db.DbHelper;
import in.codeshuffle.xkcd.data.db.DbHelperImpl;
import in.codeshuffle.xkcd.data.pref.PrefHelper;
import in.codeshuffle.xkcd.data.pref.PrefHelperImpl;
import in.codeshuffle.xkcd.di.annotation.ApplicationContext;
import in.codeshuffle.xkcd.di.annotation.DatabaseInfo;
import in.codeshuffle.xkcd.di.annotation.PreferenceInfo;
import in.codeshuffle.xkcd.util.AppConstants;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

@Module(includes = {NetworkModule.class})
public class ApplicationModule {

    private MyApp mApplication;

    public ApplicationModule(MyApp myApp){
        this.mApplication = myApp;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    MyApp provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseInfo() {
        return AppConstants.DB_NAME;
    }

    //Pref
    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(DbHelperImpl appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    PrefHelper providePreferencesHelper(PrefHelperImpl prefHelper) {
        return prefHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(DataManagerImpl dataManager) {
        return dataManager;
    }
}
