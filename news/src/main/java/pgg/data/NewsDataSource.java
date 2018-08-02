package pgg.data;

import pgg.data.bean.MessageDetail;
import pgg.data.bean.StoryList;
import pgg.lib_common.base.InfoCallback;

/**
 * Created by PDD on 2018/3/10.
 */

public interface NewsDataSource {

    /**
     * 获取当天的新闻列表
     * @param date     日期
     * @param callback 回调
     */
    void getNewsList(String date, InfoCallback<StoryList> callback);

    /**
     * 获取某条新闻详情
     *
     * @param id       新闻Id
     * @param callback 回调
     */
    void getNewsDetail(String id, InfoCallback<MessageDetail> callback);
}
