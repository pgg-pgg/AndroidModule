package pgg.lib_common.base;

import android.support.annotation.Keep;
import android.view.View;

/**
 * Created by PDD on 2018/3/10.
 */

@Keep
public interface IViewDelegate {

    BaseFragment getBaseFragment(String name);

    View getView(String name);
}
