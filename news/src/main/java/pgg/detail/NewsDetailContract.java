package pgg.detail;

import pgg.data.bean.MessageDetail;
import pgg.lib_common.base.BasePresenter;
import pgg.lib_common.base.BaseView;

/**
 * Created by PDD on 2018/3/10.
 */

public interface NewsDetailContract {

    interface View extends BaseView<Presenter> {

        boolean isActive();

        void showNewsDetail(MessageDetail detail);

    }

    interface Presenter extends BasePresenter {

        /**
         * 获取最新列表
         * @param newsId 新闻id
         */
        void getNewsDetail(String newsId);

    }
}
