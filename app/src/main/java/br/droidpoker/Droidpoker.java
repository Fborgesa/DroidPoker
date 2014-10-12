package br.droidpoker;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import br.droidpoker.sistema.Jogo;


public class Droidpoker extends Activity {

    ListView listView;
    List<String> gameActionsList;
    ArrayAdapter<String> adapter;
    Jogo droidPoker;

    public void novoJogo () {
        this.droidPoker = Jogo.getInstance(this);
        droidPoker.setRunning(true);
        String[] nomJogadores = {"John Snow", "Tyrion Lannister", "Daenerys Targaryen"};
        droidPoker.iniciarNovoJogo(nomJogadores, 5, 1000);
        //droidPoker.gameLoop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove window title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // make window fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // set activity_droidpoker as the view
        setContentView(R.layout.activity_droidpoker);
        if (savedInstanceState != null) {
            gameActionsList = new ArrayList<String>();
            gameActionsList.addAll(Arrays.asList(savedInstanceState.getStringArray("dpoker")));
            if (gameActionsList != null) {
                adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, gameActionsList);
            }
            if (Jogo.DEBUG_MODE) Log.d(Jogo.DEBUG_TAG, gameActionsList.toString());
            listView = (ListView) findViewById(R.id.game_actions);
            listView.setAdapter(adapter);
        }
        else {
            gameActionsList = new ArrayList<String>();
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, gameActionsList);
            listView = (ListView) findViewById(R.id.game_actions);
            listView.setAdapter(adapter);
            updateGameActionsList("Droidpoker v1.0 iniciado");
        }

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int seconds = c.get(Calendar.SECOND);
                updateGameActionsList("Button Pressed at " + seconds);

            }
        });
        this.novoJogo();
    }

    public void onSaveInstanceState(Bundle savedState) {
        super.onSaveInstanceState(savedState);
        List<String> savedActionsList;
        if (gameActionsList.size() > 10) {
             savedActionsList = gameActionsList.subList(gameActionsList.size()-10, gameActionsList.size());
        }
        else {
            savedActionsList = gameActionsList;
        }
        savedState.putStringArray("dpoker", savedActionsList.toArray(new String[0]));
    }

    public void updateGameActionsList(String actionString) {
        gameActionsList.add(actionString);
        adapter.notifyDataSetChanged();
        listView.smoothScrollToPosition(adapter.getCount());
    }

}
