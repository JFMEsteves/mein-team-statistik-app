package de.fhswf.statistics.list.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.fhswf.statistics.R;
import de.fhswf.statistics.list.Adapter.ListAdapter;
import de.fhswf.statistics.list.item.EndcardItem;
import de.fhswf.statistics.list.item.SpielSpielerListItem;
import de.fhswf.statistics.list.item.SpielerSaisonListItem;
import de.fhswf.statistics.model.SpielSpieler;
import de.fhswf.statistics.model.Spieler;

public class GamesFragment extends Fragment {
    private Spieler spieler;
    private ArrayList<SpielSpieler> spieleliste;
    private SpielSpieler spielSpieler;
    private ListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player_games, parent, false);
        Context context = inflater.getContext();

        // Set the adapter
        RecyclerView container = view.findViewById(R.id.container);

        container.setLayoutManager(new LinearLayoutManager(context));

        adapter = new ListAdapter();
        container.setAdapter(adapter);

        fetchSpieleListe();


        return view;
    }

    private void fetchSpieleListe() {
/*
        for(SpielSpieler c : spieler.getStats()){
            spielSpieler.setSpielerId(c.getSpielerId());
            spielSpieler.setSpielId(c.getSpielId());
            spielSpieler.setPunkte(c.getPunkte());
            spielSpieler.setGeworfeneFreiwuerfe(c.getGeworfeneFreiwuerfe());
            spielSpieler.setGetroffeneFreiwuerfe(c.getGetroffeneFreiwuerfe());
            spielSpieler.setDreiPunkteTreffer(c.getDreiPunkteTreffer());
            spielSpieler.setFouls(c.getFouls());
            adapter.add(new SpielSpielerListItem(spielSpieler));
        }


 */


        if (spieler.getStats() != null) {
            for (SpielSpieler c : spieler.getStats()) {
                if (c.getSpielerId() == spieler.getId())
                    adapter.add(new SpielSpielerListItem(c));
            }

        }


    }

    public void setSpieler(Spieler spieler) {
        this.spieler = spieler;
    }

    public Spieler getSpieler() {
        return spieler;
    }
}