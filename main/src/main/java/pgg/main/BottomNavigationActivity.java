package pgg.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import java.util.List;

import pgg.lib_common.base.BaseActivity;
import pgg.lib_common.base.BaseFragment;
import pgg.lib_common.base.IViewDelegate;
import pgg.lib_common.base.ViewManager;
import pgg.lib_common.utils.ClassUtils;
import pgg.lib_common.widget.NoScrollViewPager;

/**
 * Created by PDD on 2018/3/10.
 */

public class BottomNavigationActivity extends BaseActivity {

    private NoScrollViewPager mPager;
    private List<BaseFragment> mFragments;
    private FragmentAdapter mAdapter;

    private BottomNavigationView.OnNavigationItemSelectedListener OnNavigationItemSelectedListener=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int i=item.getItemId();
            if (i == R.id.navigation_home) {
                mPager.setCurrentItem(0);
                return true;
            } else if (i == R.id.navigation_dashboard) {
                mPager.setCurrentItem(1);
                return true;
            } else if (i == R.id.navigation_notifications) {
                mPager.setCurrentItem(2);
                return true;
            }
            return false;
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_bottom);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.main_navigation);
        navigation.setOnNavigationItemSelectedListener(OnNavigationItemSelectedListener);
        initViewPager();
    }

    private void initViewPager() {
        mFragments = ViewManager.getmViewManager().getAllFragment();//这几个Fragment是主动添加到ViewManager中的
        BaseFragment newsFragment = getNewsFragment();//主动寻找
        mFragments.add(newsFragment);
        mPager = (NoScrollViewPager) findViewById(R.id.container_pager);
        mAdapter = new FragmentAdapter(getSupportFragmentManager(), mFragments);
        mPager.setPagerEnabled(false);
        mPager.setAdapter(mAdapter);
    }

    /**
     * 在News模块中寻找实现的Fragment
     *
     * @return Fragment
     */
    private BaseFragment getNewsFragment() {
        BaseFragment newsFragment = null;
        List<IViewDelegate> viewDelegates = ClassUtils.getObjectsWithInterface(this, IViewDelegate.class, "pgg.news");
        if (viewDelegates != null && !viewDelegates.isEmpty()) {
            newsFragment = viewDelegates.get(0).getBaseFragment("");
        }
        return newsFragment;
    }
}
