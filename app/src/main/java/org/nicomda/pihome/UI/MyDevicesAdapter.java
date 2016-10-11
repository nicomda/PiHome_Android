package org.nicomda.pihome.UI;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.media.Image;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.nicomda.pihome.ModelObjects.Device;
import org.nicomda.pihome.R;

import java.util.ArrayList;

/**
 * Created by nicomda on 3/10/16.
 */
public class MyDevicesAdapter extends RecyclerView.Adapter<MyDevicesAdapter.ViewHolder> {
    private ArrayList<Device> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView TitleView;
        public TextView SubtitleView;
        public ImageView imgView;
        public ImageView editView;
        public ImageView binIcon;
        public ImageView configIcon;
        public Boolean opt_activated;

        // each data item is just a string in this case

        public ViewHolder(View itemView) {
            super(itemView);
            TitleView=(TextView)itemView.findViewById(R.id.textTitle);
            SubtitleView=(TextView)itemView.findViewById(R.id.textSubtitle);
            imgView=(ImageView)itemView.findViewById(R.id.imgDevice);
            editView=(ImageView)itemView.findViewById(R.id.config_icon);
            binIcon=(ImageView)itemView.findViewById(R.id.opt_bin_icon);
            configIcon=(ImageView)itemView.findViewById(R.id.opt_edit_icon);
        }

        public void bindDevice(Device d){
            TitleView.setText(d.getName());
            SubtitleView.setText(d.getAdditional_info());
            editView.setImageResource(R.drawable.ic_settings);
            imgView.setImageResource(d.getImg_res());
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyDevicesAdapter(ArrayList<Device> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyDevicesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_device, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Device item= mDataset.get(position);
        holder.bindDevice(item);
        holder.opt_activated=new Boolean(false);
        holder.editView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation rotate360;
                rotate360=AnimationUtils.loadAnimation(v.getContext(),R.anim.rotate360);
                v.startAnimation(rotate360);
                if(!holder.opt_activated){
                    config_opt_enable(holder,position);
                }
                else{
                    config_opt_disable(holder,position);

                }
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void config_opt_enable(final ViewHolder holder,final int position){
        holder.opt_activated=true;
        holder.configIcon.setVisibility(View.VISIBLE);
        holder.binIcon.setVisibility(View.VISIBLE);

        ObjectAnimator anim = ObjectAnimator.ofFloat(holder.configIcon, "translationX", 0,-100);
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(holder.binIcon, "translationX", 0,-200);

        AnimatorSet as = new AnimatorSet();
        as.playTogether(anim, anim1);
        as.setDuration(150);
        as.start();
        holder.configIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Config",Toast.LENGTH_SHORT).show();
            }
        });
        holder.binIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Saving register before delete for undo implementation
                final Device undodelete=mDataset.get(position);
                mDataset.remove(position);
                notifyDataSetChanged();
                Snackbar.make(v,v.getContext().getResources().getString(R.string.device_deleted_msg), Snackbar.LENGTH_LONG)
                        .setAction(v.getContext().getResources().getString(R.string.undo), new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                mDataset.add(position,undodelete);
                                config_opt_disable(holder,position);
                                notifyDataSetChanged();
                                holder.opt_activated=false;
                            }
                        }).show();


            }
        });

    }

    public void config_opt_disable(final ViewHolder holder, final int position){
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(holder.configIcon, "translationX", -100,0);
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(holder.binIcon, "translationX", -200,0);

        AnimatorSet as1 = new AnimatorSet();
        as1.playTogether(anim3,anim4);
        as1.setDuration(150);
        as1.start();
        holder.configIcon.setOnClickListener(null);
        holder.binIcon.setOnClickListener(null);
        holder.opt_activated=false;
    }
}

