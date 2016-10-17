package org.nicomda.pihome.ModelObjects;

/**
 * Created by nicomda on 3/10/16.
 */

public class Device {
    private String name;
    private String additional_info;
    private String type;
    private String img_res;
    private String id;
    private String color_res;

    public Device(String _id, String _name, String _additional_info, String _img_res, String _type, String _color_res) {
        name = _name;
        additional_info = _additional_info;
        img_res = _img_res;
        id=_id;
        type = _type;
        color_res = _color_res;

    }

    public Device(Device device) {
        name = device.getName();
        additional_info = device.getAdditional_info();
        img_res = device.getImg_res();
        id = device.getId();
        type = device.getType();
        color_res = device.getColor_res();

    }

    public String getImg_res() {
        return img_res;
    }

    public String getAdditional_info() {
        return additional_info;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setAdditional_info(String additional_info) {
        this.additional_info = additional_info;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImg_res(String img_res) {
        this.img_res = img_res;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor_res() {
        return color_res;
    }

    public void setColor_res(String color_res) {
        this.color_res = color_res;
    }
}
