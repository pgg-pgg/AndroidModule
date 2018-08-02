package pgg.lib_common.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.support.annotation.Keep;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 所有View的管理类
 * Created by PDD on 2018/3/10.
 */

@Keep
public class ViewManager {

    private static Stack<Activity> activityStack;
    private static List<BaseFragment> baseFragmentList;


    private ViewManager(){}
    public static ViewManager getmViewManager(){
        return ViewManagerHolder.sInstance;
    }

    private static class ViewManagerHolder{
        private static final ViewManager sInstance = new ViewManager();
    }
    /**
     * 添加指定Fragment到集合
     */
    public void addFragment(int index, BaseFragment fragment){
        if (baseFragmentList==null){
            baseFragmentList=new ArrayList<>();
        }
        baseFragmentList.add(index,fragment);
    }

    public BaseFragment getFragment(int index){
        if (baseFragmentList!=null){
            return baseFragmentList.get(index);
        }
        return null;
    }
    public List<BaseFragment> getAllFragment() {
        if (baseFragmentList != null) {
            return baseFragmentList;
        }
        return null;
    }


    /**
     * 添加指定Activity到堆栈
     */
    public void addActivity(Activity activity){
        if (activityStack==null){
            activityStack=new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束栈中所有的Activity
     */
    public void finishAllActivity(){
        for (int i=0,size=activityStack.size();i<size;i++){
            if (null!=activityStack.get(i)){
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void exitApp(Context context){
        try {
            finishAllActivity();
            //杀死后台进程需要在AndroidManifest中声明android.permission.KILL_BACKGROUND_PROCESSES；
            ActivityManager am= (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            am.killBackgroundProcesses(context.getPackageName());
        }catch (Exception e){
            e.printStackTrace();
            Log.e("ActivityManager", "app exit" + e.getMessage());
        }
    }
}
