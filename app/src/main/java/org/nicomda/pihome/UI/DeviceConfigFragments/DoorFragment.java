package org.nicomda.pihome.UI.DeviceConfigFragments;

/**
 * Created by nicomda on 5/10/16.
 *
 */

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.EditTextPreferenceFix;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceManager;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;


import org.nicomda.pihome.R;
import org.nicomda.pihome.aux.LocationPreference;
import org.nicomda.pihome.aux.LocationPreferenceDialogFragmentCompat;

public class DoorFragment extends com.takisoft.fix.support.v7.preference.PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
    private EditText editTextPassword;
    private SharedPreferences prefs;
    private Preference p;
    @Override
    public void onCreatePreferencesFix(Bundle bundle, String s) {
        // Load the Preferences from the XML file
        getContext().setTheme(R.style.Theme_MyDialog);
        addPreferencesFromResource(R.xml.device_door_preferences);
        p = findPreference("password");
        editTextPassword = ((EditTextPreferenceFix) p).getEditText();
        editTextPassword.setTransformationMethod(new PasswordTransformationMethod());
        prefs= PreferenceManager.getDefaultSharedPreferences(getContext());
        prefs.registerOnSharedPreferenceChangeListener(this);
        loadPreferencesOnSummary();

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        p = findPreference(key);
        if (p instanceof EditTextPreference) {
            EditTextPreference summaryText = (EditTextPreference) p;
            p.setSummary(summaryText.getText());

        }
        if (p instanceof EditTextPreferenceFix) {
            p.setSummary("●●●●●●●●●●");
        }
        if (p instanceof LocationPreference) {
            LocationPreference summaryText = (LocationPreference) p;
            p.setSummary(((LocationPreference) p).getLoc_string());
        }
    }

    @Override
    public void onDisplayPreferenceDialog(Preference preference) {
        // Try if the preference is one of our custom Preferences
        DialogFragment dialogFragment = null;
        if (preference instanceof LocationPreference) {
            // Create a new instance of LocationPreferenceDialogFragment with the key of the related
            // Preference
            dialogFragment = LocationPreferenceDialogFragmentCompat
                    .newInstance(preference.getKey());
        }
        // If it was one of our cutom Preferences, show its dialog
        if (dialogFragment != null) {
            dialogFragment.setTargetFragment(this, 0);
            dialogFragment.show(this.getFragmentManager(),
                    "android.support.v7.preference" +
                            ".PreferenceFragment.DIALOG");
        }
        // Could not be handled here. Try with the super method.
        else {
            super.onDisplayPreferenceDialog(preference);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        prefs.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();

        prefs.registerOnSharedPreferenceChangeListener(this);
    }

    public void loadPreferencesOnSummary() {
        p = findPreference("ip");
        p.setSummary(prefs.getString("ip", getString(R.string.door_ip_set)));
        p = findPreference("port");
        p.setSummary(prefs.getString("port", getString(R.string.door_port_set)));
        p = findPreference("gpio");
        p.setSummary(prefs.getString("gpio", getString(R.string.door_pin_set)));
        p = findPreference("pulsems");
        p.setSummary(prefs.getString("pulsems", getString(R.string.door_pulse_ms_set)));
        p = findPreference("gpsdistance");
        p.setSummary(prefs.getString("gpsdistance", getString(R.string.door_gps_distance_set)));
        p = findPreference("location");
        p.setSummary(prefs.getString("location", getString(R.string.door_gps_location_set)));
        p = findPreference("deviceid");
        p.setTitle(p.getTitle() + ": " + prefs.getString("deviceid", " "));
    }
}
