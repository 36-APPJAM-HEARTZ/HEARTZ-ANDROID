package com.byeboo.app.presentation.quest.behavior

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.byeboo.app.R
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme
import com.byeboo.app.core.util.noRippleClickable
import java.util.UUID


private const val MAX_PHOTO_COUNT = 1

data class SelectedImage(
    val uri: Uri,
    val id: String = UUID.randomUUID().toString()
)

@Composable
internal fun QuestPhotoPicker(
    selectedImage: SelectedImage?,
    onImageClick:() -> Unit,
    modifier: Modifier = Modifier

) {

    Box(
        modifier = modifier
    ) {
        if (selectedImage != null) {
            ImagePreview(
                selectedImage = selectedImage,
                onImageClick = onImageClick

            )
        } else {
            ImageUploadButton(
                imageCount = MAX_PHOTO_COUNT,
                onImageClick = onImageClick
            )
        }
    }
}

@Composable
private fun ImageUploadButton(
    imageCount: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(36.dp)
            .width(96.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(12.dp))
            .background(color = ByeBooTheme.colors.whiteAlpha10)
            .noRippleClickable{if (imageCount < 2) onImageClick()} ,
        contentAlignment = Alignment.Center,
    ) {

            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_plus),
                contentDescription = null,
                tint = ByeBooTheme.colors.primary300
            )
        }


}

@Composable
private fun ImagePreview(
    selectedImage: SelectedImage,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(36.dp)
            .width(96.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(12.dp))
            .background(color = ByeBooTheme.colors.gray800)
            .noRippleClickable{onImageClick()},
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = selectedImage.uri,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}
