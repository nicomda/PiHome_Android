package org.nicomda.pihome.UI;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.nicomda.pihome.ModelObjects.Device;
import org.nicomda.pihome.ModelObjects.DeviceType;
import org.nicomda.pihome.R;

import java.util.List;

/**
 * Created by nicomda on 3/10/16.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {
    private List<DeviceType> itemList;
    private Context context;

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView deviceName;
        public ImageView devicePhoto;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            deviceName = (TextView) itemView.findViewById(R.id.device_name);
            devicePhoto = (ImageView) itemView.findViewById(R.id.device_photo);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "Clicked Pos..." + getAdapterPosition(), Toast.LENGTH_SHORT).show();
            view.getContext().startActivity(new Intent(view.getContext(), DeviceConfigActivity.class));
        }
    }

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
        holder.deviceName.setText(itemList.get(position).getDevName());
        holder.devicePhoto.setImageResource(itemList.get(position).getDevImg());
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
