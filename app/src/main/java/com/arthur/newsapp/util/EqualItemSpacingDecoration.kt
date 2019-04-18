package com.arthur.newsapp.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EqualSpacingItemDecoration constructor(private val spacing: Int, private var displayMode: Int = -1) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildViewHolder(view).adapterPosition
        val itemCount = state.itemCount
        val layoutManager = parent.layoutManager
        setSpacingForDirection(outRect, layoutManager!!, position, itemCount)
    }

    private fun setSpacingForDirection(outRect: Rect,
                                       layoutManager: RecyclerView.LayoutManager,
                                       position: Int,
                                       itemCount: Int) {

        if (displayMode == -1) {
            displayMode = resolveDisplayMode(layoutManager)
        }

        when (displayMode) {
            HORIZONTAL -> {
                outRect.apply {
                    left = spacing
                    right = if (position == itemCount - 1) spacing else 0
                    top = spacing
                    bottom = spacing
                }
            }
            VERTICAL -> {
                outRect.apply {
                    left = spacing
                    right = spacing
                    top = spacing
                    bottom = if (position == itemCount - 1) spacing else 0
                }
            }
            GRID -> if (layoutManager is GridLayoutManager) {
                val cols = layoutManager.spanCount
                val rows = itemCount / cols

                outRect.apply {
                    left = spacing
                    right = if (position % cols == cols - 1) spacing else 0
                    top = spacing
                    bottom = if (position / cols == rows - 1) spacing else 0
                }
            }
        }
    }

    private fun resolveDisplayMode(layoutManager: RecyclerView.LayoutManager): Int {
        if (layoutManager is GridLayoutManager) return GRID
        return if (layoutManager.canScrollHorizontally()) HORIZONTAL else VERTICAL
    }

    companion object {
        const val HORIZONTAL = 0
        const val VERTICAL = 1
        const val GRID = 2
    }
}