package andy.ayaseruri.lib;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by ayaseruri on 15/8/25.
 */
public class TrackPageIndicator extends LinearLayout implements ViewPager.OnPageChangeListener {

    private ViewPager mViewPager;
    private ViewPager.OnPageChangeListener mOnPageChangeListener;
    private ArrayList<TabView.IGetSnapshot> iGetSnapshotArrayList;
    private Context mContext;

    public TrackPageIndicator(Context context) {
        super(context);
        this.mContext = context;
    }

    public TrackPageIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public void setViewPager(ViewPager viewPager, ArrayList<TabView.IGetSnapshot> iGetSnapshotArrayList){
        if(viewPager.getAdapter().getCount() != iGetSnapshotArrayList.size()){
            throw new IllegalArgumentException("view pager item count must match the tabViewHolderArrayList size");
        }
        this.mViewPager = viewPager;
        this.iGetSnapshotArrayList = iGetSnapshotArrayList;
        mViewPager.setOnPageChangeListener(this);
        notifyDataChanged();
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener){
        this.mOnPageChangeListener = onPageChangeListener;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if(positionOffset > 0){
            TabView left = (TabView)this.getChildAt(position);
            TabView right = (TabView)this.getChildAt(position + 1);

            left.setDirection(TabView.RIGHT);
            right.setDirection(TabView.LEFT);

            left.setProgress(1 - positionOffset);
            right.setProgress(positionOffset);
        }

        if(null != mOnPageChangeListener){
            mOnPageChangeListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }
    }

    @Override
    public void onPageSelected(int position) {
        TabView tabView = (TabView)TrackPageIndicator.this.getChildAt(mViewPager.getCurrentItem());
        tabView.setProgress(1.0f);

        for(int i=0; i < this.getChildCount(); i++){
            if(i != mViewPager.getCurrentItem()){
                ((TabView)this.getChildAt(i)).setProgress(0);
            }
        }

        if(null != mOnPageChangeListener) {
            mOnPageChangeListener.onPageSelected(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if(null != mOnPageChangeListener) {
            mOnPageChangeListener.onPageScrollStateChanged(state);
        }
    }

    public void notifyDataChanged(){
        this.removeAllViews();
        int i = 0;

        for(final TabView.IGetSnapshot iGetSnapshot : iGetSnapshotArrayList){
            final TabView tabView = new TabView(mContext, iGetSnapshot);
            tabView.setIndex(i++);
            tabView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mViewPager.getCurrentItem() != tabView.getIndex()) {
                        mViewPager.setCurrentItem(tabView.getIndex(), false);
                    }
                }
            });

            this.addView(tabView, new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));

            ((TabView)this.getChildAt(mViewPager.getCurrentItem())).setProgress(1.0f);
        }
    }
}
