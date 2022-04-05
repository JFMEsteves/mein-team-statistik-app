package de.fhswf.statistics;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import java.util.ArrayList;
import java.util.List;

import de.fhswf.statistics.api.service.SpielerService;
import de.fhswf.statistics.list.ListAdapter;
import de.fhswf.statistics.list.item.EndaddGameListItem;
import de.fhswf.statistics.list.item.SpielListItem;
import de.fhswf.statistics.list.item.SpielercardItem;
import de.fhswf.statistics.model.Spiel;
import de.fhswf.statistics.model.Spieler;

public class newGameActivity extends AppCompatActivity implements EndaddGameListItem.OnEndClickListener {
    private boolean busy;
    private SpielerService spielerService;
    private LinearLayoutManager layoutManager;
    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Intent stuff
        setContentView(R.layout.add_game_view);
        RecyclerView container = findViewById(R.id.add_player_container);

        layoutManager = new LinearLayoutManager(
                this, RecyclerView.HORIZONTAL, false);
        container.setLayoutManager(layoutManager);


        //Set Adapter for Recyclerview
        adapter = new ListAdapter();
        container.setAdapter(adapter);

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(container);

        //Testing mit manuellen Daten
        ArrayList<Spieler> spielerlist = new ArrayList<>();
        spielerlist.add(new Spieler(1, "Marcel"));
        spielerlist.add(new Spieler(2, "Joey"));
        spielerlist.add(new Spieler(3, "Tyron"));
        spielerlist.add(new Spieler(4, "Paul"));
        addSpielercardToList(spielerlist);
        // this.spielerService = new MockService(false);
        // this.busy=false;

        //TODO Liste von Spielern hier annehmen und weiterverarbeiten. / Liste von SPielern hier sammeln
    }

    private void addSpielercardToList(List<Spieler> result) {
        this.busy = false;

        //Adapter säubern
        adapter.clear();

        //TODO Spielcard hinzufügen
        adapter.add(new SpielListItem());

        // Liste dem Adapter hinzufügen
        for (Spieler c : result) {
            adapter.add(new SpielercardItem(c));
        }
        //End-Card einfügen
        adapter.add(new EndaddGameListItem().setOnEndListener(this));
    }

    @Override
    public void onEndButtonClick(@NonNull EndaddGameListItem item) {
        //TODO API Kommunikation hier
    }
}
