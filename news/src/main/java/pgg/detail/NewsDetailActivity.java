package pgg.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;

import pgg.lib_common.base.BaseActivity;

/**
 * Created by PDD on 2018/3/10.
 */

@Route(path = "/news/detail")
public class NewsDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NewsDetailView newsDetailView=new NewsDetailView(this);
        setContentView(newsDetailView);
        String id=getIntent().getStringExtra("id");
        new NewsDetailPresenter(newsDetailView).getNewsDetail(id);
    }
}
