package com.byeboo.app.core.designsystem.component.modal

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.byeboo.app.R
import com.byeboo.app.core.designsystem.component.button.ByeBooButton
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme

@Composable
fun OffboardingModal(
    onClick: () -> Unit,
    titleText: String,
    guideText: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(264.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color = ByeBooTheme.colors.gray900Alpha80)
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "축하드려요!",
                style = ByeBooTheme.typography.body2,
                color = ByeBooTheme.colors.gray400
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = titleText,
                style = ByeBooTheme.typography.sub2,
                color = ByeBooTheme.colors.gray50,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Image(
                painter = painterResource(id = R.drawable.bori_clover),
                contentDescription = "이미지",
                modifier = Modifier.size(160.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = guideText,
                style = ByeBooTheme.typography.body2,
                color = ByeBooTheme.colors.gray400,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            ByeBooButton(
                onClick = onClick,
                buttonText = "바로가기",
                buttonTextColor = ByeBooTheme.colors.white,
                buttonBackgroundColor = ByeBooTheme.colors.primary300,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OffboardingModalPreview() {
    ByeBooTheme {
        OffboardingModal(
            onClick = {},
            titleText = "감정 직면 여정을\n모두 마무리 했어요",
            guideText = "보리가 하츠핑님께\n하고싶은 말이 있다고 해요"
        )
    }
}