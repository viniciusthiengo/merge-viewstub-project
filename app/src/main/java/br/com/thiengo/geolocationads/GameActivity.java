package br.com.thiengo.geolocationads;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.thiengo.geolocationads.domain.Game;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ImageView imgHome = (ImageView) findViewById(R.id.img_home);
        ImageView imgVisitant = (ImageView) findViewById(R.id.img_visitant);
        TextView goalsHome = (TextView) findViewById(R.id.goals_home);
        TextView goalsVisitant = (TextView) findViewById(R.id.goals_visitan);

        if( getIntent() != null
                && getIntent().getParcelableExtra("game") != null ){

            Game game = (Game) getIntent().getParcelableExtra("game");

            imgHome.setImageResource( game.getSoccerTeamHome().getImage() );
            imgHome.setContentDescription( game.getSoccerTeamHome().getName() );
            imgVisitant.setImageResource( game.getSoccerTeamVisitant().getImage() );
            imgVisitant.setContentDescription( game.getSoccerTeamVisitant().getName() );
            goalsHome.setText( String.valueOf( game.getHomeGoals() ) );
            goalsVisitant.setText( String.valueOf( game.getVisitantGoals() ) );
        }
        else{
            finish();
        }
    }
}
