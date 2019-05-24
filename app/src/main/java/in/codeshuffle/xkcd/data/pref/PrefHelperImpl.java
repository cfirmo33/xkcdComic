package in.codeshuffle.xkcd.data.pref;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import in.codeshuffle.xkcd.di.annotation.ApplicationContext;
import in.codeshuffle.xkcd.di.annotation.PreferenceInfo;

public class PrefHelperImpl implements PrefHelper {

    private static final String ID_COMIC_TODAY = "id_comic_today";
    private static final String ID_COMIC_CURRENT = "id_comic_current";

    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mEditor;

    @Inject
    public PrefHelperImpl(@ApplicationContext Context context,
                          @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public void setTodaysComicId(Long comicId) {
        mEditor = mPrefs.edit();
        mEditor.putLong(ID_COMIC_TODAY, comicId);
        mEditor.apply();
    }

    @Override
    public long getTodayComicId() {
        return mPrefs.getLong(ID_COMIC_TODAY, 0);
    }

    @Override
    public long getCurrentComicId() {
        return mPrefs.getLong(ID_COMIC_CURRENT, 0);
    }

    @Override
    public void setCurrentComicId(Long comicId) {
        mEditor = mPrefs.edit();
        mEditor.putLong(ID_COMIC_CURRENT, comicId);
        mEditor.apply();
    }
}
