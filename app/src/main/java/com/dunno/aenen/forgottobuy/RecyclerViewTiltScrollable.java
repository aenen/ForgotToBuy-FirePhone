package com.dunno.aenen.forgottobuy;

import android.support.v7.widget.RecyclerView;

import com.amazon.euclid.util.TiltScrollDirection;
import com.amazon.euclid.util.TiltScrollable;

public class RecyclerViewTiltScrollable implements TiltScrollable<RecyclerView> {

    RecyclerView mRecyclerView;
    int position = 0;

    @Override
    public void scrollStart()
    {
        mRecyclerView.smoothScrollToPosition(0);
        int prr=1+1;

    }

    @Override
    public void scrollBy(int x, int y)
    {
int prr=1+1;
    }

    @Override
    public void scrollEnd()
    {
        mRecyclerView.smoothScrollToPosition(mRecyclerView.getAdapter().getItemCount() - 1);

    }

    @Override
    public float pixelRemainingInDirection(TiltScrollDirection tiltScrollDirection)
    {
        int prr=1+1;
        return 1000;
    }

    @Override
    public boolean hasContentToScroll()
    {
        int prr=1+1;
        return true;
    }

    @Override
    public void attach(RecyclerView recyclerView)
    {
        int prr=1+1;
        mRecyclerView = recyclerView;
        int itemCount = recyclerView.getAdapter().getItemCount();
    }

    @Override
    public void detach()
    {
        int prr=1+1;

    }
}
