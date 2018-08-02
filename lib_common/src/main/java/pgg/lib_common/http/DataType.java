package pgg.lib_common.http;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * Created by PDD on 2018/3/10.
 */

public class DataType {

    /*返回数据为String*/
    public static final int STRING = 1;
    /*返回数据为xml类型*/
    public static final int XML = 2;
    /*返回数据为json对象*/
    public static final int JSON_OBJECT = 3;
    /*返回数据为json数组*/
    public static final int JSON_ARRAY = 4;


    @Retention(RetentionPolicy.SOURCE)
    @IntDef({STRING,XML,JSON_ARRAY,JSON_OBJECT})
    public @interface Type{}
}
