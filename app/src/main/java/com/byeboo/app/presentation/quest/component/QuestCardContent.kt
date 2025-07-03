package com.byeboo.app.presentation.quest.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.byeboo.app.R
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme
import com.byeboo.app.core.designsystem.ui.theme.primary300
import com.byeboo.app.core.designsystem.ui.theme.primary300Alpha20
import com.byeboo.app.core.designsystem.ui.theme.primary50
import com.byeboo.app.core.designsystem.ui.theme.secondary300
import com.byeboo.app.core.designsystem.ui.theme.whiteAlpha10

@Composable
fun InProgressContent(
    questNumber: Int,
    imageResId: Int,
    modifier: Modifier = Modifier
) {
    QuestCardContainer(
        modifier = modifier,
        backgroundColor = primary300Alpha20
    ) {
        BackgroundImageLayer(imageResId)
        QuestNumberLabel(
            questNumber = questNumber,
            color = primary300,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(8.dp)
        )
    }
}

@Composable
fun AvailableContent(
    questNumber: Int,
    imageResId: Int,
    modifier: Modifier = Modifier
) {
    QuestCardContainer(
        modifier = modifier,
        backgroundColor = primary300
    ) {
        BackgroundImageLayer(imageResId)
        QuestNumberLabel(
            questNumber = questNumber,
            color = primary50,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(8.dp)
        )
    }
}

@Composable
fun TimerLockedContent(
    questNumber: Int,
    remainingTime: String,
    modifier: Modifier = Modifier
) {
    QuestCardContainer(
        modifier = modifier,
        backgroundColor = whiteAlpha10,
        borderColor = secondary300
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = String.format("%02d", questNumber),
                style = ByeBooTheme.typography.cap1,
                color = secondary300,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )

            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_lock),
                contentDescription = "locked",
                tint = whiteAlpha10,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Text(
                text = remainingTime,
                style = ByeBooTheme.typography.cap1,
                color = secondary300,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun LockedContent(
    questNumber: Int,
    modifier: Modifier = Modifier
) {
    QuestCardContainer(
        modifier = modifier,
        backgroundColor = whiteAlpha10
    ) {
        QuestNumberLabel(questNumber, whiteAlpha10)
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_lock),
            contentDescription = "locked",
            tint = whiteAlpha10,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF000000)
fun QuestPreview() {
    ByeBooTheme {
        Row {
            InProgressContent(questNumber = 1, imageResId = R.drawable.ic_launcher_background)
            Spacer(modifier = Modifier.width(10.dp))
            AvailableContent(questNumber = 1, imageResId = R.drawable.ic_launcher_background)
            Spacer(modifier = Modifier.width(10.dp))
            LockedContent(questNumber = 1)
            Spacer(modifier = Modifier.width(10.dp))
            TimerLockedContent(questNumber = 2, remainingTime = "23:59")
        }
    }
}
