package pgg.lib_common.http;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persist;
import org.simpleframework.xml.core.Persister;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pgg.lib_common.utils.StringUtils;

/**
 * 服务器返回数据的解析工具；
 * 支持XML,json对象,json数组
 * Created by PDD on 2018/3/10.
 */

public class DataParseUtil {

    private DataParseUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 解析json对象
     *
     * @param string 要解析的json
     * @param clazz  解析类
     */
    public static <T> T parseObject(String string, Class<T> clazz){
        return new Gson().fromJson(string, clazz);
    }

    /**
     * 解析json数组为ArrayList
     *
     * @param json  要解析的json
     * @param clazz 解析类
     * @return ArrayList
     */
    public static <T> ArrayList<T> parseToArrayList(String json, Class<T> clazz){
        Type type = new TypeToken<Object>() {
        }.getType();
        ArrayList<JsonObject> list = new Gson().fromJson(json, type);
        ArrayList<T> object=new ArrayList<>();
        for (JsonObject jsonobject:list){
            if (jsonobject!=null){
                object.add(new Gson().fromJson(jsonobject,clazz));
            }
        }
        return object;
    }

    /**
     * 解析json数组为List
     *
     * @param json  要解析的json
     * @param clazz 解析类
     * @return List
     */
    public static <T> List<T> parseToList(String json, Class<T[]> clazz) {
        return Arrays.asList(new Gson().fromJson(json,clazz));
    }
    /**
     * 解析Xml格式数据
     *
     * @param json  要解析的json
     * @param clazz 解析类
     */
    public static Object parseXml(String json, Class<?> clazz){
        try {
            if (!StringUtils.isEmpty(json)&&clazz!=null){
                Serializer serializer= new Persister();
                InputStreamReader inputStreamReader = new InputStreamReader(new ByteArrayInputStream(json.getBytes("UTF-8")), "utf-8");
                return serializer.read(clazz,inputStreamReader);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
