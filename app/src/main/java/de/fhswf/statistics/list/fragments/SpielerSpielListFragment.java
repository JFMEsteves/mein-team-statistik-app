package de.fhswf.statistics.list.fragments;

import android.content.Context;
import android.os.Bundle;
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
import de.fhswf.statistics.model.SpielSpieler;
import de.fhswf.statistics.model.Spieler;

/**
 * Zuständig für die Spieleübersicht
 */
public class SpielerSpielListFragment extends Fragment {
    private Spieler spieler;
    private ListAdapter adapter;
    private String sort = "";
    private ArrayList<SpielSpieler> result;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spiel_spielerlist, parent, false);
        Context context = inflater.getContext();

        TextView headerName = view.findViewById(R.id.HeaderId);
        // String title = view.getContext().getString(R.string.team);
        //headerName.setText(title);

        // Set the adapter
        RecyclerView container = view.findViewById(R.id.container);

        container.setLayoutManager(new LinearLayoutManager(context));

        adapter = new ListAdapter();
        container.setAdapter(adapter);
        // Setze die OnClickListener notwendig für die Sortierung der Liste.

        TextView headerPoints = view.findViewById(R.id.HeaderPoints);
        TextView headerFreethrowsMade = view.findViewById(R.id.HeaderFreethrowsMade);
        TextView headerFreethrowsThrown = view.findViewById(R.id.HeaderFreethrowsThrown);
        TextView headerFreethrowPercentage = view.findViewById(R.id.HeaderFreethrowPercentage);
        TextView headerThreesMade = view.findViewById(R.id.HeaderThreesMade);
        TextView headerFoulsDetails = view.findViewById(R.id.HeaderFoulsDetails);

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
        if (spieler.getStats() != null) {
            for (SpielSpieler c : spieler.getStats()) {
                if (c.getSpielerId() == spieler.getId()) {
                    result.add(c);
                }
            }

        }

        switch (sort) {
            case "NameAsc":
                result.sort(((spielSpieler, t1) -> spielSpieler.getSpiel().getTeamname().compareToIgnoreCase(t1.getSpiel().getTeamname())));
                for (SpielSpieler c : result) {
                    adapter.add(new SpielSpielerListItem(c, false));
                }

                break;
            case "NameDesc":
                result.sort(((spielSpieler, t1) -> t1.getSpiel().getTeamname().compareToIgnoreCase(spielSpieler.getSpiel().getTeamname())));
                Collections.reverse(result);
                for (SpielSpieler c : result) {
                    adapter.add(new SpielSpielerListItem(c, false));
                }

                break;
            case "PointsAsc":
                result.sort(Comparator.comparing(SpielSpieler::getPunkte));
                for (SpielSpieler c : result) {
                    adapter.add(new SpielSpielerListItem(c, false));
                }

                break;
            case "PointsDesc":
                result.sort(Comparator.comparing(SpielSpieler::getPunkte));
                Collections.reverse(result);
                for (SpielSpieler c : result) {
                    adapter.add(new SpielSpielerListItem(c, false));
                }

                break;
            case "FreethrowsMadeAsc":
                result.sort(Comparator.comparing(SpielSpieler::getGetroffeneFreiwuerfe));
                for (SpielSpieler c : result) {
                    adapter.add(new SpielSpielerListItem(c, false));
                }

                break;
            case "FreethrowsMadeDesc":
                result.sort(Comparator.comparing(SpielSpieler::getGetroffeneFreiwuerfe));
                Collections.reverse(result);
                for (SpielSpieler c : result) {
                    adapter.add(new SpielSpielerListItem(c, false));
                }

                break;
            case "FreethrowsThrownAsc":
                result.sort(Comparator.comparing(SpielSpieler::getGeworfeneFreiwuerfe));
                for (SpielSpieler c : result) {
                    adapter.add(new SpielSpielerListItem(c, false));
                }

                break;
            case "FreethrowsThrownDesc":
                result.sort(Comparator.comparing(SpielSpieler::getGeworfeneFreiwuerfe));
                Collections.reverse(result);
                for (SpielSpieler c : result) {
                    adapter.add(new SpielSpielerListItem(c, false));
                }

                break;
            case "FreethrowPercentageAsc":
                // Workaround Division by Zero
                for (SpielSpieler c : result) {
                    if (c.getGeworfeneFreiwuerfe() == 0) c.setGeworfeneFreiwuerfe(99);
                }
                result.sort((Comparator.comparingInt(spielSpieler -> spielSpieler.getGetroffeneFreiwuerfe() * 100 / spielSpieler.getGeworfeneFreiwuerfe())));
                for (SpielSpieler c : result) {
                    // Workaround Division by Zero
                    if (c.getGeworfeneFreiwuerfe() == 99) c.setGeworfeneFreiwuerfe(0);
                    adapter.add(new SpielSpielerListItem(c, false));
                }

                break;
            case "FreethrowPercentageDesc":
                // Workaround Division by Zero
                for (SpielSpieler c : result) {
                    if (c.getGeworfeneFreiwuerfe() == 0) c.setGeworfeneFreiwuerfe(99);
                }
                result.sort((Comparator.comparingInt(spielSpieler -> spielSpieler.getGetroffeneFreiwuerfe() * 100 / spielSpieler.getGeworfeneFreiwuerfe())));
                Collections.reverse(result);
                for (SpielSpieler c : result) {
                    // Workaround Division by Zero
                    if (c.getGeworfeneFreiwuerfe() == 99) c.setGeworfeneFreiwuerfe(0);
                    adapter.add(new SpielSpielerListItem(c, false));
                }

                break;
            case "ThreesMadeAsc":
                result.sort(Comparator.comparing(SpielSpieler::getDreiPunkteTreffer));
                for (SpielSpieler c : result) {
                    adapter.add(new SpielSpielerListItem(c, false));
                }

                break;
            case "ThreesMadeDesc":
                result.sort(Comparator.comparing(SpielSpieler::getDreiPunkteTreffer));
                Collections.reverse(result);
                for (SpielSpieler c : result) {
                    adapter.add(new SpielSpielerListItem(c, false));
                }

                break;
            case "FoulsDetailsAsc":
                result.sort(Comparator.comparing(SpielSpieler::getFouls));
                for (SpielSpieler c : result) {
                    adapter.add(new SpielSpielerListItem(c, false));
                }

                break;
            case "FoulsDetailsDesc":
                result.sort(Comparator.comparing(SpielSpieler::getFouls));
                Collections.reverse(result);
                for (SpielSpieler c : result) {
                    adapter.add(new SpielSpielerListItem(c, false));
                }

                break;
            default:
                for (SpielSpieler c : result) {
                    adapter.add(new SpielSpielerListItem(c, false));
                }

                break;

        }

        clearList();
    }

    public void setSpieler(Spieler spieler) {
        this.spieler = spieler;
    }

    public Spieler getSpieler() {
        return spieler;
    }

    private void clearList() {
        if (!result.isEmpty()) result.clear();
    }
}