package de.fhswf.statistics.list.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import de.fhswf.statistics.R;
import de.fhswf.statistics.list.Adapter.ListAdapter;
import de.fhswf.statistics.list.item.SpielDetailsItem;
import de.fhswf.statistics.model.Spiel;
import de.fhswf.statistics.model.Spieldetails;

public class SpielDetailFragment extends Fragment {
    private Spiel spiel;
    private ListAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup parent, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spiel_details, parent, false);
        Context context = inflater.getContext();


        RecyclerView container = view.findViewById(R.id.container);

        container.setLayoutManager(new LinearLayoutManager(context));

        adapter = new ListAdapter();
        container.setAdapter(adapter);


        fetchSpieldetailsListe();


        return view;
    }

    /**
     * Fetched die SpieldetailsListe und added sie zum  adapter.
     */
    private void fetchSpieldetailsListe() {


        // Füllt die Liste result mit den Spieldetails des Spielers um Sie dann sortiert oder unsortiert an den Adapter zu übergeben.
        if (spiel.getDetails() != null) {
            for (Spieldetails c : spiel.getDetails()) {
                if (c.getId() == spiel.getId()) {

                    adapter.add(new SpielDetailsItem(c));
                }

            }

        }


    }

    public Spiel getSpiel() {
        return spiel;
    }

    public void setSpiel(Spiel spiel) {
        this.spiel = spiel;
    }

}
