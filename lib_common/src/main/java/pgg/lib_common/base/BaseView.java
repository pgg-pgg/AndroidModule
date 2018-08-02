package pgg.lib_common.base;

import android.support.annotation.Keep;

/**
 * Created by PDD on 2018/3/10.
 */

@Keep
public interface BaseView<T> {
    void setPresenter(T presenter);
}
