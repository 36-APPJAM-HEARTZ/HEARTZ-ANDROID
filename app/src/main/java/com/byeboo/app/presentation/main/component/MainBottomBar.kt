package com.byeboo.app.presentation.main.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme
import com.byeboo.app.core.designsystem.ui.theme.gray400
import com.byeboo.app.core.designsystem.ui.theme.primary300
import com.byeboo.app.core.util.noRippleClickable
import com.byeboo.app.presentation.main.MainNavTab
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun MainBottomBar(
    visible: Boolean,
    tabs: ImmutableList<MainNavTab>,
    currentTab: MainNavTab?,
    onTabSelected: (MainNavTab) -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = EnterTransition.None,
        exit = ExitTransition.None
    ) {
        Column(
            modifier =
            Modifier
                .background(Color.White)
        ) {
            Row(
                modifier =
                Modifier
                    .navigationBarsPadding()
                    .fillMaxWidth()
                    .padding(vertical = 21.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                tabs.forEach { tab ->
                    key(tab.route) {
                        val selected = currentTab == tab
                        MainBottomBarItem(
                            tab = tab,
                            selected = selected,
                            onClick = { onTabSelected(tab) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RowScope.MainBottomBarItem(
    modifier: Modifier = Modifier,
    tab: MainNavTab,
    selected: Boolean,
    onClick: () -> Unit
) {
    val bottomItemColor = if (selected) primary300 else gray400
    Column(
        modifier =
        modifier
            .noRippleClickable(onClick = onClick)
            .weight(1f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(1.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(tab.icon),
            contentDescription = stringResource(tab.contentDescription),
            tint = bottomItemColor
        )
        Text(
            text = stringResource(tab.contentDescription),
            fontSize = 14.sp,
            color = bottomItemColor
        )
    }
}

@Preview
@Composable
private fun MainBottomBarPreview() {
    ByeBooTheme {
        var currentTab by remember { mutableStateOf(MainNavTab.HOME) }
        MainBottomBar(
            visible = true,
            tabs = MainNavTab.entries.toImmutableList(),
            currentTab = currentTab,
            onTabSelected = { currentTab = it }
        )
    }
}
