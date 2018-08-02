package pgg.functionmodule.data;

import pgg.functionmodule.data.bean.GirlsParser;

/**
 * Created by PDD on 2018/3/10.
 */

public interface GirlsDataSource {

    interface LoadGirlsCallback {
        void onGirlsLoaded(GirlsParser girlsParser);

        void onDataNotAvailable();
    }

    void getGirls(int size,int page,LoadGirlsCallback callback);
}
