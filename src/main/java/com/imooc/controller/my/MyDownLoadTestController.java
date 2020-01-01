package com.imooc.controller.my;

import com.imooc.utils.DownloadUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;

@RestController
public class MyDownLoadTestController {

    @GetMapping("/test")
    public void detail(HttpServletResponse response) {
        DownloadUtils.downloadFile("redis.pdf",response);
    }

} 