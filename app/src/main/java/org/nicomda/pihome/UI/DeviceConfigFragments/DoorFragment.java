package org.nicomda.pihome.UI.DeviceConfigFragments;

/**
 * Created by nicomda on 5/10/16.
 */

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.EditTextPreferenceFix;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.preference.XpPreferenceFragment;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;


import org.nicomda.pihome.R;

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
