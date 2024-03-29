package de.fhswf.statistics.list.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.fhswf.statistics.R;
import de.fhswf.statistics.list.item.EndcardItem;
import de.fhswf.statistics.list.item.ListItem;
import de.fhswf.statistics.list.item.SpielDetailsItem;
import de.fhswf.statistics.list.item.SpielListItem;
import de.fhswf.statistics.list.item.SpielSpielerListItem;
import de.fhswf.statistics.list.item.SpielcardItem;
import de.fhswf.statistics.list.item.SpieldetailscardItem;
import de.fhswf.statistics.list.item.SpielerListItem;
import de.fhswf.statistics.list.item.SpielerSaisonListItem;
import de.fhswf.statistics.list.item.SpielercardItem;
import de.fhswf.statistics.list.viewholder.BaseViewHolder;
import de.fhswf.statistics.list.viewholder.EndcardViewHolder;
import de.fhswf.statistics.list.viewholder.SpielDetailcardViewHolder;
import de.fhswf.statistics.list.viewholder.SpielDetailsViewHolder;
import de.fhswf.statistics.list.viewholder.SpielListViewHolder;
import de.fhswf.statistics.list.viewholder.SpielSpielerViewHolder;
import de.fhswf.statistics.list.viewholder.SpielcardViewHolder;
import de.fhswf.statistics.list.viewholder.SpielerListViewHolder;
import de.fhswf.statistics.list.viewholder.SpielerSaisonViewHolder;
import de.fhswf.statistics.list.viewholder.SpielercardViewHolder;

/**
 * Adapter für die RecyclerView
 */
public class ListAdapter extends RecyclerView.Adapter<BaseViewHolder> {


    private final ArrayList<ListItem> items;

    public ListAdapter() {
        this.items = new ArrayList<>();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());


        switch (viewType) {

            //1
            case SpielerListItem.TYPE:
                return new SpielerListViewHolder(
                        inflater.inflate(R.layout.item_spieler_list_layout, parent, false));

            //2
            case SpielcardItem.TYPE:
                return new SpielcardViewHolder(
                        inflater.inflate(R.layout.item_addgame_spiel, parent, false));

            //3
            case SpielSpielerListItem.TYPE:
                return new SpielSpielerViewHolder(
                        inflater.inflate(R.layout.item_spieler_spieleliste, parent, false));

            //4
            case SpielercardItem.TYPE:
                return new SpielercardViewHolder(
                        inflater.inflate(R.layout.item_addgame_spieler, parent, false));

            //5
            case SpielListItem.TYPE:
                return new SpielListViewHolder(inflater.inflate(R.layout.item_spiel_list_layout, parent, false));

            //6
            case SpielDetailsItem.TYPE:
                return new SpielDetailsViewHolder(inflater.inflate(R.layout.item_spieldetails_layout, parent, false));

            //8
            case SpielerSaisonListItem.TYPE:
                return new SpielerSaisonViewHolder(inflater.inflate(R.layout.item_spieler_saisonliste, parent, false));

            //9
            case SpieldetailscardItem.TYPE:
                return new SpielDetailcardViewHolder(inflater.inflate(R.layout.item_addgame_spieldetails, parent, false));

            //10
            case EndcardItem.TYPE:
                return new EndcardViewHolder(
                        inflater.inflate(R.layout.item_addgame_end, parent, false));
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
}
