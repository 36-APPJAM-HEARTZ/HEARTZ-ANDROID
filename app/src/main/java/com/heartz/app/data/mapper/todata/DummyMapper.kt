package com.heartz.app.data.mapper.todata

import com.heartz.app.data.dto.request.RequestDummyDto
import com.heartz.app.domain.model.Dummy

fun Dummy.toData(): RequestDummyDto =
    RequestDummyDto(
        id = this.id,
        email = this.email,
    )
