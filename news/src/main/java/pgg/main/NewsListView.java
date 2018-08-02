package pgg.main;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.jude.easyrecyclerview.swipe.SwipeRefreshLayout;

import pgg.data.bean.StoryList;
import pgg.news.R;

/**
 * Created by PDD on 2018/3/11.
 */

public class NewsListView extends EasyRecyclerView implements NewsListContract.View, android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener{

    private String mDate;
    private NewsListAdapter mAdapter;
    private boolean isActive;
    private NewsListContract.Presenter mPresenter;

    public NewsListView(Context context, String date){
        super(context);
        mDate =date;
        initView();
    }

    public NewsListView(Context context) {
        super(context);
        initView();
    }

    public NewsListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public NewsListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView(){
        setRefreshingColor(ContextCompat.getColor(getContext(), R.color.colorPrimary),
                ContextCompat.getColor(getContext(), android.R.color.holo_blue_light),
                ContextCompat.getColor(getContext(), android.R.color.holo_green_light));
        LinearLayoutManager lm=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        setLayoutManager(lm);
        DividerDecoration dividerDecoration = new DividerDecoration(getResources().getColor(R.color.gray_e0), 20, 20, 0);
        dividerDecoration.setDrawLastItem(true);
        addItemDecoration(dividerDecoration);
        mAdapter = new NewsListAdapter(getContext());
        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });

        setAdapterWithProgress(mAdapter);
        setRefreshListener(this);
        new NewsListPresenter(this);
        isActive = true;
    }

    @Override
    public void onRefresh() {
        mPresenter.getNewsMessage(20,1,mDate);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        isActive=true;
        mPresenter.getNewsMessage(20,1,mDate);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        isActive=false;
    }

    @Override
    public void setPresenter(NewsListContract.Presenter presenter) {
        mPresenter =presenter;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void showNewsList(StoryList info) {
        if (info != null) {
            mAdapter.clear();
            mAdapter.addAll(info.getStories());
        }
    }
}
