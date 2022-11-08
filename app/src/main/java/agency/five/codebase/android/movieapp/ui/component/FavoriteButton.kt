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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FavoriteButton(
    onClick: () -> Unit,
    state: MutableState<Boolean>,
    modifier: Modifier,
) {
    Image(painter = painterResource(id = if (state.value) R.drawable.full_heart else R.drawable.empty_heart),
        contentDescription = "Favorite button",
        modifier
            .background(Blue, CircleShape)
            .clickable {
                onClick.invoke()
            }
            .padding(10.dp))
}

@Preview
@Composable
private fun FavoriteButtonPreview() {
    var active = remember { mutableStateOf(false) }
    FavoriteButton(
        onClick = {
            active.value = !active.value
        },
        state = active,
        Modifier.size(60.dp)
    )
}