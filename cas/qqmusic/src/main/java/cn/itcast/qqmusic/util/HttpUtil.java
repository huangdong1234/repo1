package cn.itcast.qqmusic.util;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * @author Xuejun Yang
 * @version V1.0
 * @description: TODO
 * @date 2019/6/11 13:48
 */
public class HttpUtil {


    public static String get(String urlStr ){
        String message="";
        try {
            URL url=new URL(urlStr);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5*1000);
            connection.connect();
            InputStream inputStream=connection.getInputStream();
            byte[] data=new byte[10240];
            StringBuffer sb=new StringBuffer();
            int length=0;
            while ((length=inputStream.read(data))!=-1){
                String s=new String(data,0,length, Charset.forName("utf-8"));
                sb.append(s);
            }
            message=sb.toString();
            inputStream.close();
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    public static void main(String[] args) {
        String s = HttpUtil.get("http://localhost:9107/cart/addGoodsToCartList.do?itemId=741524&num=1");
        System.out.println(s);
    }
}
