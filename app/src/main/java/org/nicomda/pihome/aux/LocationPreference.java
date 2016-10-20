package org.nicomda.pihome.aux;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.preference.DialogPreference;
import android.util.AttributeSet;

import org.nicomda.pihome.R;

/**
 * Created by nicomda on 13/10/16.
 * Location preference to get a map and location.
 * Refer to LocationPReferenceDialogFragmentCompat too.
 */

public class LocationPreference extends DialogPreference {

    private String loc_string;

    public LocationPreference(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LocationPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, defStyleAttr);

    }

    public LocationPreference(Context context, AttributeSet attrs,
                              int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

    }

    public String getLoc_string() {

        return loc_string;

    }

    public void setLocationString(String locationString) {
        loc_string = locationString;
        persistString(loc_string);
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        // Default value from attribute. Fallback value is set to 0.
        return a.getInt(index, 0);
    }

    @Override
    protected void onSetInitialValue(boolean restorePersistedValue,
                                     Object defaultValue) {

        // Read the value. Use the default value if it is not possible.
        setLocationString(restorePersistedValue ?
                getPersistedString(loc_string) : (String) defaultValue);
    }

    @Override
    public int getDialogLayoutResource() {
        return R.layout.pref_dialog_location;
    }


}
