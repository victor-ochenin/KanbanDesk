package org.top.kanbanchallenge.thingsForRecyclerView

data class TaskItem(
    val header: String,
    val body: String,
    var sourceAdapter: CustomAdapter? = null
)
