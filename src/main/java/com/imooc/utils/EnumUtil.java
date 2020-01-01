package com.imooc.utils;

import com.imooc.enums.CodeEnum;

/**
 * 通过code获取枚举
 */
public class EnumUtil {

    //返回继承CodeEnum枚举
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each: enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
