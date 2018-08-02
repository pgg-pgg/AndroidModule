package pgg.main;

import pgg.data.bean.StoryList;
import pgg.lib_common.base.BasePresenter;
import pgg.lib_common.base.BaseView;

/**
 * Created by PDD on 2018/3/11.
 */

public interface NewsListContract {

    interface View extends BaseView<Presenter>{

        boolean isActive();

        void showNewsList(StoryList info);
    }


    interface Presenter extends BasePresenter{
        void getNewsMessage(int size,int page,String date);
    }
}
