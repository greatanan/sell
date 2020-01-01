package com.imooc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 购物车对象，一共两个字段
 */
//@Data
@AllArgsConstructor
public class CartDTO {

    private String productId;          //商品Id.
    private Integer productQuantity;   //商品数量

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    @Override
    public String toString() {
        return "CartDTO{" +
                "productId='" + productId + '\'' +
                ", productQuantity=" + productQuantity +
                '}';
    }

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }

    public CartDTO() {
    }
}
