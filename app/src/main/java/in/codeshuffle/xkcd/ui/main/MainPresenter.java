package in.codeshuffle.xkcd.ui.main;

import android.util.Log;

import javax.inject.Inject;

import in.codeshuffle.xkcd.data.DataManager;
import in.codeshuffle.xkcd.data.network.model.ComicResponse;
import in.codeshuffle.xkcd.ui.base.BasePresenter;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V>
        implements MainMvpPresenter<V> {

    private static final String TAG = MainPresenter.class.getSimpleName();

    @Inject
    MainPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
        fetchComicOfToday();
    }

    @Override
    public void fetchComicOfDay(long comicId) {
        getMvpView().showLoading();
        getDataManager().getComicOfSpecificDay(comicId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ComicResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ComicResponse comicResponse) {
                        if (!isViewAttached())
                            return;

                        getMvpView().onComicForDay(comicId, comicResponse);
                        getMvpView().hideLoading();
                    }

                    @Override
                    public void onError(Throwable t) {
                        if (!isViewAttached())
                            return;

                        Log.d(TAG, "onError: " + t.getLocalizedMessage());
                        getMvpView().onError("Something went wrong");
                        getMvpView().hideLoading();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void fetchComicOfToday() {
        getMvpView().showLoading();
        getDataManager().getComicOfToday()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ComicResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ComicResponse comicResponse) {
                        if (!isViewAttached())
                            return;

                        getMvpView().onComicForToday(comicResponse);
                        getMvpView().hideLoading();
                    }

                    @Override
                    public void onError(Throwable t) {
                        if (!isViewAttached())
                            return;

                        Log.d(TAG, "onError: " + t.getLocalizedMessage());
                        getMvpView().onError("Something went wrong");
                        getMvpView().hideLoading();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
