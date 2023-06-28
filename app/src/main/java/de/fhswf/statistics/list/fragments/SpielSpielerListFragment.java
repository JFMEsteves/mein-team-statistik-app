package de.fhswf.statistics.list.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import de.fhswf.statistics.R;
import de.fhswf.statistics.list.Adapter.ListAdapter;
import de.fhswf.statistics.list.item.SpielSpielerListItem;
import de.fhswf.statistics.model.Spiel;
import de.fhswf.statistics.model.SpielSpieler;

/**
 * Zuständig für die Spieleübersicht
 */
public class SpielSpielerListFragment extends Fragment {
    private Spiel spiel;
    private ListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spiel_spielerlist, parent, false);
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

        if (spiel.getStats() != null) {
            for (SpielSpieler c : spiel.getStats()) {
                if (c.getSpielId() == spiel.getId())
                    adapter.add(new SpielSpielerListItem(c, true));
            }

        }


    }

    public void setSpiel(Spiel spiel) {
        this.spiel = spiel;
    }

    public Spiel getSpiel() {
        return spiel;
    }
}