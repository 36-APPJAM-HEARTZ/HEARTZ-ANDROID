package com.byeboo.app.presentation.auth.onboarding.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.byeboo.app.R
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme
import com.byeboo.app.core.util.noRippleClickable

@Composable
fun OnboardingEmotionCard(
    content: String,
    imageRes: Int,
    isSelected: Boolean,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val borderColor = if (isSelected) ByeBooTheme.colors.primary300 else Color.Transparent
    val textColor = if (isSelected) ByeBooTheme.colors.primary300 else ByeBooTheme.colors.gray300
    val backgroundColor =
        if (isSelected) {
            ByeBooTheme.colors.primary300Alpha10
        } else {
            ByeBooTheme.colors.whiteAlpha10
        }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundColor)
            .border(2.dp, borderColor, RoundedCornerShape(12.dp))
            .noRippleClickable() { onCardClick() }
            .padding(vertical = 12.dp)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 11.5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = ColorPainter(ByeBooTheme.colors.white.copy(alpha = 0.1f)),
                contentDescription = null,
                modifier = Modifier
                    .width(73.dp)
                    .height(100.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = content,
                style = ByeBooTheme.typography.body5,
                color = textColor
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun OnboardingEmotionCardRowPreview() {
    ByeBooTheme {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .width(350.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OnboardingEmotionCard(
                content = "너무 힘들어요",
                imageRes = R.drawable.ic_launcher_background,
                isSelected = true,
                onCardClick = {},
                modifier = Modifier.width(101.dp)
            )
            OnboardingEmotionCard(
                content = "극복 중이에요",
                imageRes = R.drawable.ic_launcher_background,
                isSelected = false,
                onCardClick = {},
                modifier = Modifier.width(101.dp)
            )
            OnboardingEmotionCard(
                content = "꽤 극복했어요",
                imageRes = R.drawable.ic_launcher_background,
                isSelected = false,
                onCardClick = {},
                modifier = Modifier.width(101.dp)
            )
        }
    }
}
