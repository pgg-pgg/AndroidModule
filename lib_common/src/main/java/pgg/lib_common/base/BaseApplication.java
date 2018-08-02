package pgg.lib_common.base;

import android.app.Application;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import java.util.List;

import pgg.lib_common.utils.ClassUtils;
import pgg.lib_common.utils.Utils;

/**
 * 要想使用BaseApplication，必须在组件中实现自己的Application，并且继承BaseApplication；
 * 组件中实现的Application必须在debug包中的AndroidManifest.xml中注册，否则无法使用；
 * 组件的Application需置于java/debug文件夹中，不得放于主代码；
 * 组件中获取Context的方法必须为:Utils.getContext()，不允许其他写法；
 * Created by PDD on 2018/3/10.
 */

public class BaseApplication extends Application {

    public static final String ROOT_PACKAGE = "pgg";

    private static BaseApplication mInstance;

    private List<IApplicationDelegate> mApplicationDelegate;

    public static BaseApplication getmInstance() {
        return mInstance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
        Logger.init("pattern").logLevel(LogLevel.FULL);
        Utils.init(this);
        mApplicationDelegate= ClassUtils.getObjectsWithInterface(this,IApplicationDelegate.class,ROOT_PACKAGE);
        for (IApplicationDelegate delegate : mApplicationDelegate) {
            delegate.onCreated();
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        for (IApplicationDelegate delegate:mApplicationDelegate){
            delegate.onTerminate();
        }
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        for (IApplicationDelegate delegate:mApplicationDelegate){
            delegate.onLowMemory();
        }
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        for (IApplicationDelegate delegate:mApplicationDelegate){
            delegate.onTrimMemory(level);
        }
    }
}
