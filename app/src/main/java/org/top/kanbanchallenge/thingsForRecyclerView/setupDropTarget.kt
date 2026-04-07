package org.top.kanbanchallenge.thingsForRecyclerView

import android.view.DragEvent
import androidx.recyclerview.widget.RecyclerView

fun setupDropTarget(
    recyclerView: RecyclerView,
    targetAdapter: CustomAdapter
) {
    recyclerView.setOnDragListener { _, event ->
        when (event.action) {

            DragEvent.ACTION_DROP -> {
                val item = event.localState as TaskItem

                item.sourceAdapter?.remove(item)
                targetAdapter.add(item)
                item.sourceAdapter = targetAdapter

                true
            }

            else -> true
        }
    }
}