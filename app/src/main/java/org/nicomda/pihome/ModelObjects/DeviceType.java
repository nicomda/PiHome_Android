package org.nicomda.pihome.ModelObjects;

/**
 * Created by nicomda on 3/10/16.
 */

public class DeviceType {

    private String devName;
    private Integer devImg;

    public DeviceType(String name, int resourceId){
        devName=name;
        devImg=resourceId;
    }

    public Integer getDevImg() {
        return devImg;
    }

    public String getDevName() {
        return devName;
    }

    public void setDevImg(Integer devImg) {
        this.devImg = devImg;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }
}
