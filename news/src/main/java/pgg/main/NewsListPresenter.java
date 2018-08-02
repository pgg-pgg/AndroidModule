package pgg.main;

import pgg.data.bean.StoryList;
import pgg.data.source.RemoteNewsDataSource;
import pgg.lib_common.base.InfoCallback;

/**
 * Created by PDD on 2018/3/11.
 */

public class NewsListPresenter implements NewsListContract.Presenter {


    private NewsListContract.View view;
    private final RemoteNewsDataSource mDataSource;

    public NewsListPresenter(NewsListContract.View view){
        this.view =view;
        mDataSource = new RemoteNewsDataSource();
        view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getNewsMessage(int size, int page, String date) {
        mDataSource.getNewsList(date, new InfoCallback<StoryList>() {
            @Override
            public void onSuccess(StoryList info) {
                if (view.isActive()){
                    view.showNewsList(info);
                }
            }

            @Override
            public void onError(int code, String message) {

            }
        });
    }
}
