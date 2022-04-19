package de.fhswf.statistics.list.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.fhswf.statistics.R;
import de.fhswf.statistics.list.item.EndcardItem;
import de.fhswf.statistics.list.item.ListItem;
import de.fhswf.statistics.list.item.SpielSpielerListItem;
import de.fhswf.statistics.list.item.SpielcardItem;
import de.fhswf.statistics.list.item.SpielerListItem;
import de.fhswf.statistics.list.item.SpielerSaisonListItem;
import de.fhswf.statistics.list.item.SpielercardItem;
import de.fhswf.statistics.list.viewholder.BaseViewHolder;
import de.fhswf.statistics.list.viewholder.EndcardViewholder;
import de.fhswf.statistics.list.viewholder.SpielSpielerViewHolder;
import de.fhswf.statistics.list.viewholder.SpielcardViewholder;
import de.fhswf.statistics.list.viewholder.SpielerListViewHolder;
import de.fhswf.statistics.list.viewholder.SpielerSaisonViewholder;
import de.fhswf.statistics.list.viewholder.SpielercardViewholder;

public class ListAdapter extends RecyclerView.Adapter<BaseViewHolder> {


    private final ArrayList<ListItem> items;
    private final ArrayList<Fragment> fragmentArrayList = new ArrayList<>();

    public ListAdapter() {
        this.items = new ArrayList<>();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        //TODO Alle möglichen TYPEs hinzufügen mit ihrem dazugehörigem Viewholder und Layout
        switch (viewType) {

        //1
            case SpielerListItem.TYPE:
                return new SpielerListViewHolder(
                        inflater.inflate(R.layout.item_list_layout, parent, false));


        //4
            case SpielercardItem.TYPE:
                return new SpielercardViewholder(
                        inflater.inflate(R.layout.player_item, parent, false));

        //10
            case EndcardItem.TYPE:
                return new EndcardViewholder(
                        inflater.inflate(R.layout.player_end_item, parent, false));

        //2
            case SpielcardItem.TYPE:
                return new SpielcardViewholder(
                        inflater.inflate(R.layout.game_item, parent, false));

        //3
            case SpielSpielerListItem.TYPE:
                return new SpielSpielerViewHolder(
                        inflater.inflate(R.layout.player_games_item,parent,false));

            case SpielerSaisonListItem.TYPE:
                return new SpielerSaisonViewholder(inflater.inflate(R.layout.player_season_item,parent,false));

                default:
                throw new RuntimeException("Unsupported item-type: " + viewType);


        }
    }

    @Override
    public int getItemViewType(int position) {
       // Log.d(TAG, "getItemViewType: Checking position :" + position);
        return items.get(position).getType();
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * Füge der Liste ein neues Item hinzu und benachrichtige den Adapter.
     *
     * @param item Hinzuzufügendes Item.
     */
    public synchronized void add(@NonNull ListItem item) {
        int index = items.size();
        items.add(item);
        notifyItemInserted(index);
    }

    /**
     * Entfernt alle items aus der Liste und benachrichtigt den Adapter.
     */
    public synchronized void clear() {
        int count = items.size();
        items.clear();
        notifyItemRangeRemoved(0, count);
    }
    public ArrayList<ListItem> getItems() {
        return items;
    }
//TODO Fragment
    public void addFragment(Fragment fragment) {
        fragmentArrayList.add(fragment);
    }
}
