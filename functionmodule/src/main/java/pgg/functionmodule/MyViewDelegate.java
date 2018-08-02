package pgg.functionmodule;

import android.view.View;

import pgg.lib_common.base.BaseFragment;
import pgg.lib_common.base.IViewDelegate;

/**
 * Created by PDD on 2018/3/11.
 */

public class MyViewDelegate implements IViewDelegate {


    @Override
    public BaseFragment getBaseFragment(String name) {
        return GirlsFragment.newInstance();
    }

    @Override
    public View getView(String name) {
        return null;
    }
}
