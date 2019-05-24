package in.codeshuffle.xkcd.data.network;

import in.codeshuffle.xkcd.data.network.model.ComicResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiHelper {

    @GET("info.0.json")
    Observable<ComicResponse> getComicOfToday();

    @GET("{comicId}/info.0.json")
    Observable<ComicResponse> getComicOfSpecificDay(
            @Path("comicId") long comicId);
}
