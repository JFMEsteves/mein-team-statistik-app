package de.fhswf.statistics.list.fragments;

import android.os.Bundle;
import android.util.Log;
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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import de.fhswf.statistics.R;
import de.fhswf.statistics.api.service.RemoteSpielerService;
import de.fhswf.statistics.api.service.SpielerService;
import de.fhswf.statistics.dialog.SpielerAuswahlDialog;
import de.fhswf.statistics.list.Adapter.ListAdapter;
import de.fhswf.statistics.list.item.SpielerListItem;
import de.fhswf.statistics.model.Spieler;

public class SpielerListFragment extends Fragment implements SpielerListItem.OnSpielerListener {

    private ListAdapter adapter;
    private SpielerService SpielerService;
    private boolean busy;

    private ArrayList<Spieler> playerList;

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

        for (Spieler c : result) {
            playerList.add(c);

            adapter.add(new SpielerListItem(c).setOnSpielerListener(this));
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

}