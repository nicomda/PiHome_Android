package org.nicomda.pihome.UI.DeviceConfigFragments;

/**
 * Created by nicomda on 5/10/16.
 */

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;


import org.nicomda.pihome.R;

public class DoorFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
    private SharedPreferences prefs;
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        // Load the Preferences from the XML file
        getContext().setTheme(R.style.Theme_MyDialog);
        addPreferencesFromResource(R.xml.device_door_preferences);
        prefs= PreferenceManager.getDefaultSharedPreferences(getContext());
        prefs.registerOnSharedPreferenceChangeListener(this);

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference p=findPreference(key);
        if (p instanceof EditTextPreference) {
            EditTextPreference summaryText = (EditTextPreference) p;
            p.setSummary(summaryText.getText());
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
}
