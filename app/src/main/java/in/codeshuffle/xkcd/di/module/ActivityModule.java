package in.codeshuffle.xkcd.di.module;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import dagger.Module;
import dagger.Provides;
import in.codeshuffle.xkcd.di.annotation.PerActivity;
import in.codeshuffle.xkcd.ui.main.MainMvpPresenter;
import in.codeshuffle.xkcd.ui.main.MainMvpView;
import in.codeshuffle.xkcd.ui.main.MainPresenter;
import in.codeshuffle.xkcd.ui.splash.SplashMvpPresenter;
import in.codeshuffle.xkcd.ui.splash.SplashMvpView;
import in.codeshuffle.xkcd.ui.splash.SplashPresenter;

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity mActivity) {
        this.mActivity = mActivity;
    }

    @PerActivity
    @Provides
    MainMvpPresenter<MainMvpView> providesMainPresenter(
            MainPresenter<MainMvpView> mainPresenter) {
        return mainPresenter;
    }

    @PerActivity
    @Provides
    SplashMvpPresenter<SplashMvpView> providesSplashMvpPresenter(
            SplashPresenter<SplashMvpView> splashPresenter) {
        return splashPresenter;
    }

    @PerActivity
    @Provides
    FragmentManager providesFragmentManager() {
        return mActivity.getSupportFragmentManager();
    }

}
