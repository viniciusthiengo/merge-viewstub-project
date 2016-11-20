package br.com.thiengo.geolocationads.domain;

import android.os.AsyncTask;
import android.os.SystemClock;

import java.util.List;

import br.com.thiengo.geolocationads.MainActivity;


public class LoadDataAsyncTask extends AsyncTask<Object, Void, Void> {
    private MainActivity activity;
    private GamesAdapter adapter;

    @Override
    protected Void doInBackground(Object... objects) {
        activity = (MainActivity) objects[0];
        adapter = (GamesAdapter) objects[1];
        List<Game> games = (List<Game>) objects[2];

        games.addAll( activity.resultsGames() );

        SystemClock.sleep(3000);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        activity.loadProgressBar(false);
        adapter.notifyDataSetChanged();
    }
}
