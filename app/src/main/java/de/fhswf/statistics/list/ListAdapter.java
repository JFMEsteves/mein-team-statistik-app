package de.fhswf.statistics.list;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.fhswf.statistics.R;
import de.fhswf.statistics.list.item.EndaddGameListItem;
import de.fhswf.statistics.list.item.ListItem;
import de.fhswf.statistics.list.item.SpielListItem;
import de.fhswf.statistics.list.item.SpielerListItem;
import de.fhswf.statistics.list.item.SpielercardItem;
import de.fhswf.statistics.list.viewholder.BaseViewHolder;
import de.fhswf.statistics.list.viewholder.EndaddGameViewholder;
import de.fhswf.statistics.list.viewholder.SpielViewholder;
import de.fhswf.statistics.list.viewholder.SpielerListViewHolder;
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
                        inflater.inflate(R.layout.item_header_layout, parent, false));


            //4
            case SpielercardItem.TYPE:
                return new SpielercardViewholder(
                        inflater.inflate(R.layout.player_item, parent, false));

            //3
            case EndaddGameListItem.TYPE:
                return new EndaddGameViewholder(
                        inflater.inflate(R.layout.player_end_item, parent, false));

            //2
            case SpielListItem.TYPE:
                return new SpielViewholder(
                        inflater.inflate(R.layout.game_item, parent, false));
            default:
                throw new RuntimeException("Unsupported item-type: " + viewType);


        }
    }

    @Override
    public int getItemViewType(int position) {
        Log.d(TAG, "getItemViewType: Checking position :" + position);
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

    public void addFragment(Fragment fragment) {
        fragmentArrayList.add(fragment);
    }
}
