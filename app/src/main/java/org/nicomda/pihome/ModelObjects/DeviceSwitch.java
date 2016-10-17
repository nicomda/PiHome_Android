package org.nicomda.pihome.ModelObjects;

/**
 * Created by nicomda on 13/10/16.
 *
 */

public class DeviceSwitch {
    private String id;
    private String ip;
    private String port;
    private String password;
    private String gpio;
    private String pulse_duration;
    private String gps_distance;
    private String gps_location;
    private String password_enabled;
    private String pulse_enabled;
    private String gps_enabled;
    private String nfc_enabled;

    public DeviceSwitch(DeviceSwitch device) {
        id = device.getId();
        ip = device.getIp();
        port = device.getPort();
        gpio = device.getGpio();
        password = device.getPassword();
        pulse_duration = device.getPulse_duration();
        gps_distance = device.getGps_distance();
        gps_location = device.getGps_location();
        password_enabled = device.isPassword_enabled();
        pulse_enabled = device.isPulse_enabled();
        gps_enabled = device.isGps_enabled();
        nfc_enabled = device.isNfc_enabled();

    }

    public DeviceSwitch(String _id, String _ip, String _port, String _password_enabled, String _password,
                        String _gpio, String _pulse_enabled, String _pulse_duration, String _gps_enabled,
                        String _gps_distance, String _gps_location, String _nfc_enabled) {
        id = _id;
        ip = _ip;
        port = _port;
        gpio = _gpio;
        password = _password;
        pulse_duration = _pulse_duration;
        gps_distance = _gps_distance;
        gps_location = _gps_location;
        password_enabled = _password_enabled;
        pulse_enabled = _pulse_enabled;
        gps_enabled = _gps_enabled;
        nfc_enabled = _nfc_enabled;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getGpio() {
        return gpio;
    }

    public void setGpio(String gpio) {
        this.gpio = gpio;
    }

    public String getPulse_duration() {
        return pulse_duration;
    }

    public void setPulse_duration(String pulse_duration) {
        this.pulse_duration = pulse_duration;
    }

    public String getGps_distance() {
        return gps_distance;
    }

    public void setGps_distance(String gps_distance) {
        this.gps_distance = gps_distance;
    }

    public String isPassword_enabled() {
        return password_enabled;
    }

    public void setPassword_enabled(String password_enabled) {
        this.password_enabled = password_enabled;
    }

    public String isPulse_enabled() {
        return pulse_enabled;
    }

    public void setPulse_enabled(String pulse_enabled) {
        this.pulse_enabled = pulse_enabled;
    }

    public String isGps_enabled() {
        return gps_enabled;
    }

    public void setGps_enabled(String gps_enabled) {
        this.gps_enabled = gps_enabled;
    }

    public String isNfc_enabled() {
        return nfc_enabled;
    }

    public void setNfc_enabled(String nfc_enabled) {
        this.nfc_enabled = nfc_enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGps_location() {
        return gps_location;
    }

    public void setGps_location(String gps_location) {
        this.gps_location = gps_location;
    }
}
