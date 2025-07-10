package com.byeboo.app.presentation.auth.journeyResult

import com.byeboo.app.R

data class JourneyResultState(
    val journey: JourneyType = JourneyType.EMOTION_FACE,
    val journeyDescription: String = "너무 힘든 시간으로 보내고 있는 당신에게,\n'자기성찰형 여정'을 추천해요." +
        "\n\n이 여정은 감정을 직면하고,\n상황을 정리하며, 점차 앞으로 나아가는\n5단계로 구성되어 있어요." +
        "\n\n하루에 하나씩 기록해 나가다 보면,\n감정이 조금씩 정돈되고\n마음이 가벼워질 거예요."
)

enum class JourneyType(val journeyName: String, val imageRes: Int) {
    EMOTION_FACE("감정 직면", R.drawable.img_emotion_face),
    EMOTION_ORGANIZE("감정 정리", R.drawable.img_emotion_organize)
}

sealed interface JourneyResultSideEffect
