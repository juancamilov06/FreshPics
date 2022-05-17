package com.juanvilla.freshpic.ui.utils

import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

inline fun EditText.onTextChangedListenerDebounced(
    crossinline action: (text: String) -> Unit,
    scope: LifecycleCoroutineScope,
    delayMillis: Long = 1000L
) {
    var job: Job = Job()
    this.doOnTextChanged { text, _, _, _ ->
        job.cancel()
        if (text.isNullOrBlank()) {
            action(text.toString())
        } else {
            job = scope.launch {
                delay(delayMillis)
                action(text.toString())
            }
        }
    }
}

fun <T> LiveData<Event<T>>.eventObserve(owner: LifecycleOwner, observer: (t: T) -> Unit) {
    this.observe(owner) { it?.getContentIfNotHandled()?.let(observer) }
}