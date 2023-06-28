package de.fhswf.statistics.list.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import java.util.List;

import de.fhswf.statistics.R;
import de.fhswf.statistics.api.service.RemoteSpielerService;
import de.fhswf.statistics.api.service.SpielerService;
import de.fhswf.statistics.list.Adapter.ListAdapter;
import de.fhswf.statistics.list.item.SpielListItem;
import de.fhswf.statistics.model.Spiel;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpieleListFragment extends Fragment implements SpielListItem.OnSpielListener {

    private ListAdapter adapter;
    private ArrayList<Spiel> spielList;
    private SpielerService SpielerService;
    private boolean busy;

    private NavController navController;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_spiele_list, container, false);
        spielList = new ArrayList<>();
        RecyclerView recyclerView = root.findViewById(R.id.container);
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        //Set Adapter for Recyclerview
        adapter = new ListAdapter();
        recyclerView.setAdapter(adapter);


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
        spielList = new ArrayList<>();
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

        for (Spiel c : result) {
            spielList.add(c);

            adapter.add(new SpielListItem(c).setOnSpielListener(this));
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
}