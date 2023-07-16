package de.fhswf.statistics.list.Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import de.fhswf.statistics.list.fragments.SpielDetailFragment;
import de.fhswf.statistics.list.fragments.SpielSpielerListFragment;
import de.fhswf.statistics.model.Spiel;

/**
 * Adapter für die ViewPager2 Komponente.
 * <p>
 * Dieser Adapter wird verwendet um die Fragmente für die ViewPager2 Komponente zu erstellen.
 * <p>
 * Die Fragmente werden hierbei erstellt und mit dem Spiel Objekt initialisiert.
 */
public class FragmentPageAdapterSpiel extends FragmentStateAdapter {


    private final SpielDetailFragment spielDetailFragment;
    private final SpielSpielerListFragment spielSpielerListFragment;

    public FragmentPageAdapterSpiel(@NonNull Context mContext,
                                    @NonNull FragmentActivity fragmentActivity,
                                    @NonNull Spiel spiel) {
        super(fragmentActivity);

        this.spielDetailFragment = new SpielDetailFragment();
        spielDetailFragment.setSpiel(spiel);

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
