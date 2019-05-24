package in.codeshuffle.xkcd.data;

import android.content.Context;

import javax.inject.Inject;

import in.codeshuffle.xkcd.data.db.DbHelper;
import in.codeshuffle.xkcd.data.network.ApiHelper;
import in.codeshuffle.xkcd.data.network.model.ComicResponse;
import in.codeshuffle.xkcd.data.pref.PrefHelper;
import in.codeshuffle.xkcd.di.annotation.ApplicationContext;
import io.reactivex.Observable;

public class DataManagerImpl implements DataManager {

    private final Context mContext;
    private final DbHelper mDbHelper;
    private final PrefHelper mPrefHelper;
    private final ApiHelper mApiHelper;

    @Inject
    public DataManagerImpl(@ApplicationContext Context context,
                          DbHelper dbHelper,
                          PrefHelper prefHelper,
                          ApiHelper apiHelper) {
        mContext = context;
        mDbHelper = dbHelper;
        mPrefHelper = prefHelper;
        mApiHelper = apiHelper;
    }

    @Override
    public Observable<ComicResponse> getComicOfToday() {
        return mApiHelper.getComicOfToday();
    }

    @Override
    public Observable<ComicResponse> getComicOfSpecificDay(long comicId) {
        return mApiHelper.getComicOfSpecificDay(comicId);
    }

    @Override
    public void setTodaysComicId(Long comicId) {
        mPrefHelper.setTodaysComicId(comicId);
    }

    @Override
    public long getTodayComicId() {
        return mPrefHelper.getTodayComicId();
    }

    @Override
    public void setCurrentComicId(Long comicId) {
        mPrefHelper.setCurrentComicId(comicId);
    }

    @Override
    public long getCurrentComicId() {
        return mPrefHelper.getCurrentComicId();
    }
}
