package pgg.functionmodule.main;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import pgg.functionmodule.R;
import pgg.functionmodule.data.bean.Girls;

/**
 * Created by PDD on 2018/3/11.
 */

public class GirlsAdapter extends RecyclerArrayAdapter<Girls> {


    public OnMyItemClickListener mOnItemClickListener;

    public GirlsAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new GirlsViewHolder(parent);
    }

    @Override
    public void OnBindViewHolder(final BaseViewHolder holder, final int position) {
        super.OnBindViewHolder(holder, position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(position, holder);
                }
            }
        });
    }

    private class GirlsViewHolder extends BaseViewHolder<Girls> {

        private ImageView image;

        private GirlsViewHolder(ViewGroup parent) {
            super(parent, R.layout.function_item_girl);
            image = $(R.id.girl_img);
        }

        @Override
        public void setData(Girls data) {
            super.setData(data);
            Glide.with(getContext())
                    .load(data.getUrl())
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(image);
        }
    }

    public interface OnMyItemClickListener {
        void onItemClick(int position, BaseViewHolder holder);
    }

    public void setOnMyItemClickListener(OnMyItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
