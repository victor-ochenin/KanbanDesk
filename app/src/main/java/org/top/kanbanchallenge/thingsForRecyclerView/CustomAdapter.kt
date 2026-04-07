package org.top.kanbanchallenge.thingsForRecyclerView

import android.content.ClipData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.top.kanbanchallenge.R

class CustomAdapter(private var dataSet: MutableList<TaskItem>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val headerTextView: TextView = view.findViewById(R.id.headerTextView)
        val bodyTextView: TextView = view.findViewById(R.id.textView)

        init {
            view.setOnLongClickListener {
                val item = dataSet[adapterPosition]
                item.sourceAdapter = this@CustomAdapter

                val shadow = View.DragShadowBuilder(view)

                view.startDragAndDrop(null, shadow, item, 0)
                true
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.task_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = dataSet[position]
        viewHolder.headerTextView.text = item.header
        viewHolder.bodyTextView.text = item.body
    }

    override fun getItemCount() = dataSet.size

    fun moveItem(fromPosition: Int, toPosition: Int) {
        val item = dataSet.removeAt(fromPosition)
        dataSet.add(toPosition, item)
        notifyItemMoved(fromPosition, toPosition)
    }

    fun remove(item: TaskItem) {
        val index = dataSet.indexOf(item)
        if (index != -1) {
            dataSet.removeAt(index)
            notifyItemRemoved(index)
        }
    }

    fun add(item: TaskItem) {
        item.sourceAdapter = this
        dataSet.add(item)
        notifyItemInserted(dataSet.size - 1)
    }
}
