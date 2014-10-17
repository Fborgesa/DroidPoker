package br.droidpoker.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import br.droidpoker.R;
import br.droidpoker.core.GameCntrllr;
import br.droidpoker.core.GameView;
import br.droidpoker.domain.Jogador;
import br.droidpoker.domain.Mesa;


public class Droidpoker extends GameView {

    ListView listView;
    List<String> gameActionsList;
    ArrayAdapter<String> adapter;
    GameCntrllr gameController;

    public Droidpoker() {
        super(Mesa.getInstance());
        gameController = GameCntrllr.getInstance();
        gameController.setGameView(this);
    }

    private void novoJogo () {

        String[] nomJogadores = {"John Snow", "Tyrion Lannister", "Daenerys Targaryen"};
        gameController.iniciarNovoJogo(nomJogadores, 10, 1000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // remove window title
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); // make window fullscreen
        setContentView(R.layout.activity_droidpoker); // set activity_droidpoker as the Content View
        if (savedInstanceState != null) {
            gameActionsList = new ArrayList<String>();
            gameActionsList.addAll(Arrays.asList(savedInstanceState.getStringArray("dpoker")));
            if (gameActionsList != null) {
                adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, gameActionsList);
            }
            if (GameCntrllr.DEBUG_MODE) Log.d(GameCntrllr.DEBUG_TAG, gameActionsList.toString());
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

        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                updateGameActionsList("Novo Jogo " + c.getTime().toString());
                button.setEnabled(false);
                button.setVisibility(View.INVISIBLE);
                novoJogo();
            }
        });
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

    public void update() {
        this.updateGameActionsList(Mesa.getInstance().getLastAction());
    }

    @Override
    public void getPlayerAction() {
        String[] items = {"CHECK", "RAISE", "FOLD"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(Mesa.getInstance().getButtonPlayer().toString())
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        gameController.doAction(Jogador.PlayerActions.values()[whichButton]);
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
