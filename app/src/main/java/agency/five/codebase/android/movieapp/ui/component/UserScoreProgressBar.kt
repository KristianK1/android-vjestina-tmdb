package agency.five.codebase.android.movieapp.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UserScoreProgressBar(
    value: Float,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .width(42.dp)
            .height(42.dp)
    ) {
        Canvas(
            modifier = modifier
                .fillMaxSize()
                .padding(2.dp)
        ) {
            drawArc(
                color = if (value <= 0.3) Color.Red else if (value <= 0.7) Color.Yellow else Color.Green,
                startAngle = -90f,
                sweepAngle = 360f * value,
                useCenter = false,
                style = Stroke(
                    width = 12f,
                    cap = StrokeCap.Round
                )
            )
        }

        Text(
            text = "${value * 10}",
            fontSize = 15.sp,
            modifier = modifier
                .width(42.dp)
                .height(42.dp)
                .padding(10.dp),
        )
    }
}

@Preview
@Composable
private fun UserScoreProgressBarPreview() {
    UserScoreProgressBar(0.726f)
}