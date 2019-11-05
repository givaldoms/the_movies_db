package dev.givaldo.feature.main.extention

import androidx.appcompat.widget.SearchView
import java.util.*
import kotlin.concurrent.schedule

fun SearchView.onQueryTextChange(delay: Long = 500L, onTextChanged: (String) -> Unit) {
    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        var timer = Timer()

        override fun onQueryTextSubmit(query: String?): Boolean {
            return false
        }

        override fun onQueryTextChange(newText: String): Boolean {
            timer.cancel()
            timer = Timer()
            timer.schedule(delay) {
                onTextChanged(newText)
            }
            return true
        }
    })
}