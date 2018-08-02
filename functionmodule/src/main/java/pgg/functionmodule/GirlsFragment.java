package pgg.functionmodule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pgg.functionmodule.main.GirlsView;
import pgg.lib_common.base.BaseFragment;

/**
 * Created by PDD on 2018/3/11.
 */

public class GirlsFragment extends BaseFragment {


    public static GirlsFragment newInstance(){
        return new GirlsFragment();
    }

    public GirlsFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.function_fragment_girls,container,false);
    }
}
