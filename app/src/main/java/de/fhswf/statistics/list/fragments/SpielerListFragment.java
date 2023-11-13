package de.fhswf.statistics.list.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import de.fhswf.statistics.R;
import de.fhswf.statistics.api.service.RemoteSpielerService;
import de.fhswf.statistics.api.service.SpielerService;
import de.fhswf.statistics.dialog.SpielerAuswahlDialog;
import de.fhswf.statistics.list.Adapter.ListAdapter;
import de.fhswf.statistics.list.item.SpielerListItem;
import de.fhswf.statistics.model.Spieler;
import de.fhswf.statistics.util.StatCalculator;

public class SpielerListFragment extends Fragment implements SpielerListItem.OnSpielerListener {

    private ListAdapter adapter;
    private SpielerService SpielerService;
    private boolean busy;

    private ArrayList<Spieler> playerList;
    private String sort = "";

    private HashMap<String, Integer> map;

    private HashMap<String, Double> doubleMap;

    private NavController navController;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_spieler_list, container, false);

        playerList = new ArrayList<>();
        Log.d("CHECKING ON GETACTIVITY", "onCreateView: " + getActivity());

        //Setup Recyclerview
        RecyclerView recyclerView = root.findViewById(R.id.container);
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        //Set Adapter for Recyclerview
        adapter = new ListAdapter();
        recyclerView.setAdapter(adapter);


        //Neues Spiel hinzufügen Button mit AuswahlDialog
        FloatingActionButton addGame = root.findViewById(R.id.addGameBtn);
        addGame.setOnClickListener(v -> new SpielerAuswahlDialog(getActivity(), playerList));
        TextView headerName = root.findViewById(R.id.HeaderName);
        TextView headerAllPoints = root.findViewById(R.id.HeaderAllPoints);
        TextView headerPointsPerGame = root.findViewById(R.id.HeaderPointsPerGame);
        TextView headerFreethrows = root.findViewById(R.id.HeaderFreethrows);
        TextView headerFouls = root.findViewById(R.id.HeaderFouls);


        headerName.setOnClickListener(v -> {
            if (Objects.equals(sort, "NameAsc")) {
                sort = "NameDesc";
            } else {
                sort = "NameAsc";
            }
            refreshContent();
        });
        headerAllPoints.setOnClickListener(v -> {
            if (Objects.equals(sort, "AllPointsDesc")) {
                sort = "AllPointsAsc";
            } else {
                sort = "AllPointsDesc";
            }
            refreshContent();
        });
        headerPointsPerGame.setOnClickListener(v -> {
            if (Objects.equals(sort, "PointsPerGameDesc")) {
                sort = "PointsPerGameAsc";
            } else {
                sort = "PointsPerGameDesc";
            }
            refreshContent();
        });
        headerFreethrows.setOnClickListener(v -> {
            if (Objects.equals(sort, "FreethrowsDesc")) {
                sort = "FreethrowsAsc";
            } else {
                sort = "FreethrowsDesc";
            }
            refreshContent();
        });
        headerFouls.setOnClickListener(v -> {
            if (Objects.equals(sort, "FoulsDesc")) {
                sort = "FoulsAsc";
            } else {
                sort = "FoulsDesc";
            }
            refreshContent();
        });

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //setup Navigation Controller
        navController = Navigation.findNavController(view);

        // init Service
        // this.SpielerService = new MockService(false);
        this.SpielerService = new RemoteSpielerService(getActivity());


        // Daten von Service laden
        this.busy = false;

        refreshContent();
    }

    /**
     * Wenn Nutzer zurück zur MainActivity geht werden die Daten aktualisiert
     */
    @Override
    public void onResume() {
        super.onResume();
        playerList = new ArrayList<>();
        refreshContent();
    }


    private void refreshContent() {
        if (!busy) {
            this.busy = true;
            SpielerService.fetchSpielerList(
                    this::addSpielerToList,
                    this::showErrorDialog
            );

        }
    }

    /**
     * Kapselt die erhaltenen Spieler und fügt sie dem Adapter
     * hinzu.
     *
     * @param result Ergebnis des Services.
     */
    private void addSpielerToList(List<Spieler> result) {
        this.busy = false;
        adapter.clear();
        map = new HashMap<>();
        doubleMap = new HashMap<>();
        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Double> doubleList = new ArrayList<>();
        LinkedHashMap<String, Double> sortedDoubleMap = new LinkedHashMap<>();


        switch (sort) {
            case "NameAsc":
                result.sort((spieler, t1) -> spieler.getName().compareToIgnoreCase(t1.getName()));
                for (Spieler c : result) {
                    playerList.add(c);


                    adapter.add(new SpielerListItem(c).setOnSpielerListener(this));
                }

                break;
            case "NameDesc":
                result.sort((spieler, t1) -> spieler.getName().compareToIgnoreCase(t1.getName()));
                Collections.reverse(result);
                for (Spieler c : result) {
                    playerList.add(c);


                    adapter.add(new SpielerListItem(c).setOnSpielerListener(this));
                }

                break;
            case "AllPointsAsc":
                /*
                Ziel: Liste sortieren nach Werten die erst errechnet werden müssen.
                 */
                map.clear();
                sortedMap.clear();
                list.clear();

                // erstelle eine Hashmap mit dem Namen des Spielers als Key und den Gesamtpunkten als Value
                createHashMap(result, "Gesamtpunkte");

                for (Map.Entry<String, Integer> entry : map.entrySet()) {
                    list.add(entry.getValue());
                }
                // sortiere die Werte in der Liste
                Collections.sort(list);
                // sortiere die Map nach den  sortierten Werten der Liste
                for (int num : list) {
                    for (Map.Entry<String, Integer> entry : map.entrySet()) {
                        if (entry.getValue().equals(num)) {
                            sortedMap.put(entry.getKey(), num);
                        }
                    }

                }
                Set<String> keys = sortedMap.keySet();
                ArrayList<String> keyListAsc = new ArrayList<>(keys);
                for (String key : keyListAsc) {
                    for (Spieler c : result) {
                        if (c.getName().equals(key)) {
                            playerList.add(c);
                            adapter.add(new SpielerListItem(c).setOnSpielerListener(this));
                        }
                    }
                }
                break;
            case "AllPointsDesc":
                map.clear();
                sortedMap.clear();
                list.clear();
                // erstelle eine Hashmap mit dem Namen des Spielers als Key und den Gesamtpunkten als Value

                createHashMap(result, "Gesamtpunkte");

                for (Map.Entry<String, Integer> entry : map.entrySet()) {
                    list.add(entry.getValue());
                }
                // sortiere die Werte in der Liste und drehe sie um für absteigende Reihenfolge
                Collections.sort(list);
                Collections.reverse(list);
                for (int num : list) {
                    for (Map.Entry<String, Integer> entry : map.entrySet()) {
                        if (entry.getValue().equals(num)) {
                            sortedMap.put(entry.getKey(), num);
                        }
                    }

                }
                Set<String> keys2 = sortedMap.keySet();
                ArrayList<String> keyListDesc = new ArrayList<>(keys2);
                for (String key : keyListDesc) {
                    for (Spieler c : result) {
                        if (c.getName().equals(key)) {
                            playerList.add(c);
                            adapter.add(new SpielerListItem(c).setOnSpielerListener(this));
                        }
                    }
                }
                break;
            case "PointsPerGameAsc":
                doubleMap.clear();
                doubleList.clear();
                sortedDoubleMap.clear();

                createHashMap(result, "PunkteProSpiel");

                for (Map.Entry<String, Double> entry : doubleMap.entrySet()) {
                    doubleList.add(entry.getValue());
                }
                // sortiere die Werte in der Liste für aufsteigende Reihenfolge
                Collections.sort(doubleList);
                for (double num : doubleList) {
                    for (Map.Entry<String, Double> entry : doubleMap.entrySet()) {
                        if (entry.getValue().equals(num)) {
                            sortedDoubleMap.put(entry.getKey(), num);
                        }
                    }

                }
                Set<String> keysPPGA = sortedDoubleMap.keySet();
                ArrayList<String> keyListPPGA = new ArrayList<>(keysPPGA);
                for (String key : keyListPPGA) {
                    for (Spieler c : result) {
                        if (c.getName().equals(key)) {
                            playerList.add(c);
                            adapter.add(new SpielerListItem(c).setOnSpielerListener(this));
                        }
                    }
                }
                break;
            case "PointsPerGameDesc":
                doubleMap.clear();
                doubleList.clear();
                sortedDoubleMap.clear();

                createHashMap(result, "PunkteProSpiel");

                for (Map.Entry<String, Double> entry : doubleMap.entrySet()) {
                    doubleList.add(entry.getValue());
                }
                // sortiere die Werte in der Liste und drehe sie um für absteigende Reihenfolge
                Collections.sort(doubleList);
                Collections.reverse(doubleList);
                for (double num : doubleList) {
                    for (Map.Entry<String, Double> entry : doubleMap.entrySet()) {
                        if (entry.getValue().equals(num)) {
                            sortedDoubleMap.put(entry.getKey(), num);
                        }
                    }

                }
                Set<String> keysPPGD = sortedDoubleMap.keySet();
                ArrayList<String> keyListPPGD = new ArrayList<>(keysPPGD);
                for (String key : keyListPPGD) {
                    for (Spieler c : result) {
                        if (c.getName().equals(key)) {
                            playerList.add(c);
                            adapter.add(new SpielerListItem(c).setOnSpielerListener(this));
                        }
                    }
                }
                break;
            case "FreethrowsAsc":
                doubleMap.clear();
                doubleList.clear();
                sortedDoubleMap.clear();

                createHashMap(result, "Freiwurfquote");

                for (Map.Entry<String, Double> entry : doubleMap.entrySet()) {
                    doubleList.add(entry.getValue());
                }
                // sortiere die Werte in der Liste für aufsteigende Reihenfolge
                Collections.sort(doubleList);
                for (double num : doubleList) {
                    for (Map.Entry<String, Double> entry : doubleMap.entrySet()) {
                        if (entry.getValue().equals(num)) {
                            sortedDoubleMap.put(entry.getKey(), num);
                        }
                    }

                }
                Set<String> keysFWA = sortedDoubleMap.keySet();
                ArrayList<String> keyListFWA = new ArrayList<>(keysFWA);
                for (String key : keyListFWA) {
                    for (Spieler c : result) {
                        if (c.getName().equals(key)) {
                            playerList.add(c);
                            adapter.add(new SpielerListItem(c).setOnSpielerListener(this));
                        }
                    }
                }
                break;
            case "FreethrowsDesc":
                doubleMap.clear();
                doubleList.clear();
                sortedDoubleMap.clear();

                createHashMap(result, "Freiwurfquote");

                for (Map.Entry<String, Double> entry : doubleMap.entrySet()) {
                    doubleList.add(entry.getValue());
                }
                // sortiere die Werte in der Liste und drehe sie um für absteigende Reihenfolge
                Collections.sort(doubleList);
                Collections.reverse(doubleList);
                for (double num : doubleList) {
                    for (Map.Entry<String, Double> entry : doubleMap.entrySet()) {
                        if (entry.getValue().equals(num)) {
                            sortedDoubleMap.put(entry.getKey(), num);
                        }
                    }

                }
                Set<String> keysFWD = sortedDoubleMap.keySet();
                ArrayList<String> keyListFWD = new ArrayList<>(keysFWD);
                for (String key : keyListFWD) {
                    for (Spieler c : result) {
                        if (c.getName().equals(key)) {
                            playerList.add(c);
                            adapter.add(new SpielerListItem(c).setOnSpielerListener(this));
                        }
                    }
                }
                break;
            case "FoulsAsc":
                map.clear();
                sortedMap.clear();
                list.clear();

                createHashMap(result, "Fouls");

                for (Map.Entry<String, Integer> entry : map.entrySet()) {
                    list.add(entry.getValue());
                }
                // sortiere die Werte in der Liste für aufsteigende Reihenfolge
                Collections.sort(list);
                for (int num : list) {
                    for (Map.Entry<String, Integer> entry : map.entrySet()) {
                        if (entry.getValue().equals(num)) {
                            sortedMap.put(entry.getKey(), num);
                        }
                    }

                }
                Set<String> keysFA = sortedMap.keySet();
                ArrayList<String> keyListFA = new ArrayList<>(keysFA);
                for (String key : keyListFA) {
                    for (Spieler c : result) {
                        if (c.getName().equals(key)) {
                            playerList.add(c);
                            adapter.add(new SpielerListItem(c).setOnSpielerListener(this));
                        }
                    }
                }
                break;
            case "FoulsDesc":
                map.clear();
                sortedMap.clear();
                list.clear();

                createHashMap(result, "Fouls");

                for (Map.Entry<String, Integer> entry : map.entrySet()) {
                    list.add(entry.getValue());
                }
                // sortiere die Werte in der Liste und drehe sie um für absteigende Reihenfolge
                Collections.sort(list);
                Collections.reverse(list);
                for (int num : list) {
                    for (Map.Entry<String, Integer> entry : map.entrySet()) {
                        if (entry.getValue().equals(num)) {
                            sortedMap.put(entry.getKey(), num);
                        }
                    }

                }
                Set<String> keysFD = sortedMap.keySet();
                ArrayList<String> keyListFD = new ArrayList<>(keysFD);
                for (String key : keyListFD) {
                    for (Spieler c : result) {
                        if (c.getName().equals(key)) {
                            playerList.add(c);
                            adapter.add(new SpielerListItem(c).setOnSpielerListener(this));
                        }
                    }
                }
                break;

            default:
                //nothing, sorted by id
                for (Spieler c : result) {
                    playerList.add(c);


                    adapter.add(new SpielerListItem(c).setOnSpielerListener(this));
                }

        }


    }

    /**
     * Reagiert auf den Click auf 1 SpielerListItem indem mithilfe von Safe Args die ID und der Name des angeklickten Spielers
     * an das Ziel Fragment übergeben wird. Der Name wird in der ActionBar als Titel verwendet, die ID zum Abruf spezifischer Daten.
     *
     * @param item clicked item
     * @see SpielerTabFragment
     */
    @Override
    public void onSpielerClick(@NonNull SpielerListItem item) {
        // Mithilfe von Safe Args wird hier die ID des ausgewählten Spielers an das Ziel Fragment übergeben.
        // Dieses Fragment nutzt die ID dann um die Spieler Einzelansicht aufzubauen
        // Übergibt auch den Namen als Titel  Für die ActionBar
        NavDirections action = SpielerListFragmentDirections.actionNavHomeToNavPlayer(item.getSpieler().getName()).setSpielerID(item.getSpieler().getId());
        navController.navigate(action);
    }

    /**
     * Zeigt einen Fehler-Dialog an.
     * <p>
     * Da die Anwendung bei einem Service-Fehler nutzlos ist, gibt es entweder die Option, es
     * direkt erneut zu versuchen, oder die App wird beendet.
     *
     * @param e Fehler-Details.
     */
    private void showErrorDialog(Throwable e) {
        this.busy = false;

        new AlertDialog.Builder(getActivity(), R.style.ErrorDialogTheme)
                .setTitle(R.string.dialog_title_error)
                .setMessage(String.format(
                        getString(R.string.dialog_message_error), e.getMessage()))
                .setPositiveButton(R.string.retry, (dialog, which) -> refreshContent())
                .setNegativeButton(R.string.exit, (dialog, which) -> getActivity().finish())
                .setCancelable(true)
                .setOnCancelListener(dialog -> getActivity().finish())
                .show();
    }

    private void createHashMap(List<Spieler> result, String s) {
        switch (s) {
            case "Gesamtpunkte":
                for (Spieler c : result) {
                    map.put(c.getName(), StatCalculator.gesamtpunkteCalc(c));
                }
                break;
            case "PunkteProSpiel":
                for (Spieler c : result) {
                    doubleMap.put(c.getName(), StatCalculator.punktePerSpielCalc(c));
                }
                break;
            case "Freiwurfquote":
                for (Spieler c : result) {
                    doubleMap.put(c.getName(), Double.valueOf(StatCalculator.freiwurfquoteCalc(c, false)));
                }
                break;
            case "Fouls":
                for (Spieler c : result) {
                    map.put(c.getName(), StatCalculator.foulsCalcSpieler(c));
                }
                break;
            default:
                break;
        }

    }

}