package in.codeshuffle.xkcd.data.db;

import javax.inject.Inject;

import in.codeshuffle.xkcd.data.db.model.DaoMaster;
import in.codeshuffle.xkcd.data.db.model.DaoSession;

public class DbHelperImpl implements DbHelper {
    private final DaoSession mDaoSession;

    @Inject
    public DbHelperImpl(DbOpenHelper dbOpenHelper) {
        mDaoSession = new DaoMaster(dbOpenHelper.getWritableDb()).newSession();
    }
}
