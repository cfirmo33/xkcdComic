package in.codeshuffle.xkcd.data;

import in.codeshuffle.xkcd.data.db.DbHelper;
import in.codeshuffle.xkcd.data.network.ApiHelper;
import in.codeshuffle.xkcd.data.pref.PrefHelper;


public interface DataManager extends DbHelper, PrefHelper, ApiHelper {
}
