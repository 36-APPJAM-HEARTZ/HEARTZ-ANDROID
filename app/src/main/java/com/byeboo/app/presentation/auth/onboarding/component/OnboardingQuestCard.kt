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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.byeboo.app.R
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme
import com.byeboo.app.core.designsystem.ui.theme.gray300
import com.byeboo.app.core.designsystem.ui.theme.primary300
import com.byeboo.app.core.designsystem.ui.theme.white
import com.byeboo.app.core.util.noRippleClickable

@Composable
fun OnboardingQuestCard(
    title: String,
    content: String,
    imageRes: Int,
    isSelected: Boolean,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val borderColor = if (isSelected) primary300 else Color.Transparent
    val textColor = if (isSelected) primary300 else gray300
    val backgroundColor =
        if (isSelected) primary300.copy(alpha = 0.1f) else white.copy(alpha = 0.1f)

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundColor)
            .border(2.dp, borderColor, RoundedCornerShape(12.dp))
            .noRippleClickable() { onCardClick() }
            .padding(vertical = 24.dp)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = ColorPainter(white.copy(alpha = 0.1f)),
                contentDescription = null,
                modifier = Modifier
                    .size(56.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = title,
                style = ByeBooTheme.typography.sub2,
                color = textColor
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = content,
                style = ByeBooTheme.typography.body5,
                color = gray300,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun OnboardingQuestCardPreview() {
    ByeBooTheme {
        Row(
            modifier = Modifier
                .padding(24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OnboardingQuestCard(
                title = "질문에 답하기",
                content = "질문을 통해\n" +
                    "상황과 감정을\n" +
                    "정리해요",
                imageRes = R.drawable.ic_launcher_background,
                isSelected = true,
                onCardClick = {},
                modifier = Modifier.width(150.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            OnboardingQuestCard(
                title = "활동 인증하기",
                content = "작은 미션을 통해\n" +
                    "몸과 마음을\n" +
                    " 가볍게 해요",
                imageRes = R.drawable.ic_launcher_background,
                isSelected = false,
                onCardClick = {},
                modifier = Modifier.width(150.dp)
            )
        }
    }
}
