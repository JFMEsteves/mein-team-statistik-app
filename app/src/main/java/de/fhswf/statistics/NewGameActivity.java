package de.fhswf.statistics;

import android.content.Intent;
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
import de.fhswf.statistics.list.Adapter.ListAdapter;
import de.fhswf.statistics.list.item.EndcardItem;
import de.fhswf.statistics.list.item.SpielcardItem;
import de.fhswf.statistics.list.item.SpielercardItem;
import de.fhswf.statistics.model.Spieler;

public class NewGameActivity extends AppCompatActivity implements EndcardItem.OnEndClickListener {
    public static final String EXTRA_SPIELER_IDLIST = "spieler_ids";
    
    private boolean busy;
    private ArrayList<Integer> spielerIds;
    private SpielerService spielerService;
    private LinearLayoutManager layoutManager;
    private ListAdapter adapter;
    private SpielerService SpielService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Intent stuff
        Intent mainActivityIntent = getIntent();
        this.spielerIds = mainActivityIntent.getIntegerArrayListExtra(EXTRA_SPIELER_IDLIST);

        if(spielerIds == null) {
            throw new RuntimeException("Keine Spieler ausgewählt!");
        }

        //Setting up View
        setContentView(R.layout.add_game_view);
        RecyclerView container = findViewById(R.id.add_player_container);

        layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        container.setLayoutManager(layoutManager);


        //Set Adapter for Recyclerview
        adapter = new ListAdapter();
        container.setAdapter(adapter);

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(container);

        this.spielerService = new MockService(false);
        this.busy = false;
        refreshContent();

    }
    private void refreshContent(){
        if(!busy){
            this.busy = true;
            spielerService.fetchSpielerList(
                    this::addSpielercardToList,
                    this::showErrorDialog
            );
        }
    }

    private void showErrorDialog(Throwable throwable) {
    }

    private void addSpielercardToList(@NonNull List<Spieler> result) {
        this.busy = false;

        //Adapter säubern
        adapter.clear();
        // Spielkarte hinzufügen
        adapter.add(new SpielcardItem());

        //Spieler hinzufügen
        for(int c : spielerIds) {
            for(Spieler s : result) {
                if(s.getId() == c) {
                    adapter.add(new SpielercardItem(s));
                }
            }
        }

        //End-Card einfügen
        adapter.add(new EndcardItem().setOnEndListener(this));
    }

    @Override
    public void onEndButtonClick(@NonNull EndcardItem item) {
        //TODO API Kommunikation hier
    }
}
