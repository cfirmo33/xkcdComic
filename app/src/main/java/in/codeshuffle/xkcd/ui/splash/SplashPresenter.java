package in.codeshuffle.xkcd.ui.splash;

import android.os.Handler;

import javax.inject.Inject;

import in.codeshuffle.xkcd.data.DataManager;
import in.codeshuffle.xkcd.ui.base.BasePresenter;

public class SplashPresenter<V extends SplashMvpView> extends BasePresenter<V>
        implements SplashMvpPresenter<V> {


    private static final long MILLIS_SPLASH_DELAY = 2000;

    @Inject
    public SplashPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
        new Handler().postDelayed(() ->
                getMvpView().openMainActivity(), MILLIS_SPLASH_DELAY);
    }
}
