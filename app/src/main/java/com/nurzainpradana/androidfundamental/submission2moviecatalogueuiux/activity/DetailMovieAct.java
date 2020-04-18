package com.nurzainpradana.androidfundamental.submission2moviecatalogueuiux.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.jgabrielfreitas.core.BlurImageView;
import com.nurzainpradana.androidfundamental.submission2moviecatalogueuiux.R;
import com.nurzainpradana.androidfundamental.submission2moviecatalogueuiux.model.Movie;

public class DetailMovieAct extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";

    TextView tvDetailMovieTitle, tvDetailMovieGenre, tvDetailMovieDescription, tvDetailMovieYear;
    ImageView ivPoster;
    BlurImageView ivBlurPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        tvDetailMovieTitle = findViewById(R.id.tv_title_movie_detail);
        tvDetailMovieDescription = findViewById(R.id.tv_description_movie_detail);
        tvDetailMovieGenre = findViewById(R.id.tv_genre_movie_detail);
        tvDetailMovieYear = findViewById(R.id.tv_year_movie_detail);
        ivPoster = findViewById(R.id.img_poster_movie_detail);
        ivBlurPoster = findViewById(R.id.img_poster_blur_movie_detail);


        //Movie movie = getIntent().getExtras();
        //tvDetailMovieTitle.setText(movieTitle);

        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        tvDetailMovieTitle.setText(movie.getMovieTitle());
        tvDetailMovieYear.setText(movie.getMovieYear());
        tvDetailMovieDescription.setText(movie.getMovieDescription());
        tvDetailMovieGenre.setText(movie.getMovieGenre());

        ivPoster.setImageResource(movie.getMoviePoster());
        ivBlurPoster.setImageResource(movie.getMoviePoster());
        ivBlurPoster.setBlur(3);

        String movieText = getString(R.string.title_movies);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(movieText + " - " + movie.getMovieTitle().toUpperCase());

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
