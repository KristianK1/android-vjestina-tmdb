package agency.five.codebase.android.movieapp.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

data class CrewmanItemViewState(
    val name: String,
    val job: String,
)

@Composable
fun CrewmanItem(
    item: CrewmanItemViewState,
    ) {
    Column {
        Text(
            text = item.name,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = item.job,
            fontSize = 14.sp,
        )
    }
}
