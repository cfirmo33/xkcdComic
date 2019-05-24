package in.codeshuffle.xkcd.ui.base;

import javax.inject.Inject;

import in.codeshuffle.xkcd.data.DataManager;

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private V mMvpView;

    private DataManager dataManager;

    @Inject
    public BasePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    @Override
    public void onAttach(V mvpView) {
        this.mMvpView = mvpView;
    }

    @Override
    public void onDetach() {
        mMvpView = null;
    }

    public V getMvpView() {
        return mMvpView;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    @Override
    public void handleApiError() {

    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.onAttach(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
