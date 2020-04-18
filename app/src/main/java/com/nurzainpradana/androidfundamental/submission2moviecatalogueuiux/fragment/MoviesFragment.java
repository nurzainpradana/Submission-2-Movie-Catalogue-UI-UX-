package com.nurzainpradana.androidfundamental.submission2moviecatalogueuiux.fragment;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nurzainpradana.androidfundamental.submission2moviecatalogueuiux.R;
import com.nurzainpradana.androidfundamental.submission2moviecatalogueuiux.activity.DetailMovieAct;
import com.nurzainpradana.androidfundamental.submission2moviecatalogueuiux.adapter.CardViewMovieAdapter;
import com.nurzainpradana.androidfundamental.submission2moviecatalogueuiux.model.Movie;

import java.util.ArrayList;

public class MoviesFragment extends Fragment {

    private RecyclerView rvMovies;
    private ArrayList<Movie> listMovie = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_movies, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvMovies = view.findViewById(R.id.rv_movies);
        rvMovies.setHasFixedSize(true);

        listMovie.addAll(getListMovies());
        showRecyclerCardView();
    }

    public ArrayList<Movie> getListMovies() {
        String[] dataMovieTitle = getResources().getStringArray(R.array.data_movie_title);
        String[] dataMovieYear = getResources().getStringArray(R.array.data_movie_year);
        String[] dataMovieGenre = getResources().getStringArray(R.array.data_movie_genre);
        String[] dataMovieDescription = getResources().getStringArray(R.array.data_movie_description);
        TypedArray dataMoviePoster = getResources().obtainTypedArray(R.array.data_movie_poster);

        ArrayList<Movie> listMovie = new ArrayList<>();
        for (int i = 0; i < dataMovieTitle.length; i++) {
            Movie movie = new Movie();
            movie.setMovieTitle(dataMovieTitle[i]);
            movie.setMovieDescription(dataMovieDescription[i]);
            movie.setMovieGenre(dataMovieGenre[i]);
            movie.setMovieYear(dataMovieYear[i]);
            movie.setMoviePoster(dataMoviePoster.getResourceId(i,-1));

            listMovie.add(movie);
        }
        return listMovie;
    }

    private void showRecyclerCardView() {
        rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
        final CardViewMovieAdapter cardViewMovieAdapter = new CardViewMovieAdapter(listMovie);
        rvMovies.setAdapter(cardViewMovieAdapter);

        cardViewMovieAdapter.setOnItemClickCallback(new CardViewMovieAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Movie data) {
                showSelectedMovie(data);

                Intent gotoDetailMovie = new Intent(getContext(), DetailMovieAct.class);
                Movie mMovie = new Movie();
                mMovie.setMovieTitle(data.getMovieTitle());
                mMovie.setMoviePoster(data.getMoviePoster());
                mMovie.setMovieYear(data.getMovieYear());
                mMovie.setMovieGenre(data.getMovieGenre());
                mMovie.setMovieDescription(data.getMovieDescription());
                gotoDetailMovie.putExtra(DetailMovieAct.EXTRA_MOVIE, mMovie);
                startActivity(gotoDetailMovie);

            }
        });

    }
    public void showSelectedMovie(Movie movie){
        Toast.makeText(getContext(), "Kamu memilih " + movie.getMovieTitle(), Toast.LENGTH_SHORT).show();
    }
}
