package com.byeboo.app.data.mapper.todata

import com.byeboo.app.data.dto.request.RequestDummyDto
import com.byeboo.app.domain.model.Dummy

fun Dummy.toData(): RequestDummyDto =
    RequestDummyDto(
        id = this.id,
        email = this.email
    )
