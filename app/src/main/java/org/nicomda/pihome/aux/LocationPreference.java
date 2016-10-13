package org.nicomda.pihome.aux;

import android.content.Context;
import android.content.res.TypedArray;
import android.location.Location;
import android.support.v7.preference.DialogPreference;
import android.util.AttributeSet;

/**
 * Created by nicomda on 13/10/16.
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

        //Do custom stuff here

    }

    public String getLoc_string() {
        return loc_string;
    }

    public void setLocationString(String _latitude, String _longitude) {
        loc_string = _latitude + "," + _longitude;
        persistString(loc_string);
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        // Default value from attribute. Fallback value is set to 0.
        return a.getInt(index, 0);
    }

    //TODO Implement OnSetInitialValue
}
