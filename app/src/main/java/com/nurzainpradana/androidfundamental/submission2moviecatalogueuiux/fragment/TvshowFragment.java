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
import com.nurzainpradana.androidfundamental.submission2moviecatalogueuiux.activity.DetailTvShowAct;
import com.nurzainpradana.androidfundamental.submission2moviecatalogueuiux.adapter.CardViewTvShowAdapter;
import com.nurzainpradana.androidfundamental.submission2moviecatalogueuiux.model.TvShow;

import java.util.ArrayList;

public class TvshowFragment extends Fragment {

    private CardViewTvShowAdapter cardViewTvShowAdapter;
    private RecyclerView rvTvShow;
    private ArrayList<TvShow> listTvShow = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tvshow, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvTvShow = view.findViewById(R.id.rv_tvshow);
        rvTvShow.setHasFixedSize(true);

        listTvShow.addAll(getListTvShow());
        showRecyclerCardView();
    }



    public ArrayList<TvShow> getListTvShow() {
        String[] dataTvShowTitle = getResources().getStringArray(R.array.data_tvShow_title);
        String[] dataTvShowYear = getResources().getStringArray(R.array.data_tvShow_year);
        String[] dataTvShowGenre = getResources().getStringArray(R.array.data_tvShow_genre);
        String[] dataTvSHowDescription = getResources().getStringArray(R.array.data_tvShow_description);
        TypedArray dataTvShowPoster = getResources().obtainTypedArray(R.array.data_tvShow_poster);

        ArrayList<TvShow> listTvShow = new ArrayList<>();
        for (int i = 0; i < dataTvShowTitle.length; i++) {
            TvShow tvShow = new TvShow();
            tvShow.setTvShowTitle(dataTvShowTitle[i]);
            tvShow.setTvShowDescription(dataTvSHowDescription[i]);
            tvShow.setTvShowGenre(dataTvShowGenre[i]);
            tvShow.setTvShowYear(dataTvShowYear[i]);
            tvShow.setTvShowPoster(dataTvShowPoster.getResourceId(i, -1));

            listTvShow.add(tvShow);
        }
        return listTvShow;
    }

    private void showRecyclerCardView() {
        rvTvShow.setLayoutManager(new LinearLayoutManager(getContext()));
        CardViewTvShowAdapter cardViewTvShowAdapter = new CardViewTvShowAdapter(listTvShow);
        rvTvShow.setAdapter(cardViewTvShowAdapter);


        cardViewTvShowAdapter.setOnItemClickCallback(new CardViewTvShowAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(TvShow data) {
                showSelectedTvShow(data);

                Intent gotoDetailTvShow = new Intent(getContext(), DetailTvShowAct.class);
                TvShow mTvShow = new TvShow();
                mTvShow.setTvShowTitle(data.getTvShowTitle());
                mTvShow.setTvShowPoster(data.getTvShowPoster());
                mTvShow.setTvShowYear(data.getTvShowYear());
                mTvShow.setTvShowGenre(data.getTvShowGenre());
                mTvShow.setTvShowDescription(data.getTvShowDescription());
                gotoDetailTvShow.putExtra(DetailTvShowAct.EXTRA_TV_SHOW, mTvShow);
                startActivity(gotoDetailTvShow);
            }
        });
    }

    public void showSelectedTvShow(TvShow tvShow) {
        Toast.makeText(getContext(), "Kamu memilih " + tvShow.getTvShowTitle(), Toast.LENGTH_SHORT).show();
    }
}
