package com.byeboo.app.presentation.quest

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class QuestTipState(
    val stepNumber: Int = 0,
    val questNumber: Int = 0,
    val question: String = "연애에서 반복됐던 문제 패턴 3가지를 생각해보아요.",
    val tipStep: ImmutableList<Int> = persistentListOf(),
    val tipQuestion: ImmutableList<String> = persistentListOf(),
    val tipAnswer: ImmutableList<String> = persistentListOf()
)