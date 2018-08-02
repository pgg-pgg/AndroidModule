package pgg.functionmodule.data.source;

import pgg.functionmodule.Constants;
import pgg.functionmodule.data.GirlsDataSource;
import pgg.functionmodule.data.bean.GirlsParser;
import pgg.lib_common.http.DataType;
import pgg.lib_common.http.HttpClient;
import pgg.lib_common.http.OnResultListener;

/**
 * Created by PDD on 2018/3/10.
 */

public class RemoteGirlsDataSource implements GirlsDataSource {

    @Override
    public void getGirls(int size, int page, final LoadGirlsCallback callback) {
        HttpClient client = new HttpClient.Builder()
                .baseUrl(Constants.GAN_HUO_API)
                .url("福利/" + size + "/" + page)
                .bodyType(DataType.JSON_OBJECT, GirlsParser.class)
                .build();
        client.get(new OnResultListener<GirlsParser>(){
            @Override
            public void onSuccess(GirlsParser result) {
                callback.onGirlsLoaded(result);
            }

            @Override
            public void onError(int code, String message) {
                callback.onDataNotAvailable();
            }

            @Override
            public void onFailure(String message) {
                callback.onDataNotAvailable();
            }
        });
    }
}
