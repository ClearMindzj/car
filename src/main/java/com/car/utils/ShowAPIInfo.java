package com.car.utils;

import com.car.consts.Carconst;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.util.StringUtils;

/**
 * Created by zhengjie on 2020/5/4.
 */
public class ShowAPIInfo {

    //传个URL参数
    public static JsonObject Location(String locationUrl){
        String result=URLConnectionAPI.getLocation(locationUrl);
        JsonObject jsonObject=new JsonParser().parse(result).getAsJsonObject();
        JsonObject addressObject=jsonObject.get("regeocode").getAsJsonObject().get("addressComponent").getAsJsonObject();
        return addressObject;
    }
    public static String specificLocation(JsonObject addressObject){
        StringBuilder stringBuilder=new StringBuilder();
        String province=addressObject.get("province").getAsString();
        String city="";
        if(isProvince(addressObject)){
             city=addressObject.get("city").getAsString();
        }

        String district=addressObject.get("district").getAsString();
        String specificLocation=stringBuilder.append(province).append(city).append(district).toString();
        return specificLocation;
    }

    //返回一个市作为天气API使用的参数
    public static String cityLocation(JsonObject addressObject){
        String city;
        if(isProvince(addressObject)){
            city=addressObject.get("city").getAsString();
        }else {
            city=addressObject.get("province").getAsString();
        }

        return city;
    }

    //得到天气数据数组
    public static JsonObject getNow(String weatherUrl){
        String result=URLConnectionAPI.getWeather(weatherUrl);
        JsonObject jsonObject=new JsonParser().parse(result).getAsJsonObject();
        JsonObject nowObject=jsonObject.get("HeWeather6").getAsJsonArray().get(0).getAsJsonObject().get("now").getAsJsonObject();
        return nowObject ;
    }
    //获取风向
    public static String getWindDirection(JsonObject nowObject){
        String windDirection=nowObject.get("wind_dir").getAsString();
        return windDirection;
    }
    //获取风速
    public static String getWindSpeed(JsonObject nowObject){
        String windSpeed=nowObject.get("wind_spd").getAsString();
        return windSpeed;
    }
    //获取温度
    public static String getTemperature(JsonObject nowObject){
        String temperature=nowObject.get("tmp").getAsString();
        return temperature;
    }
    //获取压强
    public static String getPressure(JsonObject nowObject){
        String pressure=nowObject.get("pres").getAsString();
        return pressure;
    }

    //判断是否为直辖市
    public static boolean isProvince(JsonObject addressObject){
        if(addressObject.get("city").isJsonArray()){
            return false;
        }else {
            return true;
        }
    }


}
