package com.nurzainpradana.androidfundamental.submission2moviecatalogueuiux.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.jgabrielfreitas.core.BlurImageView;
import com.nurzainpradana.androidfundamental.submission2moviecatalogueuiux.R;
import com.nurzainpradana.androidfundamental.submission2moviecatalogueuiux.model.TvShow;

public class DetailTvShowAct extends AppCompatActivity {
    public static final String EXTRA_TV_SHOW = "extra_tv_show";

    TextView tvDetailTvShowTitle, tvDetailTvShowGenre, tvDetailTvShowDescription, tvDetailTvShowYear;
    ImageView ivPoster;
    BlurImageView ivBlurPoster;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv_show);

        tvDetailTvShowTitle = findViewById(R.id.tv_title_tv_show_detail);
        tvDetailTvShowDescription = findViewById(R.id.tv_description_tv_show_detail);
        tvDetailTvShowGenre = findViewById(R.id.tv_genre_tv_show_detail);
        tvDetailTvShowYear = findViewById(R.id.tv_year_tv_show_detail);
        ivPoster = findViewById(R.id.img_poster_tv_show_detail);
        ivBlurPoster = findViewById(R.id.img_poster_blur_tv_show_detail);


        //TvShow TvShow = getIntent().getExtras();
        //tvDetailTvShowTitle.setText(TvShowTitle);

        TvShow TvShow = getIntent().getParcelableExtra(EXTRA_TV_SHOW);
        tvDetailTvShowTitle.setText(TvShow.getTvShowTitle());
        tvDetailTvShowYear.setText(TvShow.getTvShowYear());
        tvDetailTvShowDescription.setText(TvShow.getTvShowDescription());
        tvDetailTvShowGenre.setText(TvShow.getTvShowGenre());

        ivPoster.setImageResource(TvShow.getTvShowPoster());
        ivBlurPoster.setImageResource(TvShow.getTvShowPoster());
        ivBlurPoster.setBlur(3);

        String tvShowText = getString(R.string.title_tvshow);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(tvShowText + " - " + TvShow.getTvShowTitle().toUpperCase());
    }
}
