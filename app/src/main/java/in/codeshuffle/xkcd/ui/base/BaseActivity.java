package in.codeshuffle.xkcd.ui.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;

import butterknife.Unbinder;
import in.codeshuffle.xkcd.MyApp;
import in.codeshuffle.xkcd.R;
import in.codeshuffle.xkcd.di.component.ActivityComponent;
import in.codeshuffle.xkcd.di.component.DaggerActivityComponent;
import in.codeshuffle.xkcd.di.module.ActivityModule;
import in.codeshuffle.xkcd.util.CommonUtils;
import in.codeshuffle.xkcd.util.KeyboardUtils;
import in.codeshuffle.xkcd.util.NetworkUtils;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public abstract class BaseActivity extends AppCompatActivity implements MvpView,
        BaseFragment.Callback {

    private Unbinder mUnBinder;

    private ActivityComponent mActivityComponent;

    private ProgressDialog mProgressDialog;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivityComponent = DaggerActivityComponent.builder()
                .applicationComponent(((MyApp) getApplication()).getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();

    }

    protected abstract void setUp();

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this);
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    public void onError(int errorStrResId) {
        onError(getString(errorStrResId));
    }

    @Override
    public void onError(String errorMsg) {
        if (errorMsg != null) {
            showSnackBar(errorMsg);
        } else {
            showSnackBar(getString(R.string.something_went_wrong));
        }
    }

    @Override
    public void showShortToast(String message) {
        CommonUtils.showShortToast(this, message);
    }

    @Override
    public void showShortToast(int msgResId) {
        CommonUtils.showShortToast(this, getString(msgResId));
    }

    @Override
    public void showLongToast(String message) {
        CommonUtils.showLongToast(this, message);
    }

    @Override
    public void showLongToast(int msgResId) {
        CommonUtils.showLongToast(this, getString(msgResId));
    }

    @Override
    public void showSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = sbView
                .findViewById(com.google.android.material.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.white));
        snackbar.show();
    }

    @Override
    public void showSnackBar(int msgResId) {
        showSnackBar(getString(msgResId));
    }

    @Override
    public boolean isNetWorkConnected() {
        return NetworkUtils.isNetworkConnected(this);
    }

    @Override
    public void hideKeyboard() {
        KeyboardUtils.hideSoftInput(this);
    }

    @Override
    protected void onDestroy() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroy();
    }
}
