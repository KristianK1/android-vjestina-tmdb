package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.ui.theme.Blue
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun FavoriteButton(
    checked: Boolean,
    modifier: Modifier = Modifier,
) {
    val rememberState = remember { mutableStateOf(checked)}
    Image(
        painter = painterResource(id = if (rememberState.value) R.drawable.full_heart else R.drawable.empty_heart),
        contentDescription = "Favorite button",
        modifier
            .clickable {
                rememberState.value = !rememberState.value
            }
            .size(width = 27.dp, height = 27.dp)
            .background(Blue, CircleShape)
            .padding(top = 7.dp, bottom = 7.dp, start = 7.dp, end = 7.dp)
    )
}

@Preview
@Composable
private fun FavoriteButtonPreview() {
    FavoriteButton(false)
}