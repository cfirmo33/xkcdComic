package in.codeshuffle.xkcd.data.network;

import javax.inject.Inject;

import in.codeshuffle.xkcd.data.network.model.ComicResponse;
import io.reactivex.Observable;

public class ApiHelperImpl implements ApiHelper{

    private ApiHelper apiHelper;

    @Inject
    ApiHelperImpl(ApiHelper apiHelper) {
        this.apiHelper = apiHelper;
    }

    @Override
    public Observable<ComicResponse> getComicOfSpecificDay(long comicId) {
        return null;
    }

    @Override
    public Observable<ComicResponse> getComicOfToday() {
        return null;
    }
}
