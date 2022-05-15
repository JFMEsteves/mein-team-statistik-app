package de.fhswf.statistics.list.Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import de.fhswf.statistics.R;
import de.fhswf.statistics.list.fragments.GamesFragment;
import de.fhswf.statistics.list.fragments.SeasonFragment;
import de.fhswf.statistics.model.Spieler;


/**
 * Ein [FragmentPagerAdapter]  der ein fragment returned zusammenhängend mit einen der sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.Saison, R.string.spiel_liste};
    private final Context mContext;

    private final SeasonFragment seasonFragment;
    private final GamesFragment gamesFragment;
    private final Spieler spieler;


    public SectionsPagerAdapter(@NonNull Context mContext,
                                @NonNull FragmentManager fm,
                                @NonNull Spieler spieler) {
        super(fm);
        this.mContext = mContext;
        this.spieler = spieler;


        this.seasonFragment = new SeasonFragment();
        seasonFragment.setSpieler(spieler);
        this.gamesFragment = new GamesFragment();
        gamesFragment.setSpieler(spieler);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch (position) {
            case 0:
                return seasonFragment;
            case 1:
                return gamesFragment;
        }
        throw new RuntimeException();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }

}