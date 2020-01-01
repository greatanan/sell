package com.imooc.exception;

import com.imooc.enums.ResultEnum;
import lombok.Data;

/**
 *自定义商品异常
 */
//@Data
public class SellException extends RuntimeException{

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "SellException{" +
                "code=" + code +
                '}';
    }
}
