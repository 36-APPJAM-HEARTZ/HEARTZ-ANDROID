package com.byeboo.app.presentation.home.homeamulet

import com.byeboo.app.R

data class HomeAmuletState(
    val journey: AmuletType = AmuletType.EMOTION_FACE,
    val journeyDescription: String = "",
    val isLoading: Boolean = true
)

enum class AmuletType(val journeyName: String, val imageRes: Int) {
    EMOTION_FACE("감정 직면", R.drawable.img_emotion_face),
    EMOTION_ORGANIZE("감정 정리", R.drawable.img_emotion_organize);

    companion object {
        fun from(journeyName: String): AmuletType =
            entries.find { it.journeyName == journeyName } ?: EMOTION_FACE
    }
}
sealed interface JourneyResultSideEffect {
    data object NavigateToHomeOnboarding : JourneyResultSideEffect
}
