package pgg.lib_common.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 可以禁止滑动翻页的ViewPager
 * Created by PDD on 2018/3/10.
 */

public class NoScrollViewPager extends ViewPager {

    private boolean isPagingEnabled = true;

    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return this.isPagingEnabled&&super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return this.isPagingEnabled&&super.onInterceptTouchEvent(ev);
    }

    public void setPagerEnabled(boolean b) {
        this.isPagingEnabled = b;
    }
}
