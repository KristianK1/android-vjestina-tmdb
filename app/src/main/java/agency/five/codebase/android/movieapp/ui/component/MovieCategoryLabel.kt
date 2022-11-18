package agency.five.codebase.android.movieapp.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import agency.five.codebase.android.movieapp.ui.theme.spacing
import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import agency.five.codebase.android.movieapp.R
import androidx.compose.runtime.*
import androidx.compose.ui.res.dimensionResource

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
    onClick: (item: MovieCategoryLabelViewState) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .clickable { onClick(item) }
            .width(IntrinsicSize.Max),
    )
    {
        Text(
            text = when (item.categoryText) {
                is MovieCategoryTextString -> item.categoryText.category;
                is MovieCategoryTextStringResource -> stringResource(id = item.categoryText.category)
            },
            fontSize = 16.sp,
            fontWeight = if (item.isSelected) FontWeight.Bold else FontWeight.Normal,
            color = if (item.isSelected) Color.Black else Color.Gray
        )
        Box(
            modifier = Modifier
                .padding(top = MaterialTheme.spacing.extraSmall)
                .background(if (item.isSelected) Color.Black else Color.Transparent)
                .height(dimensionResource(id = R.dimen.movie_category_label_underline_height))
                .fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun MovieCategoryLabelPreview() {
    var movieCategoryViewState by remember {
        mutableStateOf(
            MovieCategoryLabelViewState(
                9,
                true,
                MovieCategoryTextString("Comedy")
            )
        )
    }

    val onClick = { item: MovieCategoryLabelViewState ->
        movieCategoryViewState = movieCategoryViewState.copy(isSelected = !item.isSelected)
    }

    MovieCategoryLabel(movieCategoryViewState, onClick, Modifier)

}