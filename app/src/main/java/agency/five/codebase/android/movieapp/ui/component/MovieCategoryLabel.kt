package agency.five.codebase.android.movieapp.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

sealed class MovieCategoryLabelTextViewState();

data class MovieCategoryTextString(val category: String) : MovieCategoryLabelTextViewState()
data class MovieCategoryTextStringResource(val category: Int) : MovieCategoryLabelTextViewState()

data class MovieCategoryLabelViewState(
    val itemId: Int,
    val isSelected: Boolean,
    val categoryText: MovieCategoryLabelTextViewState,
)

@Composable
fun MovieCategoryLabel(
    item: MovieCategoryLabelViewState,
    modifier: Modifier = Modifier,
) {

    val rememberState = remember { mutableStateOf(item.isSelected) }

    Column(
        modifier = modifier
            .height(30.dp)
    ) {
        Text(
            text = when (item.categoryText) {
                is MovieCategoryTextString -> item.categoryText.category;
                is MovieCategoryTextStringResource -> stringResource(id = item.categoryText.category)
            },
            fontSize = 16.sp,
            textDecoration = if (rememberState.value) TextDecoration.Underline else TextDecoration.None,
            fontWeight = if (rememberState.value) FontWeight.Bold else FontWeight.Normal,
            color = if (rememberState.value) Color.Black else Color.Gray,
            modifier = modifier
                .clickable {
                    rememberState.value = !rememberState.value
                }
        )
    }
}


@Preview
@Composable
fun MovieCategoryLabelPreview() {
    val movieCategoryViewState =
        MovieCategoryLabelViewState(9, false, MovieCategoryTextString("Comedy"))
    MovieCategoryLabel(movieCategoryViewState)
}