package de.fhswf.statistics.list.fragments;

import static java.util.Comparator.comparing;

import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import de.fhswf.statistics.R;
import de.fhswf.statistics.api.service.RemoteSpielerService;
import de.fhswf.statistics.api.service.SpielerService;
import de.fhswf.statistics.list.Adapter.ListAdapter;
import de.fhswf.statistics.list.item.SpielListItem;
import de.fhswf.statistics.model.Spiel;
import de.fhswf.statistics.util.ViewSort;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpieleListFragment extends Fragment implements SpielListItem.OnSpielListener {

    private ListAdapter adapter;
    // private ArrayList<Spiel> spielList;
    private SpielerService SpielerService;
    private boolean busy;
    private String sort = "";

    private HashMap<String, Double> map;

    private NavController navController;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_spiele_list, container, false);
        // spielList = new ArrayList<>();
        RecyclerView recyclerView = root.findViewById(R.id.container);
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        //Set Adapter for Recyclerview
        adapter = new ListAdapter();
        recyclerView.setAdapter(adapter);


        // Set OnClick Listener for Sorting the List

        TextView headerDatum = root.findViewById(R.id.HeaderDate);
        TextView headerName = root.findViewById(R.id.HeaderTeamName);
        TextView headerGegnerPunkte = root.findViewById(R.id.HeaderEnemyPoints);
        TextView headerUnserePunkte = root.findViewById(R.id.HeaderOurPoints);
        TextView headerPunktdifferenz = root.findViewById(R.id.HeaderPointdifference);
        TextView headerTeamfouls = root.findViewById(R.id.HeaderTeamfouls);

        headerDatum.setOnClickListener(v -> {
            if (Objects.equals(sort, "DatumAsc")) {
                sort = "DatumDesc";
            } else {
                sort = "DatumAsc";
            }
            refreshContent();
        });

        headerName.setOnClickListener(v -> {
            if (Objects.equals(sort, "NameAsc")) {
                sort = "NameDesc";
            } else {
                sort = "NameAsc";
            }
            refreshContent();
        });

        headerGegnerPunkte.setOnClickListener(v -> {
            if (Objects.equals(sort, "GegnerPunkteDesc")) {
                sort = "GegnerPunkteAsc";
            } else {
                sort = "GegnerPunkteDesc";
            }
            refreshContent();
        });

        headerUnserePunkte.setOnClickListener(v -> {
            if (Objects.equals(sort, "UnserePunkteDesc")) {
                sort = "UnserePunkteAsc";
            } else {
                sort = "UnserePunkteDesc";
            }
            refreshContent();
        });

        headerPunktdifferenz.setOnClickListener(v -> {
            if (Objects.equals(sort, "PunktedifferenzDesc")) {
                sort = "PunktedifferenzAsc";
            } else {
                sort = "PunktedifferenzDesc";
            }
            refreshContent();
        });

        headerTeamfouls.setOnClickListener(v -> {
            if (Objects.equals(sort, "TeamfoulsDesc")) {
                sort = "TeamfoulsAsc";
            } else {
                sort = "TeamfoulsDesc";
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
        //this.SpielerService = new MockService(false);
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
        //   spielList = new ArrayList<>();
        refreshContent();
    }

    /**
     * Kapselt die erhaltenen Spieler und fügt sie dem Adapter
     * hinzu.
     *
     * @param result Ergebnis des Services.
     */
    private void addSpielToList(List<Spiel> result) {
        this.busy = false;
        adapter.clear();
        map = new HashMap<>();
        ArrayList<String> keyList;

        switch (sort) {
            case "DatumAsc":
                result.sort(comparing(Spiel::getDatum));
                for (Spiel c : result) {
                    //   spielList.add(c);
                    adapter.add(new SpielListItem(c).setOnSpielListener(this));
                }
                break;
            case "DatumDesc":
                result.sort(comparing(Spiel::getDatum).reversed());
                for (Spiel c : result) {
                    // spielList.add(c);
                    adapter.add(new SpielListItem(c).setOnSpielListener(this));
                }
                break;
            case "NameAsc":
                result.sort(comparing(Spiel::getTeamname));
                for (Spiel c : result) {
                    //  spielList.add(c);
                    adapter.add(new SpielListItem(c).setOnSpielListener(this));
                }
                break;
            case "NameDesc":
                result.sort(comparing(Spiel::getTeamname).reversed());
                for (Spiel c : result) {
                    //  spielList.add(c);
                    adapter.add(new SpielListItem(c).setOnSpielListener(this));
                }
                break;
            case "GegnerPunkteAsc":
                result.sort(comparing(Spiel::getGastPunkte));
                for (Spiel c : result) {
                    //  spielList.add(c);
                    adapter.add(new SpielListItem(c).setOnSpielListener(this));
                }
                break;
            case "GegnerPunkteDesc":
                result.sort(comparing(Spiel::getGastPunkte).reversed());
                for (Spiel c : result) {
                    // spielList.add(c);
                    adapter.add(new SpielListItem(c).setOnSpielListener(this));
                }
                break;
            case "UnserePunkteAsc":
                result.sort(comparing(Spiel::getHeimPunkte));
                for (Spiel c : result) {
                    // spielList.add(c);
                    adapter.add(new SpielListItem(c).setOnSpielListener(this));
                }
                break;
            case "UnserePunkteDesc":
                result.sort(comparing(Spiel::getHeimPunkte).reversed());
                for (Spiel c : result) {
                    // spielList.add(c);
                    adapter.add(new SpielListItem(c).setOnSpielListener(this));
                }
                break;
            case "PunktedifferenzAsc":
                map = ViewSort.createHashMapSpiel(result, "Punktedifferenz");
                keyList = ViewSort.sortedList(map, false);

                for (String key : keyList) {
                    for (Spiel c : result) {
                        if (c.getTeamname().equals(key)) {
                            //   spielList.add(c);
                            adapter.add(new SpielListItem(c).setOnSpielListener(this));
                        }
                    }
                }
                clear();
                break;
            case "PunktedifferenzDesc":
                map = ViewSort.createHashMapSpiel(result, "Punktedifferenz");
                keyList = ViewSort.sortedList(map, true);

                for (String key : keyList) {
                    for (Spiel c : result) {
                        if (c.getTeamname().equals(key)) {
                            // spielList.add(c);
                            adapter.add(new SpielListItem(c).setOnSpielListener(this));
                        }
                    }
                }
                clear();
                break;
            case "TeamfoulsAsc":
                map = ViewSort.createHashMapSpiel(result, "Teamfouls");
                keyList = ViewSort.sortedList(map, false);

                for (String key : keyList) {
                    for (Spiel c : result) {
                        if (c.getTeamname().equals(key)) {
                            //   spielList.add(c);
                            adapter.add(new SpielListItem(c).setOnSpielListener(this));
                        }
                    }
                }
                clear();
                break;
            case "TeamfoulsDesc":
                map = ViewSort.createHashMapSpiel(result, "Teamfouls");
                keyList = ViewSort.sortedList(map, true);

                for (String key : keyList) {
                    for (Spiel c : result) {
                        if (c.getTeamname().equals(key)) {
                            // spielList.add(c);
                            adapter.add(new SpielListItem(c).setOnSpielListener(this));
                        }
                    }
                }
                clear();
                break;
            default:
                for (Spiel c : result) {
                    //spielList.add(c);

                    adapter.add(new SpielListItem(c).setOnSpielListener(this));
                }
        }


    }

    private void refreshContent() {
        if (!busy) {
            this.busy = true;
            SpielerService.fetchSpielList(
                    this::addSpielToList,
                    this::showErrorDialog
            );

        }
    }

    @Override
    public void onSpielClick(@NonNull SpielListItem item) {

        NavDirections action = SpieleListFragmentDirections.actionNavGamesToNavGame(item.getSpiel().getTeamname()).setSpielID(item.getSpiel().getId());
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

    private void clear() {
        if (!map.isEmpty()) map.clear();
    }
}