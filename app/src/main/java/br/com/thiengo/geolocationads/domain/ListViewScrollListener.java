package br.com.thiengo.geolocationads.domain;

import android.widget.AbsListView;

import java.util.List;

import br.com.thiengo.geolocationads.MainActivity;


public class ListViewScrollListener implements AbsListView.OnScrollListener {
    private MainActivity activity;
    private GamesAdapter adapter;
    private List<Game> games;

    private int visibleThreshold = 1;
    private int previousTotal = 0;
    private boolean loading = true;


    public ListViewScrollListener( MainActivity activity, GamesAdapter adapter, List<Game> games ){
        this.activity = activity;
        this.adapter = adapter;
        this.games = games;
    }

    @Override
    public void onScroll(
            AbsListView absListView,
            int firstVisibleItem,
            int visibleItemCount,
            int totalItemCount) {

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }


        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {

            activity.loadProgressBar(true);

            new LoadDataAsyncTask().execute(
                    activity,
                    adapter,
                    games );
            loading = true;
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {}
}
