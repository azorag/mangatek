package com.jeancr.mangatek.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridMangaItemDecoration :RecyclerView.ItemDecoration() {
    private val spacing = 50
    private val spanCount = 2

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position =parent.getChildAdapterPosition(view)
        val column = position % 2

        outRect.left = spacing - column *spacing/spanCount
        outRect.right = (column+1)*spacing/spanCount
    }
}