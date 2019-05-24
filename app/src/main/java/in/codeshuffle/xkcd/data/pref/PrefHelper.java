package in.codeshuffle.xkcd.data.pref;

public interface PrefHelper {
    void setTodaysComicId(Long comicId);

    long getTodayComicId();

    void setCurrentComicId(Long comicId);

    long getCurrentComicId();
}
