package de.fhswf.statistics.list.Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import de.fhswf.statistics.list.fragments.SpielerSpielListFragment;
import de.fhswf.statistics.list.fragments.SeasonFragment;
import de.fhswf.statistics.model.Spieler;

public class FragmentPageAdapter extends FragmentStateAdapter {


    private final SeasonFragment seasonFragment;
    private final SpielerSpielListFragment spielerSpielListFragment;
    private final Spieler spieler;

    public FragmentPageAdapter(@NonNull Context mContext,
                               @NonNull FragmentActivity fragmentActivity,
                               @NonNull Spieler spieler) {
        super(fragmentActivity);
        this.spieler = spieler;

        this.seasonFragment = new SeasonFragment();
        seasonFragment.setSpieler(spieler);
        this.spielerSpielListFragment = new SpielerSpielListFragment();
        spielerSpielListFragment.setSpieler(spieler);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return seasonFragment;
            case 1:
                return spielerSpielListFragment;
            default:
                return seasonFragment;
        }
    }


    @Override
    public int getItemCount() {
        return 2;
    }
}
