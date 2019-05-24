package in.codeshuffle.xkcd.ui.main;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.codeshuffle.xkcd.R;
import in.codeshuffle.xkcd.data.network.model.ComicResponse;
import in.codeshuffle.xkcd.data.pref.PrefHelper;
import in.codeshuffle.xkcd.ui.base.BaseActivity;
import in.codeshuffle.xkcd.ui.comicscreen.ComicFragment;

public class MainActivity extends BaseActivity implements MainMvpView {

    @BindView(R.id.contentContainer)
    View contentContainer;
    @BindView(R.id.loadingContainer)
    View loadingContainer;
    @BindView(R.id.comicContainer)
    View comicContainer;
    @BindView(R.id.controlContainer)
    View controlContainer;
    @BindView(R.id.prevBtn)
    View prevBtn;
    @BindView(R.id.nextBtn)
    View nextBtn;

    @Inject
    MainMvpPresenter<MainMvpView> mPresenter;
    @Inject
    PrefHelper prefHelper;
    @Inject
    FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUnBinder(ButterKnife.bind(this));

        getActivityComponent().inject(MainActivity.this);

        mPresenter.onAttach(MainActivity.this);

        setUp();
    }

    @Override
    protected void setUp() {
    }

    @Override
    public void showLoading() {
        super.showLoading();
        contentContainer.setVisibility(View.GONE);
        loadingContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        super.hideLoading();
        contentContainer.setVisibility(View.VISIBLE);
        loadingContainer.setVisibility(View.GONE);
    }

    @Override
    public void onComicForToday(ComicResponse comicResponse) {
        controlContainer.setVisibility(View.VISIBLE);

        prefHelper.setTodaysComicId(comicResponse.getNum());
        prefHelper.setCurrentComicId(comicResponse.getNum());

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.comicContainer,
                ComicFragment.getInstance(comicResponse));
        fragmentTransaction.commit();

        nextBtn.setEnabled(false);
    }

    @Override
    public void onComicForDay(long comicId, ComicResponse comicResponse) {

        prefHelper.setCurrentComicId(comicId);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.comicContainer,
                ComicFragment.getInstance(comicResponse));
        fragmentTransaction.commit();

        prevBtn.setEnabled(comicId != 1);
        nextBtn.setEnabled(comicId != prefHelper.getTodayComicId());
    }

    @OnClick({R.id.prevBtn, R.id.nextBtn})
    void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.prevBtn:
                mPresenter.fetchComicOfDay(prefHelper.getCurrentComicId() - 1);
                break;
            case R.id.nextBtn:
                mPresenter.fetchComicOfDay(prefHelper.getCurrentComicId() + 1);
                break;
        }
    }

}
