package com.imooc.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

public class DownloadUtils {

   public static void downloadFile(String fileName, HttpServletResponse response){
       String filename = null;
       try {
            filename = URLEncoder.encode(fileName, "UTF-8");
       } catch (UnsupportedEncodingException e) {
       }
       response.setContentType("application/octet-stream;charset=utf-8");
       response.addHeader("content-disposition","attachment;filename=" + filename);

       try {
           copyFile(getFile("exceltemplate",fileName),response.getOutputStream());
//         copyFile(getFileLocal("c:\\var",File.separator+"redis.pdf"),response.getOutputStream());

       } catch (IOException e) {
       }
   }

    public static File getFile(String path,String name){
       File file = null;
       java.net.URL res = DownloadUtils.class.getClassLoader().getResource(path + "/" + name);
       //因为我们的文件放在了项目里面，部署的时候如果是jar的方式部署，就拿不到文件了，这里我们通过这种方式获取文件
       if(res.toString().startsWith("jar:")){
            try {
                InputStream input = DownloadUtils.class.getClassLoader().getResourceAsStream(path + File.separator + name);
                file=new File(name);
                FileOutputStream out = new FileOutputStream(file);
                int read;
                byte[] bytes = new byte[1024];
                while ((read = input.read(bytes))!= -1){
                     out.write(bytes,0,read);
                }
                file.deleteOnExit();
                return file;
            } catch (IOException e) {
            }
       }else{
            file = new File(res.getFile());
       }

       return file;
    }


    public static File getFileLocal(String path,String name){
        File file = new File(path, name);
        return file;
    }

    public static void copyFile(File file,OutputStream out){
        if(out == null){
            return;
        }
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int len = 0;
            int fileLen = (int)file.length();
            while (len < fileLen){
                int remainedLen = fileLen - len;
                if(remainedLen > 1024){
                    remainedLen = 1024;
                }
                int readLen = fs.read(buffer,0, remainedLen);
                out.write(buffer,0,readLen);
                len += readLen;
            }
        } catch (IOException e) {

        } finally {
            if(fs != null){
                try {
                    fs.close();
                } catch (IOException e) {

                }

            }
        }
    }

}