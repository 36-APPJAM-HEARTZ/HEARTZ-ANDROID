package com.byeboo.app.core.designsystem.component.tag

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme

@Composable
fun SmallTag(
    modifier: Modifier = Modifier,
    tagText: String = "",
    tagColor: Color = ByeBooTheme.colors.secondary300

) {
    Text(
        text = tagText,
        style = ByeBooTheme.typography.cap1.copy(color = tagColor),
        modifier = modifier
    )

}

@Preview
@Composable
private fun SmallTagPreview() {
    ByeBooTheme {
        Column {

            SmallTag(
                modifier = Modifier,
                tagText = "STEP 1",
            )

            SmallTag(
                modifier = Modifier,
                tagText = "STEP 1",
                tagColor = ByeBooTheme.colors.gray300
            )

        }
    }
}