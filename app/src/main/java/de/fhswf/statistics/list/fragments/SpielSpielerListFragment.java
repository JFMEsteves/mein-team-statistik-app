package de.fhswf.statistics.list.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

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

    private String sort = "";
    private ArrayList<SpielSpieler> result;

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

        TextView headerName = view.findViewById(R.id.HeaderId);
        TextView headerPoints = view.findViewById(R.id.HeaderPoints);
        TextView headerFreethrowsMade = view.findViewById(R.id.HeaderFreethrowsMade);
        TextView headerFreethrowsThrown = view.findViewById(R.id.HeaderFreethrowsThrown);
        TextView headerFreethrowPercentage = view.findViewById(R.id.HeaderFreethrowPercentage);
        TextView headerThreesMade = view.findViewById(R.id.HeaderThreesMade);
        TextView headerFoulsDetails = view.findViewById(R.id.HeaderFoulsDetails);

        // Setze die OnClickListener notwendig für die Sortierung der Liste.
        headerName.setOnClickListener(v -> {
            if (Objects.equals(sort, "NameAsc")) {
                sort = "NameDesc";
            } else {
                sort = "NameAsc";
            }
            fetchSpieleListe();
        });
        headerPoints.setOnClickListener(v -> {
            if (Objects.equals(sort, "PointsDesc")) {
                sort = "PointsAsc";
            } else {
                sort = "PointsDesc";
            }
            fetchSpieleListe();
        });

        headerFreethrowsMade.setOnClickListener(v -> {
            if (Objects.equals(sort, "FreethrowsMadeDesc")) {
                sort = "FreethrowsMadeAsc";
            } else {
                sort = "FreethrowsMadeDesc";
            }
            fetchSpieleListe();
        });
        headerFreethrowsThrown.setOnClickListener(v -> {
            if (Objects.equals(sort, "FreethrowsThrownDesc")) {
                sort = "FreethrowsThrownAsc";
            } else {
                sort = "FreethrowsThrownDesc";
            }
            fetchSpieleListe();
        });
        headerFreethrowPercentage.setOnClickListener(v -> {
            if (Objects.equals(sort, "FreethrowPercentageDesc")) {
                sort = "FreethrowPercentageAsc";
            } else {
                sort = "FreethrowPercentageDesc";
            }
            fetchSpieleListe();
        });
        headerThreesMade.setOnClickListener(v -> {
            if (Objects.equals(sort, "ThreesMadeDesc")) {
                sort = "ThreesMadeAsc";
            } else {
                sort = "ThreesMadeDesc";
            }
            fetchSpieleListe();
        });
        headerFoulsDetails.setOnClickListener(v -> {
            if (Objects.equals(sort, "FoulsDetailsDesc")) {
                sort = "FoulsDetailsAsc";
            } else {
                sort = "FoulsDetailsDesc";
            }
            fetchSpieleListe();
        });


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fetchSpieleListe();
    }

    private void fetchSpieleListe() {
        if (!adapter.getItems().isEmpty()) adapter.clear();
        result = new ArrayList<>();

        if (spiel.getStats() != null) {
            for (SpielSpieler c : spiel.getStats()) {
                if (c.getSpielId() == spiel.getId())
                    // adapter.add(new SpielSpielerListItem(c, true));
                    result.add(c);
                Log.d("INHALT", "fetchSpieleListe: " + c.getSpieler().getName());
            }

        }

        switch (sort) {
            case "NameAsc":
                result.sort(((SpielSpieler, t1) -> SpielSpieler.getSpieler().getName().compareToIgnoreCase(t1.getSpieler().getName())));
                for (SpielSpieler c : result) {
                    adapter.add(new SpielSpielerListItem(c, true));
                }

                break;
            case "NameDesc":
                result.sort(((spielSpieler, t1) -> t1.getSpiel().getTeamname().compareToIgnoreCase(spielSpieler.getSpiel().getTeamname())));
                Collections.reverse(result);
                for (SpielSpieler c : result) {
                    adapter.add(new SpielSpielerListItem(c, true));
                }

                break;
            case "PointsAsc":
                result.sort(Comparator.comparing(SpielSpieler::getPunkte));
                for (SpielSpieler c : result) {
                    adapter.add(new SpielSpielerListItem(c, true));
                }

                break;
            case "PointsDesc":
                result.sort(Comparator.comparing(SpielSpieler::getPunkte));
                Collections.reverse(result);
                for (SpielSpieler c : result) {
                    adapter.add(new SpielSpielerListItem(c, true));
                }

                break;
            case "FreethrowsMadeAsc":
                result.sort(Comparator.comparing(SpielSpieler::getGetroffeneFreiwuerfe));
                for (SpielSpieler c : result) {
                    adapter.add(new SpielSpielerListItem(c, true));
                }

                break;
            case "FreethrowsMadeDesc":
                result.sort(Comparator.comparing(SpielSpieler::getGetroffeneFreiwuerfe));
                Collections.reverse(result);
                for (SpielSpieler c : result) {
                    adapter.add(new SpielSpielerListItem(c, true));
                }

                break;
            case "FreethrowsThrownAsc":
                result.sort(Comparator.comparing(SpielSpieler::getGeworfeneFreiwuerfe));
                for (SpielSpieler c : result) {
                    adapter.add(new SpielSpielerListItem(c, true));
                }

                break;
            case "FreethrowsThrownDesc":
                result.sort(Comparator.comparing(SpielSpieler::getGeworfeneFreiwuerfe));
                Collections.reverse(result);
                for (SpielSpieler c : result) {
                    adapter.add(new SpielSpielerListItem(c, true));
                }

                break;
            case "FreethrowPercentageAsc":
                //TODO Dieses Comparing übertragen auf die anderen Sortierungen in Spielerliste !!!
                for (SpielSpieler c : result) {
                    if (c.getGeworfeneFreiwuerfe() == 0) c.setGeworfeneFreiwuerfe(99);
                }
                result.sort((Comparator.comparingInt(spielSpieler -> spielSpieler.getGetroffeneFreiwuerfe() * 100 / spielSpieler.getGeworfeneFreiwuerfe())));
                for (SpielSpieler c : result) {
                    if (c.getGeworfeneFreiwuerfe() == 99) c.setGeworfeneFreiwuerfe(0);
                    adapter.add(new SpielSpielerListItem(c, true));
                }

                break;
            case "FreethrowPercentageDesc":
                // Workaround Divide by Zero
                for (SpielSpieler c : result) {
                    if (c.getGeworfeneFreiwuerfe() == 0) c.setGeworfeneFreiwuerfe(99);
                }
                result.sort((Comparator.comparingInt(spielSpieler -> spielSpieler.getGetroffeneFreiwuerfe() * 100 / spielSpieler.getGeworfeneFreiwuerfe())));
                Collections.reverse(result);
                for (SpielSpieler c : result) {
                    // Workaround Divide by Zero
                    if (c.getGeworfeneFreiwuerfe() == 99) c.setGeworfeneFreiwuerfe(0);
                    adapter.add(new SpielSpielerListItem(c, true));
                }

                break;
            case "ThreesMadeAsc":
                result.sort(Comparator.comparing(SpielSpieler::getDreiPunkteTreffer));
                for (SpielSpieler c : result) {
                    adapter.add(new SpielSpielerListItem(c, true));
                }

                break;
            case "ThreesMadeDesc":
                result.sort(Comparator.comparing(SpielSpieler::getDreiPunkteTreffer));
                Collections.reverse(result);
                for (SpielSpieler c : result) {
                    adapter.add(new SpielSpielerListItem(c, true));
                }

                break;
            case "FoulsDetailsAsc":
                result.sort(Comparator.comparing(SpielSpieler::getFouls));
                for (SpielSpieler c : result) {
                    adapter.add(new SpielSpielerListItem(c, true));
                }

                break;
            case "FoulsDetailsDesc":
                result.sort(Comparator.comparing(SpielSpieler::getFouls));
                Collections.reverse(result);
                for (SpielSpieler c : result) {
                    adapter.add(new SpielSpielerListItem(c, true));
                }

                break;
            default:
                for (SpielSpieler c : result) {
                    adapter.add(new SpielSpielerListItem(c, true));
                }

                break;

        }

        clearList();

    }

    public void setSpiel(Spiel spiel) {
        this.spiel = spiel;
    }

    public Spiel getSpiel() {
        return spiel;
    }

    private void clearList() {
        if (!result.isEmpty()) result.clear();
    }

}
