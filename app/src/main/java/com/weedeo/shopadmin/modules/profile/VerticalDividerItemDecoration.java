package com.weedeo.shopadmin.modules.profile;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

class VerticalDividerItemDecoration extends RecyclerView.ItemDecoration {

    private boolean verticalOrientation = true;
    private int space;

    public VerticalDividerItemDecoration(int value, boolean verticalOrientation) {
        this.space = value;
        this.verticalOrientation = verticalOrientation;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        // skip first item in the list
        if (parent.getChildAdapterPosition(view) != 0) {

            if (verticalOrientation) {

                outRect.set(space, 0, 0, 0);

            } else if (!verticalOrientation) {

                outRect.set(0, space, 0, 0);
            }
        }
    }
}
