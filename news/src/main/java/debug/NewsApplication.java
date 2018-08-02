package debug;

import android.app.Application;

import com.orhanobut.logger.Logger;

import pgg.Constants;
import pgg.data.bean.StoryList;
import pgg.lib_common.base.BaseApplication;
import pgg.lib_common.http.DataType;
import pgg.lib_common.http.HttpClient;
import pgg.lib_common.http.OnResultListener;

/**
 * Created by PDD on 2018/3/10.
 */

public class NewsApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        login();
    }


    /**
     * 在这里模拟登陆，然后拿到sessionId或者Token
     * 这样就能够在组件请求接口了
     */
    private void login() {
        HttpClient client = new HttpClient.Builder()
                .baseUrl(Constants.ZHIHU_DAILY_BEFORE_MESSAGE)
                .url("20170419")
                .bodyType(DataType.JSON_OBJECT, StoryList.class)
                .build();
        client.get(new OnResultListener<StoryList>() {

            @Override
            public void onSuccess(StoryList result) {
                Logger.e(result.toString());
            }

            @Override
            public void onError(int code, String message) {
                Logger.e(message);
            }

            @Override
            public void onFailure(String message) {
                Logger.e(message);
            }
        });
    }

}
