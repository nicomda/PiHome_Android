package org.nicomda.pihome.UI;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.nicomda.pihome.R;
import org.nicomda.pihome.UI.DeviceConfigFragments.ChangeDeviceDescriptionDialogFragment;
import org.nicomda.pihome.UI.DeviceConfigFragments.ChangeDeviceNameDialogFragment;
import org.nicomda.pihome.UI.DeviceConfigFragments.DoorFragment;

import me.henrytao.smoothappbarlayout.SmoothAppBarLayout;

public class DeviceConfigActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    private TextView toolbar_title;
    private TextView toolbar_subtitle;
    private SharedPreferences prefs;
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
        SmoothAppBarLayout appbar=(SmoothAppBarLayout) findViewById(R.id.smooth_app_bar_layout);
        toolbar_title=(TextView)appbar.findViewById(R.id.title);
        toolbar_subtitle=(TextView)appbar.findViewById(R.id.subtitle);
        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(verticalOffset!=0){
                    toolbar_subtitle.setVisibility(View.GONE);
                    toolbar_title.setOnClickListener(null);
                    toolbar_subtitle.setOnClickListener(null);
                }else{
                    toolbar_subtitle.setVisibility(View.VISIBLE);
                    toolbar_title.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ChangeDeviceNameDialogFragment dialog=new ChangeDeviceNameDialogFragment();
                            dialog.show(getSupportFragmentManager(),"Dialog Change Name");
                        }
                    });
                    toolbar_subtitle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ChangeDeviceDescriptionDialogFragment dialog=new ChangeDeviceDescriptionDialogFragment();
                            dialog.show(getSupportFragmentManager(),"Dialog Change Description");
                        }
                    });
                }
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_fragment, new DoorFragment())
                .commit();

        //Setup OnSharedPreferenceChangeListener to get Title & Subtitle from the Dialog
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.registerOnSharedPreferenceChangeListener(this);
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
        if(key.equals("devicetitle")) toolbar_title.setText(prefs.getString("devicetitle",getString(R.string.device_title)));
        if(key.equals("devicesubtitle")) toolbar_subtitle.setText(prefs.getString("devicesubtitle",getString(R.string.device_title)));
    }
}
