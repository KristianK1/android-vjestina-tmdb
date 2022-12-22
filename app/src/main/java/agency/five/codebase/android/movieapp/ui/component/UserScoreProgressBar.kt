package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.ui.theme.spacing
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
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


private const val STARTING_POSITION_OF_CIRCLE = -90f
private const val FULL_CIRClE = 360f

@Composable
fun UserScoreProgressBar(
    percentage: Float,
    value: Float,
    modifier: Modifier = Modifier,
    color: Color,
    strokeWidth: Float,
) {

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Canvas(
            modifier = modifier
                .fillMaxSize()
                .padding(MaterialTheme.spacing.extraSmall)
        ) {
            drawArc(
                color = color,
                startAngle = STARTING_POSITION_OF_CIRCLE,
                sweepAngle = FULL_CIRClE * percentage,
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
