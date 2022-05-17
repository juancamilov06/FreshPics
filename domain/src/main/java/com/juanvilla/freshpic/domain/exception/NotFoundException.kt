package com.juanvilla.freshpic.domain.exception

class NotFoundException(
    code: Int = 404,
    message: String
) : BaseException(message)