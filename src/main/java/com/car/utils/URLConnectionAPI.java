package com.car.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by zhengjie on 2020/5/1.
 */
public class URLConnectionAPI {

    public static String getLocation(String locationUrl) {
        return getString(locationUrl);
    }

    public static String getWeather(String weatherUrl){
        return getString(weatherUrl);
    }

    public static String getString(String Url) {
        //用JAVA发起http请求，并返回json格式的结果
        StringBuffer result = new StringBuffer();
        try {
            URL url = new URL(Url);
            URLConnection conn = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            in.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
