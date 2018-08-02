package pgg.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import pgg.lib_common.base.BaseActivity;
import pgg.news.R;

/**
 * Created by PDD on 2018/3/11.
 */

@Route(path = "/news/center")
public class NewsCenterActivity extends BaseActivity {


    private Toolbar mToolBar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private NewsListViewAdapter mListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.news_activity_news);
        mToolBar = (Toolbar) findViewById(R.id.news_title_bar);
        mToolBar.setTitle("知乎日报");
        setupToolBar(mToolBar, false);
        mTabLayout = (TabLayout) findViewById(R.id.date_tab);
        mViewPager = (ViewPager) findViewById(R.id.message_pager);
        mListAdapter = new NewsListViewAdapter(getMessageListViews(), getWeekDate());
        mViewPager.setAdapter(mListAdapter);
        //setupWithViewPager必须在ViewPager.setAdapter()之后调用
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private static List<String> getWeekDate() {
        List<String> dates = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        for (int i = 0; i < 7; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_YEAR, 1 - i);
            dates.add(simpleDateFormat.format(calendar.getTime()));
        }
        return dates;
    }

    private List<? extends View> getMessageListViews() {
        List<NewsListView> viewList = new ArrayList<>();
        List<String> weekDate = getWeekDate();
        if (weekDate != null) {
            for (String tab : weekDate) {
                viewList.add(new NewsListView(this, tab));
            }
        }
        return viewList;
    }
}
