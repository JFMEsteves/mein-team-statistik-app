package de.fhswf.statistics.list.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import de.fhswf.statistics.R;
import de.fhswf.statistics.list.Adapter.ListAdapter;
import de.fhswf.statistics.list.item.SpielSpielerListItem;
import de.fhswf.statistics.model.SpielSpieler;
import de.fhswf.statistics.model.Spieler;

/**
 * Zuständig für die Spieleübersicht
 */
public class SpielerSpielListFragment extends Fragment {
    private Spieler spieler;
    private ListAdapter adapter;
    private TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spiel_spielerlist, parent, false);
        Context context = inflater.getContext();

        textView = view.findViewById(R.id.GameID);
        String title = view.getContext().getString(R.string.team);
        textView.setText(title);
        // Set the adapter
        RecyclerView container = view.findViewById(R.id.container);

        container.setLayoutManager(new LinearLayoutManager(context));

        adapter = new ListAdapter();
        container.setAdapter(adapter);

        fetchSpieleListe();


        return view;
    }

    private void fetchSpieleListe() {

        if (spieler.getStats() != null) {
            for (SpielSpieler c : spieler.getStats()) {
                if (c.getSpielerId() == spieler.getId())
                    adapter.add(new SpielSpielerListItem(c, false));
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