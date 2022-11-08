package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.ui.theme.Spacing
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
import androidx.compose.ui.Alignment

@Composable
fun UserScoreProgressBar(
    percentage: Float,
    value: Float,
    modifier: Modifier = Modifier,
    color: Color,
    strokeWidth: Float,
) {
    val localSpacing = Spacing()
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Canvas(
            modifier = modifier
                .fillMaxSize()
                .padding(localSpacing.extraSmall)
        ) {
            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = 360f * percentage,
                useCenter = false,
                style = Stroke(
                    width = strokeWidth, cap = StrokeCap.Round
                )
            )
            drawCircle(
                color = color.copy(alpha = 0.1f), style = Stroke(
                    width = strokeWidth
                )
            )
        }
        Text(
            text = "$value",
            fontSize = 25.sp
        )
    }

}

@Preview
@Composable
private fun UserScoreProgressBarPreview() {
    val percentage = 0.15f;
    UserScoreProgressBar(
        percentage,
        percentage * 10,
        modifier = Modifier.size(60.dp),
        color = Color.Green,
        strokeWidth = 15f
    );
}