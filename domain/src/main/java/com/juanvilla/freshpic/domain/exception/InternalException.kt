package com.juanvilla.freshpic.domain.exception

class InternalException(
    code: Int = 500,
    message: String
) : BaseException(message)