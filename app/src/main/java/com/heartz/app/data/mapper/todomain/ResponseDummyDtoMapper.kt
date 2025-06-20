package com.heartz.app.data.mapper.todomain

import com.heartz.app.data.dto.response.ResponseDummyDto
import com.heartz.app.domain.model.DummyResultModel

fun ResponseDummyDto.toDomain(): DummyResultModel =
    DummyResultModel(
        info = info,
    )
