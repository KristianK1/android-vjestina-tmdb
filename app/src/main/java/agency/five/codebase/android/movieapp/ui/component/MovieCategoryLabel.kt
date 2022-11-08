package agency.five.codebase.android.movieapp.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
    val categoryText: MovieCategoryLabelTextViewState,
)

@Composable
fun MovieCategoryLabel(
    item: MovieCategoryLabelViewState,
    state: MutableState<Boolean>,
    onClick: () -> Unit,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .clickable { onClick.invoke() }
    ) {
        Text(
            text = when (item.categoryText) {
                is MovieCategoryTextString -> item.categoryText.category;
                is MovieCategoryTextStringResource -> stringResource(id = item.categoryText.category)
            },
            fontSize = 16.sp,
            textDecoration = if (state.value) TextDecoration.Underline else TextDecoration.None,
            fontWeight = if (state.value) FontWeight.Bold else FontWeight.Normal,
            color = if (state.value) Color.Black else Color.Gray
        )
    }
}

@Preview
@Composable
fun MovieCategoryLabelPreview() {
    val movieCategoryViewState = MovieCategoryLabelViewState(9, MovieCategoryTextString("Comedy"))
    val state = remember {
        mutableStateOf(false)
    }
    val onClick = {
        state.value = !state.value
    }

    MovieCategoryLabel(movieCategoryViewState, state, onClick, Modifier)
}