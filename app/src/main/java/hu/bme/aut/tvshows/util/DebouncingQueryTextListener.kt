package hu.bme.aut.tvshows.util

import android.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import hu.bme.aut.tvshows.dispatchers.DispatcherProvider
import kotlinx.coroutines.*

internal class DebouncingQueryTextListener(
    lifecycle: Lifecycle,
    val dispatcherProvider: DispatcherProvider,
    private val onDebouncingQueryTextChange: (String) -> Unit
) : SearchView.OnQueryTextListener {
    var debouncePeriod: Long = 500

    private val coroutineScope = lifecycle.coroutineScope

    private var searchJob: Job? = null

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        searchJob?.cancel()
        searchJob = coroutineScope.launch(dispatcherProvider.main()) {
            newText?.let {
                delay(debouncePeriod)
                onDebouncingQueryTextChange(newText)
            }
        }
        return false
    }
}