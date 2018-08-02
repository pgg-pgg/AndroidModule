package pgg.functionmodule.main;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import pgg.functionmodule.Constants;
import pgg.functionmodule.R;
import pgg.functionmodule.data.bean.Girls;
import pgg.lib_common.utils.Utils;

/**
 * Created by PDD on 2018/3/11.
 */

public class GirlsView extends FrameLayout implements GirlsContract.View, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnLoadMoreListener  {


    private ViewStub mNetworkErrorLayout;
    private EasyRecyclerView mGirlsRecyclerView;
    private GirlsAdapter mAdapter;
    private ArrayList<Girls> mData;
    private boolean mActive;
    private GirlsContract.Presenter presenter;
    private View networkErrorView;
    private int  size=20;
    private int page=1;

    public GirlsView(@NonNull Context context) {
        super(context);
        initView();
    }



    public GirlsView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public GirlsView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @Override
    public void setPresenter(GirlsContract.Presenter presenter) {
        this.presenter =presenter;
    }

    private void initView() {

        inflate(getContext(), R.layout.function_view_girls_content, this);
        mNetworkErrorLayout = (ViewStub) findViewById(R.id.network_error_layout);
        mGirlsRecyclerView = (EasyRecyclerView) findViewById(R.id.girls_recycler_view);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mGirlsRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        mAdapter = new GirlsAdapter(getContext());

        mGirlsRecyclerView.setAdapterWithProgress(mAdapter);
        mGirlsRecyclerView.setRefreshingColor(
                ContextCompat.getColor(getContext(), R.color.colorPrimary),
                ContextCompat.getColor(getContext(), android.R.color.holo_blue_light),
                ContextCompat.getColor(getContext(), android.R.color.holo_green_light)
        );
        mAdapter.setMore(pgg.lib_common.R.layout.layout_load_more, this);
        mAdapter.setNoMore(R.layout.layout_load_no_more);
        mAdapter.setError(R.layout.layout_load_error);
        mAdapter.setOnMyItemClickListener(new GirlsAdapter.OnMyItemClickListener() {
            @Override
            public void onItemClick(int position, BaseViewHolder holder) {
                Intent intent = new Intent(Utils.getActivity(GirlsView.this), GirlsActivity.class);
                intent.putParcelableArrayListExtra(Constants.INTENT_GIRLS, mData);
                intent.putExtra(Constants.INTENT_INDEX, position);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeScaleUpAnimation(holder.itemView, holder.itemView.getWidth() / 2, holder.itemView.getHeight() / 2, 0, 0);
                Utils.getActivity(GirlsView.this).startActivity(intent, options.toBundle());
            }
        });

        mGirlsRecyclerView.setRefreshListener(this);

        mData = new ArrayList<>();
        mActive = true;
    }

    @Override
    public boolean isActive() {
        return mActive;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mActive=true;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mActive=false;
    }

    @Override
    public void refresh(List<Girls> data) {
        mData.clear();
        mData.addAll(data);
        mAdapter.clear();
        mAdapter.addAll(data);
    }

    @Override
    public void load(List<Girls> data) {
        mData.addAll(data);
        mAdapter.addAll(data);
    }

    @Override
    public void showError() {
        mGirlsRecyclerView.showError();

        if (networkErrorView != null) {
            networkErrorView.setVisibility(View.VISIBLE);
            return;
        }

        networkErrorView = mNetworkErrorLayout.inflate();
    }

    @Override
    public void showNormal() {
        if (networkErrorView != null) {
            networkErrorView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onRefresh() {
        presenter.getGirls(size, page, true);
        page = 1;
    }

    @Override
    public void onLoadMore() {
        if (mData.size() % size == 0) {
            page++;
            presenter.getGirls(size, page, false);
        }
    }
}
