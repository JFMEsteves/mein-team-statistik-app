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
import de.fhswf.statistics.list.Adapter.FragmentPageAdapterSpiel;
import de.fhswf.statistics.model.Spiel;

public class SpielTabFragment extends Fragment {
    private int spielID;
    private Spiel spiel;
    private SpielerService spielerService;
    private boolean busy;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_spiel_tab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spielID = SpielTabFragmentArgs.fromBundle(getArguments()).getSpielID();

        //init Service
        //this.spielerService = new MockService(false);
        this.spielerService = new RemoteSpielerService(getActivity());
        this.busy = false;
        viewPager2 = view.findViewById(R.id.viewpager2_spiel);
        tabLayout = view.findViewById(R.id.tabs_spiel);
        fetchSpielDetails();
    }

    /**
     * ViewPager vorbereiten
     */
    private void preparePager() {
        // Spielername wird Titel
        //setTitle(spieler.getName());
        FragmentPageAdapterSpiel fragmentPageAdapter = new FragmentPageAdapterSpiel(getActivity(), getActivity(), spiel);
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

    private void fetchSpielDetails() {
        if (!busy) {
            this.busy = true;

            spielerService.fetchSpielDetails(
                    spielID,
                    this::handleSpielResult,
                    this::handleSpielDetailsError
            );
        }
    }

    private void handleSpielResult(Spiel result) {
        this.busy = false;
        this.spiel = result;

        if (spiel.getDetails() == null) {
            handleSpielDetailsError(new RuntimeException("Spieldaten konnten nicht gefunden werden!"));
            return;
        }

        preparePager();
    }

    private void handleSpielDetailsError(Throwable e) {
        this.busy = false;

        new AlertDialog.Builder(getActivity(), R.style.ErrorDialogTheme)
                .setTitle(R.string.dialog_title_error)
                .setMessage(String.format(Locale.getDefault(),
                        getString(R.string.dialog_message_error), e.getMessage()))
                .setPositiveButton(R.string.retry, (dialog, which) -> fetchSpielDetails())
                .setNegativeButton(R.string.exit, (dialog, which) -> getActivity().finish())
                .setCancelable(true).setOnCancelListener(di -> getActivity().finish())
                .show();
    }
}
