package com.upclicks.ffc.commons;

import android.widget.AbsListView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class EndlessScrollListener extends RecyclerView.OnScrollListener {
    int currentItems=0;
    int totalItems=0;
    int scrollOutItems=0;
    boolean isScrolling =false;

    private LinearLayoutManager mLinearLayoutManager;

    public EndlessScrollListener(LinearLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
            isScrolling=true;
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        currentItems=mLinearLayoutManager.getChildCount();
        totalItems=mLinearLayoutManager.getItemCount();
        scrollOutItems=mLinearLayoutManager.findFirstCompletelyVisibleItemPosition();
        if(dy>0) {
            if (isScrolling && (currentItems + scrollOutItems == totalItems)) {
                loadMore();
            }
        }
    }

    public abstract void loadMore();
}