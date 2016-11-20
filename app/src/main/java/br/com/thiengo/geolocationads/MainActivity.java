package br.com.thiengo.geolocationads;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.thiengo.geolocationads.domain.Game;
import br.com.thiengo.geolocationads.domain.GamesAdapter;
import br.com.thiengo.geolocationads.domain.ListViewScrollListener;
import br.com.thiengo.geolocationads.domain.SoccerTeam;



public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private List<Game> games;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initResults();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




    private void initResults(){

        ListView listView = (ListView) findViewById(R.id.list_view);
        games = resultsGames();

        listView.setAdapter( new GamesAdapter(this, games) );
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                intent.putExtra( "game", games.get(position) );
                startActivity(intent);
            }
        });

        /* SCROLL PARA CARREGAR MAIS ITENS NO LISTVIEW */
        ListViewScrollListener scrollListener = new ListViewScrollListener(
                                                            this,
                                                            (GamesAdapter) listView.getAdapter(),
                                                            games );
        listView.setOnScrollListener( scrollListener );
    }

    public List<Game> resultsGames(){
        List<Game> games = new ArrayList<>();
        games.add( new Game( new SoccerTeam(R.drawable.ec_flamengo, "Flamengo"), new SoccerTeam(R.drawable.ec_santa_cruz, "Santa Cruz"), 3, 0) );
        games.add( new Game( new SoccerTeam(R.drawable.ec_cruzeiro, "Cruzeiro"), new SoccerTeam(R.drawable.ec_ponte_preta, "Ponte Preta"), 2, 0) );
        games.add( new Game( new SoccerTeam(R.drawable.ec_figueirense, "Figuerense"), new SoccerTeam(R.drawable.ec_botafogo, "Botafogo"), 0, 1) );
        games.add( new Game( new SoccerTeam(R.drawable.ec_corinthians, "Corinthians"), new SoccerTeam(R.drawable.ec_atletico_mg, "Atlético MG"), 0, 0) );
        games.add( new Game( new SoccerTeam(R.drawable.ec_sport_recife, "Sport Recife"), new SoccerTeam(R.drawable.ec_sao_paulo, "São Paulo"), 0, 0) );
        games.add( new Game( new SoccerTeam(R.drawable.ec_santos, "Santos"), new SoccerTeam(R.drawable.ec_fluminense, "Fluminense"), 2, 1) );
        games.add( new Game( new SoccerTeam(R.drawable.ec_internacional, "Internacional"), new SoccerTeam(R.drawable.ec_coritiba, "Coritiba"), 1, 0) );
        games.add( new Game( new SoccerTeam(R.drawable.ec_america_mg, "América MG"), new SoccerTeam(R.drawable.ec_palmeiras, "Palmeiras"), 0, 2) );
        return games;
    }

    public void loadProgressBar(boolean status){
        try{
            int visibility = status ? View.VISIBLE : View.GONE;
            findViewById(R.id.progress_bar).setVisibility(visibility);
        }
        catch(Exception e){
            ((ViewStub) findViewById(R.id.vs_progress_bar)).inflate();
        }
    }
}

