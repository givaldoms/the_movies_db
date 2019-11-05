package dev.givaldo.feature.main.extention

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.givaldo.feature.main.util.PaginationScrollListener

fun RecyclerView.onScrollFinish(event: () -> Unit) {

    addOnScrollListener(object : PaginationScrollListener(layoutManager as LinearLayoutManager) {
        override fun loadMoreItems() {
            event()
        }
    })

}