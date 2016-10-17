package org.nicomda.pihome.ModelObjects;

/**
 * Created by nicomda on 13/10/16.
 * 
 */

public class DeviceSwitch {
    private int id;
    private String ip;
    private int port;
    private String password;
    private int gpio;
    private int pulse_duration;
    private int gps_distance;
    private String gps_location;
    private boolean password_enabled;
    private boolean pulse_enabled;
    private boolean gps_enabled;
    private boolean nfc_enabled;

    DeviceSwitch(DeviceSwitch device) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getGpio() {
        return gpio;
    }

    public void setGpio(int gpio) {
        this.gpio = gpio;
    }

    public int getPulse_duration() {
        return pulse_duration;
    }

    public void setPulse_duration(int pulse_duration) {
        this.pulse_duration = pulse_duration;
    }

    public int getGps_distance() {
        return gps_distance;
    }

    public void setGps_distance(int gps_distance) {
        this.gps_distance = gps_distance;
    }

    public boolean isPassword_enabled() {
        return password_enabled;
    }

    public void setPassword_enabled(boolean password_enabled) {
        this.password_enabled = password_enabled;
    }

    public boolean isPulse_enabled() {
        return pulse_enabled;
    }

    public void setPulse_enabled(boolean pulse_enabled) {
        this.pulse_enabled = pulse_enabled;
    }

    public boolean isGps_enabled() {
        return gps_enabled;
    }

    public void setGps_enabled(boolean gps_enabled) {
        this.gps_enabled = gps_enabled;
    }

    public boolean isNfc_enabled() {
        return nfc_enabled;
    }

    public void setNfc_enabled(boolean nfc_enabled) {
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
