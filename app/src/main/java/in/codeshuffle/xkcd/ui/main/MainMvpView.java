package in.codeshuffle.xkcd.ui.main;

import in.codeshuffle.xkcd.data.network.model.ComicResponse;
import in.codeshuffle.xkcd.ui.base.MvpView;

public interface MainMvpView extends MvpView {
    void onComicForToday(ComicResponse comicResponse);

    void onComicForDay(long comicId, ComicResponse comicResponse);
}
