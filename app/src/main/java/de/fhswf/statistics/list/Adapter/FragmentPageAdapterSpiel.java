package de.fhswf.statistics.list.Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import de.fhswf.statistics.list.fragments.SpielDetailFragment;
import de.fhswf.statistics.list.fragments.SpielSpielerListFragment;
import de.fhswf.statistics.model.Spiel;

public class FragmentPageAdapterSpiel extends FragmentStateAdapter {


    private final SpielDetailFragment spielDetailFragment;
    private final SpielSpielerListFragment spielSpielerListFragment;
    private final Spiel spiel;

    public FragmentPageAdapterSpiel(@NonNull Context mContext,
                                    @NonNull FragmentActivity fragmentActivity,
                                    @NonNull Spiel spiel) {
        super(fragmentActivity);
        this.spiel = spiel;

        this.spielDetailFragment = new SpielDetailFragment();
        spielDetailFragment.setSpiel(spiel);
        //seasonFragment.setSpieler(spiel);
        this.spielSpielerListFragment = new SpielSpielerListFragment();
        spielSpielerListFragment.setSpiel(spiel);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return spielDetailFragment;
            case 1:
                return spielSpielerListFragment;
            default:
                return spielSpielerListFragment;
        }
    }


    @Override
    public int getItemCount() {
        return 2;
    }
}
