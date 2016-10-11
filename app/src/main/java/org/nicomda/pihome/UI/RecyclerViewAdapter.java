package org.nicomda.pihome.UI;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.nicomda.pihome.ModelObjects.Device;
import org.nicomda.pihome.ModelObjects.DeviceType;
import org.nicomda.pihome.R;

import java.util.List;

/**
 * Created by nicomda on 3/10/16.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter <RecyclerViewHolder> {
    private List<DeviceType> itemList;
    private Context context;

    public RecyclerViewAdapter(Context context, List<DeviceType> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_device_add, null);
        RecyclerViewHolder rcv = new RecyclerViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.countryName.setText(itemList.get(position).getDevName());
        holder.countryPhoto.setImageResource(itemList.get(position).getDevImg());
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
