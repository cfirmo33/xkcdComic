package in.codeshuffle.xkcd.ui.comicscreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.codeshuffle.xkcd.R;
import in.codeshuffle.xkcd.data.network.model.ComicResponse;
import in.codeshuffle.xkcd.ui.base.BaseFragment;

public class ComicFragment extends BaseFragment {

    private static final String COMIC = "comic";

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.publishedOn)
    TextView publishedOn;
    @BindView(R.id.comicImgView)
    ImageView comicImgView;
    ComicResponse comicResponse;

    private String name;

    public static ComicFragment getInstance(ComicResponse comicResponse) {
        ComicFragment comicFragment = new ComicFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(COMIC, comicResponse);
        comicFragment.setArguments(bundle);
        return comicFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comic_screen, container, false);
        setUnbinder(ButterKnife.bind(this, view));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            comicResponse = (ComicResponse) args.getSerializable(COMIC);
            if (comicResponse == null) return;
            title.setText(comicResponse.getSafeTitle());
            publishedOn.setText(String.format("Published on: %s/%s/%s",
                    comicResponse.getDay(),
                    comicResponse.getMonth(),
                    comicResponse.getYear()));
            Glide.with(this)
                    .load(comicResponse.getImg())
                    .into(comicImgView);
        }
    }

    @OnClick(R.id.viewTranscript)
    void onViewTranscript() {
        if (comicResponse != null) {
            new AlertDialog.Builder(getmActivity())
                    .setIcon(R.mipmap.ic_launcher)
                    .setTitle(comicResponse.getSafeTitle())
                    .setMessage(comicResponse.getAlt().length() == 0 ?
                            getString(R.string.no_transcript_given)
                            : comicResponse.getAlt())
                    .setPositiveButton(getString(R.string.ok), ((dialog, which) -> {
                        dialog.dismiss();
                    }))
                    .create().show();
        }
    }

    @Override
    public void onDestroyView() {
        if (getUnbinder() != null)
            getUnbinder().unbind();
        super.onDestroyView();
    }
}
