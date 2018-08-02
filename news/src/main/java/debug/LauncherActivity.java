package debug;

import android.content.Intent;
import android.os.Bundle;

import pgg.detail.NewsDetailActivity;
import pgg.lib_common.base.BaseActivity;

/**
 * Created by PDD on 2018/3/11.
 */

public class LauncherActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在这里传值给需要调试的Activity
        Intent intent = new Intent(this, NewsDetailActivity.class);
        intent.putExtra("id", "9500116");
        startActivity(intent);
        finish();
    }
}
