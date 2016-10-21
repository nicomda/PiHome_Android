package org.nicomda.pihome.UI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.nicomda.pihome.ModelObjects.Device;
import org.nicomda.pihome.ModelObjects.DeviceSwitch;
import org.nicomda.pihome.R;
import org.nicomda.pihome.UI.DeviceConfigFragments.ChangeDeviceDescriptionDialogFragment;
import org.nicomda.pihome.UI.DeviceConfigFragments.ChangeDeviceNameDialogFragment;
import org.nicomda.pihome.UI.DeviceConfigFragments.DoorFragment;
import org.nicomda.pihome.sqlite.DB_Queries;

import me.henrytao.smoothappbarlayout.SmoothAppBarLayout;

public class DeviceConfigActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    private TextView toolbar_title;
    private TextView toolbar_subtitle;
    private ImageView avatar;
    private SharedPreferences prefs;
    private SmoothAppBarLayout appbar;
    private DB_Queries datos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_config);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_textView);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        appbar = (SmoothAppBarLayout) findViewById(R.id.smooth_app_bar_layout);
        toolbar_title=(TextView)appbar.findViewById(R.id.title);
        toolbar_subtitle=(TextView)appbar.findViewById(R.id.subtitle);
        avatar = (ImageView) appbar.findViewById(R.id.avatar);
        //Setting up visibility and listeners in Coordinator Layout
        configOffsetChangedListener();
        //GET DB INSTANCE
        datos = DB_Queries.getInstance(getApplicationContext());
        //FAB BUTTON SAVE
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDevice();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_fragment, new DoorFragment())
                .commit();

        //Setup OnSharedPreferenceChangeListener to get Title & Subtitle from the Dialog
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.registerOnSharedPreferenceChangeListener(this);
        toolbar_title.setText(prefs.getString("devicetitle", getString(R.string.device_title)));
        toolbar_subtitle.setText(prefs.getString("devicesubtitle", getString(R.string.device_subtitle)));
        avatar.setImageResource(Integer.valueOf(prefs.getString("imgres", String.valueOf(R.drawable.bulb))));

        //
    }

    @Override
    protected void onResume() {
        super.onResume();
// Set up a listener whenever a key changes
                prefs.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
// Unregister the listener whenever a key changes
        prefs.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals("devicetitle"))
            toolbar_title.setText(prefs.getString("devicetitle", getString(R.string.device_title_set)));
        if (key.equals("devicesubtitle"))
            toolbar_subtitle.setText(prefs.getString("devicesubtitle", getString(R.string.device_title_set)));
    }

    public void configOffsetChangedListener() {
        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset != 0) {
                    toolbar_subtitle.setVisibility(View.GONE);
                    toolbar_title.setOnClickListener(null);
                    toolbar_subtitle.setOnClickListener(null);
                } else {
                    toolbar_subtitle.setVisibility(View.VISIBLE);
                    toolbar_title.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ChangeDeviceNameDialogFragment dialog = new ChangeDeviceNameDialogFragment();
                            dialog.show(getSupportFragmentManager(), "Dialog Change Name");
                        }
                    });
                    toolbar_subtitle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ChangeDeviceDescriptionDialogFragment dialog = new ChangeDeviceDescriptionDialogFragment();
                            dialog.show(getSupportFragmentManager(), "Dialog Change Description");
                        }
                    });
                }
            }
        });
    }

    public Device getDeviceFromPrefs() {
        Device dev = new Device(prefs.getString("deviceid", "ID"),
                prefs.getString("devicetitle", getString(R.string.device_title)),
                prefs.getString("devicesubtitle", getString(R.string.device_subtitle)),
                prefs.getString("imgres", "UNSET"),
                prefs.getString("type", "UNSET"),
                prefs.getString("colorres", "UNSET"));

        return dev;
    }

    public DeviceSwitch getSwitchFromPrefs() {
        DeviceSwitch dev = new DeviceSwitch(
                prefs.getString("deviceid", getString(R.string.door_device_id)),
                prefs.getString("ip", getString(R.string.door_ip)),
                prefs.getString("port", getString(R.string.door_port)),
                String.valueOf(prefs.getBoolean("passswitch", false)),
                prefs.getString("password", getString(R.string.door_password)),
                prefs.getString("gpio", getString(R.string.door_pin)),
                String.valueOf(prefs.getBoolean("pulseswitch", false)),
                prefs.getString("pulsems", getString(R.string.door_pulse_ms)),
                String.valueOf(prefs.getBoolean("gpsswitch", false)),
                prefs.getString("gpsdistance", getString(R.string.door_gps_distance)),
                prefs.getString("location", getString(R.string.door_gps_location)),
                String.valueOf(prefs.getBoolean("nfcswitch", false)));
        return dev;
    }

    public void saveDevice() {
        DeviceSwitch s;
        Device d;
        s = getSwitchFromPrefs();
        d = getDeviceFromPrefs();
        //This means it's a new device because has no ID assigned
        if (s.getId() == getString(R.string.door_device_id)) {
            s.setId(d.generarIdDevice()); // Double id assignment
            //INSERT DB QUERIES
            try {
                datos.getDb().beginTransaction();
                Log.d("DBaseInsert DEVICE ", datos.insertDevice(d));
                Log.d("DBaseInsert SWITCH ", datos.insertSwitch(s));
                datos.getDb().setTransactionSuccessful();
            } catch (Exception e) {
                Log.d("DBaseException", e.getMessage());

            } finally {
                datos.getDb().endTransaction();
            }
        } else {
            //UPDATE DB QUERIES
            try {
                datos.getDb().beginTransaction();
                Log.d("DBaseUpdate DEVICE ", String.valueOf(datos.updateDevice(d)));
                Log.d("DBaseUpdate SWITCH ", String.valueOf(datos.updateSwitch(s)));
                datos.getDb().setTransactionSuccessful();
            } catch (Exception e) {
                Log.d("DBaseException", e.getMessage());

            } finally {
                datos.getDb().endTransaction();
            }
        }
    }

    //New implementation doesn't use it. Not gonna deprecate it yet.
    public Device getDeviceByName(String name) {
        Device d;
        try {
            datos.getDb().beginTransaction();
            d = datos.getDeviceByName(name);
            datos.getDb().setTransactionSuccessful();
            return d;
        } catch (Exception e) {
            Log.d("DBaseException", e.getMessage());
            d = null;
            return d;
        } finally {
            datos.getDb().endTransaction();

        }

    }




}
