package pgg.detail;

import pgg.data.NewsDataSource;
import pgg.data.bean.MessageDetail;
import pgg.data.source.RemoteNewsDataSource;
import pgg.lib_common.base.InfoCallback;

/**
 * Created by PDD on 2018/3/10.
 */

public class NewsDetailPresenter implements NewsDetailContract.Presenter{


    private NewsDetailContract.View mView;
    private NewsDataSource mDataSource;

    public NewsDetailPresenter(NewsDetailContract.View view){
        this.mView=view;
        mDataSource=new RemoteNewsDataSource();
        mView.setPresenter(this);
    }


    @Override
    public void start() {

    }

    @Override
    public void getNewsDetail(String newsId) {
        mDataSource.getNewsDetail(newsId, new InfoCallback<MessageDetail>() {
            @Override
            public void onSuccess(MessageDetail info) {
                if (mView.isActive()){
                    mView.showNewsDetail(info);
                }
            }

            @Override
            public void onError(int code, String message) {

            }
        });
    }
}
