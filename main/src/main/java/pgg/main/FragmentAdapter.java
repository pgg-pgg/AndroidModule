package pgg.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import pgg.lib_common.base.BaseFragment;

/**
 * Created by PDD on 2018/3/10.
 */

public class FragmentAdapter extends FragmentStatePagerAdapter {
    private List<BaseFragment> mFragments;

    public FragmentAdapter(FragmentManager fm,List<BaseFragment> mFragments) {
        super(fm);
        this.mFragments=mFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments!=null?mFragments.size():0;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
