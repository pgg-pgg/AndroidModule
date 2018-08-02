package pgg.functionmodule;

import pgg.lib_common.base.IApplicationDelegate;
import pgg.lib_common.base.ViewManager;

/**
 * Created by PDD on 2018/3/11.
 */

public class MyDelegate implements IApplicationDelegate {


    @Override
    public void onCreated() {
        ViewManager.getmViewManager().addFragment(0,GirlsFragment.newInstance());
    }

    @Override
    public void onLowMemory() {

    }

    @Override
    public void onTrimMemory(int level) {

    }

    @Override
    public void onTerminate() {

    }
}
