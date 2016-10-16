package org.nicomda.pihome.sqlite;

import java.util.UUID;

/**
 * Created by nicomda on 16/10/16.
 */

public class DB_Metadata {

    interface UserColumns {
        String ID = "id";
        String NAME = "id";
        String EMAIL = "email";
        String PASSWORD = "password";
        String PASSWORD_ENABLED_CONFIG = "password_enabled_config";
        String ID_DEV = "dev_id";

    }

    interface DeviceColumns {
        String ID = "id";
        String TYPE = "type";
        String TITLE = "title";
        String DESCRIPTION = "description";
        String IMAGE = "image";
        String COLOR = "color";
    }

    interface SwitchColumns {
        String ID = "id";
        String IP = "ip";
        String PORT = "port";
        String PASSWORD_ENABLED = "password_enabled";
        String PASSWORD = "password";
        String GPIO = "gpio";
        String PULSE_ENABLED = "pulse_enabled";
        String PULSE_DURATION = "pulse_duration";
        String GPS_ENABLED = "gps_enabled";
        String GPS_DISTANCE = "gps_distance";
        String GPS_LOCATION = "gps_location";
        String NFC_ENABLED = "nfc_enabled";
    }

    public static class User implements UserColumns {
        public static String generarIdUser() {
            return "U-" + UUID.randomUUID().toString();
        }
    }

    public static class Device implements DeviceColumns {
        public static String generarIdDevice() {
            return "D-" + UUID.randomUUID().toString();
        }
    }

    public static class Switch implements SwitchColumns {
        public static String generarIdSwitch() {
            return "S-" + UUID.randomUUID().toString();
        }
    }

    private DB_Metadata() {

    }

}
