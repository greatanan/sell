package com.imooc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "projectUrl")
@Component
public class ProjectUrlConfig {

    public String wechatMpAuthorize;     // 微信公众平台授权url
    public String wechatOpenAuthorize;   //微信开放平台授权url
    public String sell;                  //点餐系统

    public String getWechatMpAuthorize() {
        return wechatMpAuthorize;
    }

    public void setWechatMpAuthorize(String wechatMpAuthorize) {
        this.wechatMpAuthorize = wechatMpAuthorize;
    }

    public String getWechatOpenAuthorize() {
        return wechatOpenAuthorize;
    }

    public void setWechatOpenAuthorize(String wechatOpenAuthorize) {
        this.wechatOpenAuthorize = wechatOpenAuthorize;
    }

    public String getSell() {
        return sell;
    }

    public void setSell(String sell) {
        this.sell = sell;
    }

    @Override
    public String toString() {
        return "ProjectUrlConfig{" +
                "wechatMpAuthorize='" + wechatMpAuthorize + '\'' +
                ", wechatOpenAuthorize='" + wechatOpenAuthorize + '\'' +
                ", sell='" + sell + '\'' +
                '}';
    }
}
