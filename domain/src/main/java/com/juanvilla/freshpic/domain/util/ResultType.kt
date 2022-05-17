package com.juanvilla.freshpic.domain.util

import com.juanvilla.freshpic.domain.exception.BaseException

sealed class ResultType<out T> {
    data class Success<T>(val data: T) : ResultType<T>()
    data class Error(
        val error: BaseException
    ) : ResultType<Nothing>()
}