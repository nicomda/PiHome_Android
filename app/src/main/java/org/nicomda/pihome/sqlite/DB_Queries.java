package org.nicomda.pihome.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import org.nicomda.pihome.ModelObjects.DeviceSwitch;
import org.nicomda.pihome.sqlite.DB_Metadata.Device;
import org.nicomda.pihome.sqlite.DB_Metadata.Switch;

import java.util.ArrayList;

/**
 * Created by nicomda on 17/10/16.
 */

public final class DB_Queries {
    private static DB_PiHome database;

    private static DB_Queries instance = new DB_Queries();

    private DB_Queries() {

    }

    public static DB_Queries getInstance(Context context) {
        if (database == null) {
            database = new DB_PiHome(context);
        }
        return instance;
    }

    private static final String DEVICE_JOIN_DEVICE_SWITCH = "device " + "INNER JOIN switch " + "ON device.id = switch.id ";

    private final String[] proyDevice = new String[]{
            DB_PiHome.Tables.DEVICE + "." + Device.ID,
            Device.TYPE,
            Device.TITLE,
            Device.DESCRIPTION,
            Device.IMAGE,
            Device.COLOR};

    public ArrayList<org.nicomda.pihome.ModelObjects.Device> getDevices() {
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + DB_PiHome.Tables.DEVICE, null);
        ArrayList<org.nicomda.pihome.ModelObjects.Device> devices = new ArrayList<>();
        for (int i = 0; c.moveToNext(); i++) {
            devices.add(i, new org.nicomda.pihome.ModelObjects.Device(
                    c.getString(c.getColumnIndex(Device.ID)),
                    c.getString(c.getColumnIndex(Device.TITLE)),
                    c.getString(c.getColumnIndex(Device.DESCRIPTION)),
                    c.getString(c.getColumnIndex(Device.IMAGE)),
                    c.getString(c.getColumnIndex(Device.TYPE)),
                    c.getString(c.getColumnIndex(Device.COLOR))
            ));
        }

        return devices;
    }

    public org.nicomda.pihome.ModelObjects.Device getDeviceByName(String name) {
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + DB_PiHome.Tables.DEVICE + " WHERE " + Device.TITLE + " = '" + name + "'", null);
        c.moveToFirst();
        c.getString(c.getColumnIndex(Device.ID));
        return new org.nicomda.pihome.ModelObjects.Device(
                c.getString(c.getColumnIndex(Device.ID)),
                c.getString(c.getColumnIndex(Device.TITLE)),
                c.getString(c.getColumnIndex(Device.DESCRIPTION)),
                c.getString(c.getColumnIndex(Device.IMAGE)),
                c.getString(c.getColumnIndex(Device.TYPE)),
                c.getString(c.getColumnIndex(Device.COLOR))
        );
    }

    public org.nicomda.pihome.ModelObjects.DeviceSwitch getSwitchByID(String id) {
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + DB_PiHome.Tables.SWITCH + " WHERE " + Device.ID + " = '" + id + "'", null);
        c.moveToFirst();
        c.getString(c.getColumnIndex(Device.ID));
        return new org.nicomda.pihome.ModelObjects.DeviceSwitch(
                c.getString(c.getColumnIndex(Switch.ID)),
                c.getString(c.getColumnIndex(Switch.IP)),
                c.getString(c.getColumnIndex(Switch.PORT)),
                c.getString(c.getColumnIndex(Switch.PASSWORD_ENABLED)),
                c.getString(c.getColumnIndex(Switch.PASSWORD)),
                c.getString(c.getColumnIndex(Switch.GPIO)),
                c.getString(c.getColumnIndex(Switch.PULSE_ENABLED)),
                c.getString(c.getColumnIndex(Switch.PULSE_ENABLED)),
                c.getString(c.getColumnIndex(Switch.GPS_ENABLED)),
                c.getString(c.getColumnIndex(Switch.GPS_DISTANCE)),
                c.getString(c.getColumnIndex(Switch.GPS_LOCATION)),
                c.getString(c.getColumnIndex(Switch.NFC_ENABLED))
        );
    }

    public String insertDevice(org.nicomda.pihome.ModelObjects.Device device) {
        SQLiteDatabase db = database.getWritableDatabase();
        // Generar Pk
        String idDevice = Device.generarIdDevice();

        ContentValues values = new ContentValues();
        values.put(Device.ID, idDevice);
        values.put(Device.TITLE, device.getName());
        values.put(Device.DESCRIPTION, device.getAdditional_info());
        values.put(Device.TYPE, device.getType());
        values.put(Device.IMAGE, device.getImg_res());
        values.put(Device.COLOR, device.getColor_res());

        // Insertar cabecera
        db.insertOrThrow(DB_PiHome.Tables.DEVICE, null, values);

        return idDevice;
    }

    public boolean updateDevice(org.nicomda.pihome.ModelObjects.Device newDevice) {
        SQLiteDatabase db = database.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Device.TITLE, newDevice.getName());
        values.put(Device.DESCRIPTION, newDevice.getAdditional_info());
        values.put(Device.TYPE, newDevice.getType());
        values.put(Device.IMAGE, newDevice.getImg_res());
        values.put(Device.COLOR, newDevice.getColor_res());

        String whereClause = String.format("%s=?", Device.ID);
        String[] whereArgs = {newDevice.getId().toString()};

        int result = db.update(DB_PiHome.Tables.DEVICE, values, whereClause, whereArgs);

        return result > 0;
    }

    public boolean deleteDeviceById(String id) {
        SQLiteDatabase db = database.getWritableDatabase();

        String whereClause = Device.ID + "=?";
        String[] whereArgs = {id};

        int result = db.delete(DB_PiHome.Tables.DEVICE, whereClause, whereArgs);

        return result > 0;
    }

    public ArrayList<DeviceSwitch> getSwitchs() {
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + DB_PiHome.Tables.SWITCH, null);
        ArrayList<DeviceSwitch> switches = new ArrayList<>();
        for (int i = 0; c.moveToNext(); i++) {
            switches.add(i, new DeviceSwitch(
                    c.getString(c.getColumnIndex(Switch.ID)),
                    c.getString(c.getColumnIndex(Switch.IP)),
                    c.getString(c.getColumnIndex(Switch.PORT)),
                    c.getString(c.getColumnIndex(Switch.PASSWORD_ENABLED)),
                    c.getString(c.getColumnIndex(Switch.PASSWORD)),
                    c.getString(c.getColumnIndex(Switch.GPIO)),
                    c.getString(c.getColumnIndex(Switch.PULSE_ENABLED)),
                    c.getString(c.getColumnIndex(Switch.PULSE_DURATION)),
                    c.getString(c.getColumnIndex(Switch.GPS_ENABLED)),
                    c.getString(c.getColumnIndex(Switch.GPS_DISTANCE)),
                    c.getString(c.getColumnIndex(Switch.GPS_LOCATION)),
                    c.getString(c.getColumnIndex(Switch.NFC_ENABLED))
            ));
        }


        return switches;
    }

    public String insertSwitch(DeviceSwitch _switch) {
        SQLiteDatabase db = database.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(Switch.ID, _switch.getId());
        valores.put(Switch.IP, _switch.getIp());
        valores.put(Switch.PORT, _switch.getPort());
        valores.put(Switch.PASSWORD_ENABLED, _switch.isPassword_enabled());
        valores.put(Switch.PASSWORD, _switch.getPassword());
        valores.put(Switch.GPIO, _switch.getGpio());
        valores.put(Switch.PULSE_ENABLED, _switch.isPulse_enabled());
        valores.put(Switch.PULSE_DURATION, _switch.getPulse_duration());
        valores.put(Switch.GPS_ENABLED, _switch.isGps_enabled());
        valores.put(Switch.GPS_DISTANCE, _switch.getGps_distance());
        valores.put(Switch.GPS_LOCATION, _switch.getGps_location());
        valores.put(Switch.NFC_ENABLED, _switch.isNfc_enabled());

        db.insertOrThrow(DB_PiHome.Tables.SWITCH, null, valores);

        return String.format("%s", _switch.getId());

    }

    public boolean updateSwitch(DeviceSwitch _switch) {
        SQLiteDatabase db = database.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(Switch.IP, _switch.getIp());
        valores.put(Switch.PORT, _switch.getPort());
        valores.put(Switch.PASSWORD_ENABLED, _switch.isPassword_enabled());
        valores.put(Switch.PASSWORD, _switch.getPassword());
        valores.put(Switch.GPIO, _switch.getGpio());
        valores.put(Switch.PULSE_ENABLED, _switch.isPulse_enabled());
        valores.put(Switch.PULSE_DURATION, _switch.getPulse_duration());
        valores.put(Switch.GPS_ENABLED, _switch.isGps_enabled());
        valores.put(Switch.GPS_DISTANCE, _switch.getGps_distance());
        valores.put(Switch.GPS_LOCATION, _switch.getGps_location());
        valores.put(Switch.NFC_ENABLED, _switch.isNfc_enabled());

        String selection = String.format("%s=?", Switch.ID);
        final String[] whereArgs = {String.valueOf(_switch.getId())};

        int result = db.update(DB_PiHome.Tables.SWITCH, valores, selection, whereArgs);

        return result > 0;
    }

    public boolean deleteSwitchById(String id) {
        SQLiteDatabase db = database.getWritableDatabase();

        String selection = String.format("%s=?", Switch.ID);
        String[] whereArgs = {id};

        int result = db.delete(DB_PiHome.Tables.SWITCH, selection, whereArgs);

        return result > 0;
    }

    public SQLiteDatabase getDb() {
        return database.getWritableDatabase();
    }
}
