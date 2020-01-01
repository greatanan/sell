package com.imooc.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 表单校验
 */
@Data
public class OrderForm {

    @NotEmpty(message = "姓名必填")
    private String name;   //买家姓名

    @NotEmpty(message = "手机号必填")
    private String phone;  //买家手机号

    @NotEmpty(message = "地址必填")
    private String address;//买家地址

    @NotEmpty(message = "openid必填")
    private String openid; //买家微信openid

    @NotEmpty(message = "购物车不能为空")
    private String items; // 购物车，也是一个字符串

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "OrderForm{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", openid='" + openid + '\'' +
                ", items='" + items + '\'' +
                '}';
    }
}
