package org.top.kanbanchallenge.thingsForRecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.top.kanbanchallenge.R

class CustomAdapter(private var dataSet: List<TaskItem>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val headerTextView: TextView = view.findViewById(R.id.headerTextView)
        val bodyTextView: TextView = view.findViewById(R.id.textView)
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

    fun updateData(newData: List<TaskItem>) {
        dataSet = newData
        notifyDataSetChanged()
    }
}
