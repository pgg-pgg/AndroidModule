package pgg.functionmodule.main;

import java.util.List;

import pgg.functionmodule.data.bean.Girls;
import pgg.functionmodule.data.bean.GirlsParser;
import pgg.lib_common.base.BasePresenter;
import pgg.lib_common.base.BaseView;

/**
 * Created by PDD on 2018/3/11.
 */

public interface GirlsContract {

    interface View extends BaseView<Presenter>{

        boolean isActive();

        void refresh(List<Girls> data);

        void load(List<Girls> data);

        void showError();

        void showNormal();
    }

    interface Presenter extends BasePresenter{
        void getGirls(int page, int size, boolean isRefresh);
    }
}
