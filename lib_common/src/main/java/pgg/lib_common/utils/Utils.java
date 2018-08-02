package pgg.lib_common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

/**
 * Created by PDD on 2018/3/10.
 */

public class Utils {

    private static Context context;

    private Utils(){
        throw new UnsupportedOperationException("不合法的操作");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        Utils.context = context.getApplicationContext();
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext(){
        if (context!=null){
            return context;
        }
        throw new NullPointerException("你需要先初始化Context");
    }

    /**
     * View获取Activity的工具
     *
     * @param view
     * @return Activity
     */
    public static @NonNull
    Activity getActivity(View view){
        Context context=view.getContext();
        while (context instanceof ContextWrapper){
            if (context instanceof Activity){
                return (Activity)context;
            }
            context=((ContextWrapper)context).getBaseContext();
        }
        throw new IllegalStateException("View " + view + " is not attached to an Activity");
    }

    /**
     * 获取全局String
     * @param id
     * @return
     */
    public static String getString(@StringRes int id){
        return getContext().getResources().getString(id);
    }

    /**
     * 判断app是否为debug模式
     * @return
     */
    public static boolean isAppDebug(){
        if (StringUtils.isSpace(context.getPackageName())) return false;
        try {
            PackageManager pm=context.getPackageManager();
            ApplicationInfo ai = pm.getApplicationInfo(context.getPackageName(), 0);
            return (ai!=null)&&(ai.flags==ApplicationInfo.FLAG_DEBUGGABLE);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将Fragment添加到对应的容器中
     * @param fragmentManage
     * @param fragment
     * @param fragmentId
     */
    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManage, @NonNull Fragment fragment,@NonNull int fragmentId){
        checkNotNull(fragmentManage);
        checkNotNull(fragment);
        FragmentTransaction fragmentTransaction = fragmentManage.beginTransaction();
        fragmentTransaction.add(fragmentId,fragment);
        fragmentTransaction.commit();
    }

    public static <T>T checkNotNull(T obj){
        if (obj==null){
            throw new NullPointerException("对象为空");
        }
        return obj;
    }
}
