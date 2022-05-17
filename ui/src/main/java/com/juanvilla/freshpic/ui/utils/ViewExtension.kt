package com.juanvilla.freshpic.ui.utils

import android.os.Environment
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.juanvilla.freshpic.domain.entity.Gif
import java.io.File
import java.io.FileOutputStream
import java.nio.ByteBuffer
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