package org.nicomda.pihome.UI;

/**
 * Created by nicomda on 3/10/16.
 */

import android.app.Application;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.nicomda.pihome.R;

public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView countryName;
    public ImageView countryPhoto;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        countryName = (TextView)itemView.findViewById(R.id.device_name);
        countryPhoto = (ImageView)itemView.findViewById(R.id.device_photo);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Clicked Pos..." + getAdapterPosition(), Toast.LENGTH_SHORT).show();
        view.getContext().startActivity(new Intent(view.getContext(), DeviceConfigActivity.class));
    }
}