package in.codeshuffle.xkcd.ui.main;

import in.codeshuffle.xkcd.di.annotation.PerActivity;
import in.codeshuffle.xkcd.ui.base.MvpPresenter;
import in.codeshuffle.xkcd.ui.base.MvpView;

@PerActivity
public interface MainMvpPresenter<V extends MvpView> extends MvpPresenter<V> {
    void fetchComicOfDay(long comicId);

    void fetchComicOfToday();
}
