package com.byeboo.app.presentation.quest

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class QuestTipState(
    val stepNumber: Int = 0,
    val questNumber: Int = 0,
    val question: String = "연애에서 반복됐던 문제 패턴 3가지를 생각해보아요.",
    val tipStep: ImmutableList<Int> = persistentListOf(1, 2, 3),
    val tipQuestion: ImmutableList<String> = persistentListOf(
        "반복되었던 상황은 무엇이었나요?",
        "그 상황에서 내가 했던 행동은?",
        "그 결과 어떤 감정을 느꼈나요?"
    ),
    val tipAnswer: ImmutableList<String> = persistentListOf(
        "예: 항상 연락이 끊기면 불안해졌어요.",
        "예: 먼저 연락을 계속해서 했어요.",
        "예: 상대가 멀어지는 느낌을 받아 자존감이 낮아졌어요."
    )
)

sealed interface QuestTipSideEffect{
    data object NavigateBack: QuestTipSideEffect
}