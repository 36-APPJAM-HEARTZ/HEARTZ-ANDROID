package com.byeboo.app.data.mapper.todomain

import com.byeboo.app.data.dto.response.ResponseDummyDto
import com.byeboo.app.domain.model.DummyResultModel

fun ResponseDummyDto.toDomain(): DummyResultModel =
    DummyResultModel(
        info = info
    )
