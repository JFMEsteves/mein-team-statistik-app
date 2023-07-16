package de.fhswf.statistics.list.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import java.util.Locale;

import de.fhswf.statistics.R;
import de.fhswf.statistics.api.service.RemoteSpielerService;
import de.fhswf.statistics.api.service.SpielerService;
import de.fhswf.statistics.list.Adapter.FragmentPageAdapter;
import de.fhswf.statistics.model.Spieler;

public class SpielerTabFragment extends Fragment {

    private int spielerID;
    private Spieler spieler;
    private SpielerService spielerService;
    private boolean busy;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_spieler_tab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Entnimmt die ID aus dem Argument
        spielerID = SpielerTabFragmentArgs.fromBundle(getArguments()).getSpielerID();

        //init Service
        // this.spielerService = new MockService(false);
        this.spielerService = new RemoteSpielerService(getActivity());

        this.busy = false;
        viewPager2 = view.findViewById(R.id.viewpager2);
        tabLayout = view.findViewById(R.id.tabs_spiel);
        fetchSpielerDetails();
    }

    /**
     * ViewPager vorbereiten
     */
    private void preparePager() {
        // Spielername wird Titel
        //setTitle(spieler.getName());
        FragmentPageAdapter fragmentPageAdapter = new FragmentPageAdapter(getActivity(), getActivity(), spieler);
        //ViewPager viewPager = findViewById(R.id.viewpager);

        viewPager2.setAdapter(fragmentPageAdapter);
        // Verhindert die Swipe Geste zum Wechseln der Tabs, da Tests zeigten, dass sonst das seitliche Scrollen innerhalb des Tabs erschwert ist.
        viewPager2.setUserInputEnabled(false);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        // Wird zwar nicht genutzt, weil der Input disabled ist, für Einfachheit einer Umstellung aber trotzdem noch vorhanden, falls der input auf false gestellt wird
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });
    }

    /**
     * Stößt das Abrufen der Spieler an.
     */
    private void fetchSpielerDetails() {
        if (!busy) {
            this.busy = true;

            spielerService.fetchSpielerDetails(
                    spielerID,
                    this::handleSpielerResult,
                    this::handleSpielerDetailsError
            );
        }
    }

    /**
     * Aufgerufen, wenn der SpielerService die Survey erfolgreich abgerufen hat.
     *
     * @param result Spieler
     */
    private void handleSpielerResult(Spieler result) {
        this.busy = false;
        this.spieler = result;

        if (spieler.getStats() == null) {
            handleSpielerDetailsError(new RuntimeException("Spielerdaten konnten nicht gefunden werden!"));
            return;
        }

        preparePager();
    }

    /**
     * Fehler-Behandlung für das Abrufen der Spieler-Details.
     * <p>
     * Da ein Versagen hier bedeutet, dass die Activity nicht weiter genutzt werden kann, gibt
     * es hier lediglich die Möglichkeit, es erneut zu versuchen, oder die Activity zu beenden.
     *
     * @param e Fehler-Details.
     */
    private void handleSpielerDetailsError(Throwable e) {
        this.busy = false;

        new AlertDialog.Builder(getActivity(), R.style.ErrorDialogTheme)
                .setTitle(R.string.dialog_title_error)
                .setMessage(String.format(Locale.getDefault(),
                        getString(R.string.dialog_message_error), e.getMessage()))
                .setPositiveButton(R.string.retry, (dialog, which) -> fetchSpielerDetails())
                .setNegativeButton(R.string.exit, (dialog, which) -> getActivity().finish())
                .setCancelable(true).setOnCancelListener(di -> getActivity().finish())
                .show();
    }

}
