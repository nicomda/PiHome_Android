package org.nicomda.pihome.ModelObjects;

/**
 * Created by nicomda on 3/10/16.
 */

public class Device {
    private String name;
    private String additional_info;
    private String type;
    private Integer img_res,id;

    public Device(Integer _id, String _name, Integer _img_res, String _additional_info, String _type) {
        name = _name;
        additional_info = _additional_info;
        img_res = _img_res;
        id=_id;
        type = _type;

    }

    public Integer getImg_res() {
        return img_res;
    }

    public String getAdditional_info() {
        return additional_info;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setAdditional_info(String additional_info) {
        this.additional_info = additional_info;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setImg_res(Integer img_res) {
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
}
