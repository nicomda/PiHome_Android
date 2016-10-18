package org.nicomda.pihome.UI;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;


import org.nicomda.pihome.ModelObjects.Device;
import org.nicomda.pihome.ModelObjects.DeviceType;
import org.nicomda.pihome.R;

import java.util.ArrayList;
import java.util.List;

public class AddDevice extends AppCompatActivity {

    private GridLayoutManager lLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_deviceadd);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setTitleTextColor(Color.parseColor("#FAFAFA"));
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        loadDeviceGrid();
    }

    private void loadDeviceGrid(){
        List <DeviceType> DeviceItems= getAllItemList();
        lLayout = new GridLayoutManager(AddDevice.this,2);
        RecyclerView rView= (RecyclerView)findViewById(R.id.recycler_view_devices);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);
        RecyclerViewAdapter recyclerViewAdapter=new RecyclerViewAdapter(AddDevice.this,DeviceItems);
        rView.setAdapter(recyclerViewAdapter);
    }

    private List<DeviceType> getAllItemList(){
        //DEVICE ARRAY
        List<DeviceType> allItems = new ArrayList<DeviceType>();
        allItems.add(new DeviceType(getApplicationContext().getString(R.string.device_switch), R.drawable.light_switch));
        allItems.add(new DeviceType(getApplicationContext().getString(R.string.device_bulb), R.drawable.bulb));
        allItems.add(new DeviceType(getApplicationContext().getString(R.string.device_door), R.drawable.door));
        allItems.add(new DeviceType(getApplicationContext().getString(R.string.device_cam), R.drawable.cam));
        allItems.add(new DeviceType(getApplicationContext().getString(R.string.device_blind), R.drawable.blind));
        allItems.add(new DeviceType(getApplicationContext().getString(R.string.device_sprinkle), R.drawable.sprinkler));
        allItems.add(new DeviceType(getApplicationContext().getString(R.string.device_purify_plant), R.drawable.purify_plant));
        return allItems;
    }

}
