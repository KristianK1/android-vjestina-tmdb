package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.ui.theme.Blue
import agency.five.codebase.android.movieapp.ui.theme.spacing
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*

@Composable
fun FavoriteButton(
    onClick: () -> Unit,
    state: Boolean,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = if (state) R.drawable.full_heart else R.drawable.empty_heart),
        contentDescription = "Favorite button",
        modifier
            .size(32.dp)
            .background(Blue, CircleShape)
            .clickable {
                onClick()
            }
            .padding(MaterialTheme.spacing.small))
}

@Preview
@Composable
private fun FavoriteButtonPreview() {
    var active by remember { mutableStateOf(false) }
    FavoriteButton(
        onClick = {
            active = !active
        },
        state = active,
        Modifier
    )
}
