package org.nicomda.pihome.UI;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.nicomda.pihome.ModelObjects.Device;
import org.nicomda.pihome.ModelObjects.DeviceSwitch;
import org.nicomda.pihome.R;
import org.nicomda.pihome.sqlite.DB_Queries;

import java.util.ArrayList;

/**
 * Created by nicomda on 3/10/16.
 * Adapter for main screen devices
 */
public class MyDevicesAdapter extends RecyclerView.Adapter<MyDevicesAdapter.ViewHolder> {
    private ArrayList<Device> mDataset;
    private DB_Queries database;
    private DeviceSwitch undodeleteswitch;
    private Device undodeletedevice;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

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
            imgView.setImageResource(Integer.valueOf(d.getImg_res()));
            //TODO ACTION FOR THE DEVICE. SWITCH TYPE, GET INFO AND ID FROM DB
            final String itemid = d.getId();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "ID: " + itemid, Toast.LENGTH_SHORT).show();
                }
            });
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
        database = DB_Queries.getInstance(v.getContext());
        prefs = PreferenceManager.getDefaultSharedPreferences(v.getContext());
        editor = prefs.edit();

        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Device item = mDataset.get(holder.getAdapterPosition());
        holder.bindDevice(item);
        holder.opt_activated = Boolean.FALSE;
        holder.editView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation rotate360;
                rotate360=AnimationUtils.loadAnimation(v.getContext(),R.anim.rotate360);
                v.startAnimation(rotate360);
                if(!holder.opt_activated){
                    config_opt_enable(holder, holder.getAdapterPosition());
                }
                else{
                    config_opt_disable(holder, holder.getAdapterPosition());

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
                loadDevicePreferences(mDataset.get(position), getSwitchFromDatabase(mDataset.get(position).getId()));
                v.getContext().startActivity(new Intent(v.getContext(), DeviceConfigActivity.class));
            }
        });
        holder.binIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Saving register before delete for undo implementation
                undodeletedevice = mDataset.get(position);
                undodeleteswitch = getSwitchFromDatabase(mDataset.get(position).getId());
                deleteDeviceFromDatabase(mDataset.get(position).getId(), v.getContext());
                mDataset.remove(position);
                config_opt_disable(holder, position);
                notifyDataSetChanged();
                Snackbar.make(v,v.getContext().getResources().getString(R.string.device_deleted_msg), Snackbar.LENGTH_LONG)
                        .setAction(v.getContext().getResources().getString(R.string.undo), new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                mDataset.add(position, undodeletedevice);
                                insertDevice(undodeletedevice, undodeleteswitch);
                                config_opt_disable(holder,position);
                                notifyDataSetChanged();

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

    public void loadDevicePreferences(Device d, DeviceSwitch s) {
        editor.putString("ip", s.getIp());
        editor.putString("port", s.getPort());
        editor.putBoolean("passswitch", Boolean.valueOf(s.isPassword_enabled()));
        editor.putString("password", s.getPassword());
        editor.putString("gpio", s.getGpio());
        editor.putBoolean("pulseswitch", Boolean.valueOf(s.isPulse_enabled()));
        editor.putString("pulsems", s.getPulse_duration());
        editor.putBoolean("gpsswitch", Boolean.valueOf(s.isGps_enabled()));
        editor.putString("gpsdistance", s.getGps_distance());
        editor.putString("location", s.getGps_location());
        editor.putBoolean("nfcswitch", Boolean.valueOf(s.isNfc_enabled()));
        editor.putString("deviceid", d.getId());
        editor.putString("devicetitle", d.getName());
        editor.putString("devicesubtitle", d.getAdditional_info());
        editor.putString("imgres", d.getImg_res());
        editor.putString("type", d.getType());
        editor.putString("colorres", d.getColor_res());
        editor.commit();

    }

    public void deleteDeviceFromDatabase(String id, Context context) {
        try {
            database.getDb().beginTransaction();
            database.deleteDeviceById(id);
            Log.d("DBaseDelete DEVICE", id);
            database.deleteSwitchById(id);
            Log.d("DBaseDelete SWITCH", id);
            database.getDb().setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("DBaseException", e.getMessage());
        } finally {
            database.getDb().endTransaction();
        }
    }

    public DeviceSwitch getSwitchFromDatabase(String id) {
        DeviceSwitch s = null;
        try {
            database.getDb().beginTransaction();
            s = database.getSwitchByID(id);
            database.getDb().setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("DBaseException", e.getMessage());
        } finally {
            database.getDb().endTransaction();
        }
        return s;
    }

    public void insertDevice(Device d, DeviceSwitch s) {
        try {
            database.getDb().beginTransaction();
            database.insertDevice(d);
            Log.d("DBaseInsert DEVICE", d.getId());
            database.insertSwitch(s);
            Log.d("DBaseInsert SWITCH", d.getId());
            database.getDb().setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("DBaseException", e.getMessage());
        } finally {
            database.getDb().endTransaction();
        }
    }
}

