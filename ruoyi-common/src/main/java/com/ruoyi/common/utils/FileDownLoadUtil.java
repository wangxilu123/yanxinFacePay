package com.ruoyi.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class FileDownLoadUtil {
	public static String download(String urlString,String savePath) throws Exception {
		
		String filename = urlString.substring(urlString.indexOf("data")+5,urlString.indexOf("?"));
        // 构造URL    
        URL url = new URL(urlString);    
        // 打开连接    
        URLConnection con = url.openConnection();    
        //设置请求超时为5s    
        con.setConnectTimeout(5*1000);    
        // 输入流    
        InputStream is = con.getInputStream();    
        
        // 1K的数据缓冲    
        byte[] bs = new byte[1024];    
        // 读取到的数据长度    
        int len;    
        // 输出的文件流    
       File sf=new File(savePath);    
       if(!sf.exists()){    
           sf.mkdirs();    
       }    
       OutputStream os = new FileOutputStream(sf.getPath()+"\\"+filename);    
        // 开始读取    
        while ((len = is.read(bs)) != -1) {    
          os.write(bs, 0, len);    
        }    
        // 完毕，关闭所有链接    
        os.close();    
        is.close();
        System.out.print(sf.getPath()+"\\"+filename);
		return sf.getPath()+"\\"+filename;
    }  
	
	public static void main(String[] args) throws Exception  
	{  
	    download("http://yanxinfacepay.oss-cn-beijing.aliyuncs.com/data/1582185495904.jpg?Expires=1897545490&OSSAccessKeyId=LTAI4FkKkhPSCc8fkVzc52jo&Signature=uKid/nNjMHFRwwL8KG5qkdF/b5Y%3D","d:\\image\\");    
	}  
}
