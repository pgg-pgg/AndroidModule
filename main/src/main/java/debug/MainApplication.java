package debug;

import com.orhanobut.logger.Logger;

import pgg.lib_common.base.BaseApplication;
import pgg.lib_common.http.HttpClient;
import pgg.lib_common.http.OnResultListener;

/**
 * Created by PDD on 2018/3/10.
 */

public class MainApplication extends BaseApplication {

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
        HttpClient build = new HttpClient.Builder().baseUrl("http://gank.io/api/data/")
                .url("福利/10/1")
                .build();
        build.get(new OnResultListener<String>(){
            @Override
            public void onSuccess(String result) {
                Logger.e(result);
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
