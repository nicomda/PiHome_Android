package org.nicomda.pihome.UI.DeviceConfigFragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import org.nicomda.pihome.R;

/**
 * Created by nicomda on 6/10/16.
 * Dialog to change device's description onclick
 */

public class ChangeDeviceDescriptionDialogFragment extends DialogFragment {
    private EditText description;
    private SharedPreferences prefs;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        getContext().setTheme(R.style.Theme_MyDialog);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View content=inflater.inflate(R.layout.dialog_change_device_description,null);
        builder.setTitle(R.string.device_subtitle_set)
                .setView(content);
        description=(EditText)content.findViewById(R.id.device_change_description);
        builder.setPositiveButton(R.string.device_title_button_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        prefs = PreferenceManager.getDefaultSharedPreferences(getDialog().getContext());
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("devicesubtitle", description.getText().toString());
                        editor.commit();
                    }
                })
                .setNegativeButton(R.string.device_title_button_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        getDialog().cancel();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

}
