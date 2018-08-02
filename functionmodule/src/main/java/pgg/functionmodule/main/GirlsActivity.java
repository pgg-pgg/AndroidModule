package pgg.functionmodule.main;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;

import pgg.functionmodule.R;
import pgg.lib_common.base.BaseActionBarActivity;
import pgg.lib_common.base.BaseActivity;

/**
 * Created by PDD on 2018/3/11.
 */
@Route(path = "/girls/list")
public class GirlsActivity extends BaseActionBarActivity {


    private GirlsView mView;
    private GirlsContract.Presenter mPresenter;

    @Override
    protected int setTitleId() {
        return R.string.function_girls_activity_title;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = new GirlsView(this);
        setContentView(mView);
        mPresenter = new GirlsPresenter(mView);
        mPresenter.start();
    }
}
