package pgg.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;

import pgg.lib_common.base.BaseActivity;
import pgg.lib_common.base.ViewManager;
import pgg.lib_common.utils.ToastUtils;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private long mExitTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_main);
        findViewById(R.id.news_button).setOnClickListener(this);
        findViewById(R.id.girls_button).setOnClickListener(this);
        findViewById(R.id.fragment_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.news_button) {
            //跳转到NewsCenterActivity
            ARouter.getInstance().build("/news/center").navigation();
        } else if (v.getId() == R.id.girls_button) {
            //跳转到GirlsActivity
            ARouter.getInstance().build("/girls/list").navigation();
        } else if (v.getId() == R.id.fragment_button) {
            startActivity(new Intent(this, BottomNavigationActivity.class));
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            //两秒之内按返回键就会退出
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                ToastUtils.showShortToast(getString(R.string.main_app_exit_hint));
                mExitTime = System.currentTimeMillis();
            } else {
                ViewManager.getmViewManager().exitApp(this);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
