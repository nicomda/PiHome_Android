package org.nicomda.pihome.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.provider.BaseColumns;

import org.nicomda.pihome.sqlite.DB_Metadata.Device;
import org.nicomda.pihome.sqlite.DB_Metadata.Switch;
import org.nicomda.pihome.sqlite.DB_Metadata.User;

/**
 * Created by nicomda on 17/10/16.
 */

public class DB_PiHome extends SQLiteOpenHelper {

    private static final String DB_NAME = "pihome.db";

    private static final int VERSION_ACTUAL = 1;


    interface Tables {
        String USER = "user";
        String DEVICE = "device";
        String SWITCH = "switch";
    }

    public DB_PiHome(Context contexto) {
        super(contexto, DB_NAME, null, VERSION_ACTUAL);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT UNIQUE NOT NULL,%s TEXT NOT NULL,%s TEXT NOT NULL," +
                        "%s TEXT,%s TEXT)",
                Tables.DEVICE, BaseColumns._ID,
                Device.TITLE, Device.DESCRIPTION, Device.TYPE,
                Device.IMAGE, Device.COLOR));
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT UNIQUE NOT NULL,%s TEXT NOT NULL,%s TEXT NOT NULL," +
                        "%s TEXT UNIQUE NOT NULL,%s TEXT NOT NULL,%s TEXT NOT NULL,%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL,%s TEXT NOT NULL,%s TEXT NOT NULL,%s TEXT NOT NULL," +
                        "FOREIGN KEY(%s) REFERENCES %s(%s)",
                Tables.SWITCH, BaseColumns._ID,
                Switch.IP, Switch.PORT, Switch.PASSWORD_ENABLED,
                Switch.PASSWORD_ENABLED, Switch.GPIO,
                Switch.PULSE_ENABLED, Switch.PULSE_DURATION,
                Switch.GPS_ENABLED, Switch.GPS_DISTANCE,
                Switch.GPS_LOCATION, Switch.NFC_ENABLED,
                Switch.ID, Tables.DEVICE, Device.ID));
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Tables.USER);
        db.execSQL("DROP TABLE IF EXISTS " + Tables.DEVICE);
        db.execSQL("DROP TABLE IF EXISTS " + Tables.SWITCH);

        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                db.setForeignKeyConstraintsEnabled(true);
            } else {
                db.execSQL("PRAGMA foreign_keys=ON");
            }
        }
    }
}
