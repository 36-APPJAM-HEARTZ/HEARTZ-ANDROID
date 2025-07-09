package com.byeboo.app.presentation.splash

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import com.byeboo.app.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

@HiltViewModel
class OnBoardingViewModel @Inject constructor() : ViewModel() {
    private val _pageIndex = mutableIntStateOf(0)
    val pageIndex: State<Int> = _pageIndex

    private val pages: PersistentList<List<OnBoardingState>> = persistentListOf(
        persistentListOf(
            OnBoardingState(
                title = "저는 당신이 털어놓은 감정을 담는 주머니,\n보리라고 해요.",
                imageRes = R.drawable.img_onboarding_1st
            ),

            OnBoardingState(
                title = "이별 후 걸림돌 같은 감정들을 털어놔주시면\n제 안에서 조약돌이 되어 쌓여요.",
                imageRes = R.drawable.img_onboarding_2nd
            )

        ),

        persistentListOf(

            OnBoardingState(
                title = "당신이 준비가 되었을 때\n조약돌들을 하나씩 꺼내 바닥에 놓아드려요.",
                imageRes = R.drawable.img_onboarding_3rd
            ),

            OnBoardingState(
                title = "제가 모아둔 조약돌을 디딤돌 삼아\n한 걸음 한 걸음 미래로 나아가 주세요.",
                imageRes = R.drawable.img_onboarding_4th
            )

        ),

        persistentListOf(
            OnBoardingState(
                title = "자, 이제 시간이 됐어요.\n\n당신에게 꼭 맞는 이별 극복 여정에 따라\n퀘스트를 하나하나씩 진행하면서\n감정을 정리하고, 극복해보아요.\n\n저 보리가 항상 함께할게요.",
                imageRes = R.drawable.img_onboarding_5th
            )
        )

    )

    fun nextPage() {
        if (_pageIndex.intValue < pages.lastIndex) {
            _pageIndex.intValue += 1
        }
    }

    fun skipPage() {
    }

    fun currentContents(): List<OnBoardingState> = pages[_pageIndex.value]
    fun showPageNumber(): String = "${_pageIndex.intValue + 1}/${pages.size}"
}
