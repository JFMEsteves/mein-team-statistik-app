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

import de.fhswf.statistics.R;
import de.fhswf.statistics.list.Adapter.ListAdapter;
import de.fhswf.statistics.list.item.EndcardItem;
import de.fhswf.statistics.list.item.SpielerSaisonListItem;
import de.fhswf.statistics.model.Spieler;

//TODO Fragmente richtig fertigstellen
public class SeasonFragment extends Fragment {

    private Spieler spieler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player_season, parent, false);
        Context context = inflater.getContext();

        // Set the adapter
        RecyclerView container = view.findViewById(R.id.container);

        container.setLayoutManager(new LinearLayoutManager(context));

        ListAdapter adapter = new ListAdapter();
        container.setAdapter(adapter);

        adapter.add(new SpielerSaisonListItem(spieler));


        return view;
    }

    public void setSpieler(Spieler spieler) {
        this.spieler = spieler;
    }

    public Spieler getSpieler() {
        return spieler;
    }
}