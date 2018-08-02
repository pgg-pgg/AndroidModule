package pgg.functionmodule.main;

import pgg.functionmodule.data.GirlsDataSource;
import pgg.functionmodule.data.bean.GirlsParser;
import pgg.functionmodule.data.source.RemoteGirlsDataSource;

/**
 * Created by PDD on 2018/3/11.
 */

public class GirlsPresenter implements GirlsContract.Presenter {


    private GirlsContract.View view;
    private RemoteGirlsDataSource mData;

    public GirlsPresenter(GirlsContract.View view){
        this.view =view;
        mData = new RemoteGirlsDataSource();
        view.setPresenter(this);
    }

    @Override
    public void start() {
        getGirls(20,1,true);
    }

    @Override
    public void getGirls(int page, int size, final boolean isRefresh) {
        mData.getGirls(size, page, new GirlsDataSource.LoadGirlsCallback() {
            @Override
            public void onGirlsLoaded(GirlsParser girlsParser) {
                if (isRefresh){
                    view.refresh(girlsParser.getResults());
                }else {
                    view.load(girlsParser.getResults());
                }
                view.showError();
            }

            @Override
            public void onDataNotAvailable() {
                if (isRefresh) {
                    view.showError();
                }
            }
        });
    }
}
