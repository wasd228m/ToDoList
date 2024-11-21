import android.view.FrameMetrics.ANIMATION_DURATION
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.ui.draw.scale
import com.example.myapplication.components.TaskCard
import com.example.myapplication.utils.ExpandAndShrinkAnimation
import com.example.myapplication.view_models.Task
import com.example.myapplication.view_models.TaskViewModel
import kotlinx.coroutines.delay
import androidx.compose.material.rememberDismissState



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskItem(
    viewModel: TaskViewModel,
    task: Task,
) {
    // Dismiss state for swipe-to-dismiss action
    val dismissState = rememberDismissState()

    // Track whether the task has been dismissed
    val isDismissed = dismissState.isDismissed(DismissDirection.StartToEnd)

    val itemAppeared = remember { mutableStateOf(true) }

    LaunchedEffect(isDismissed) {
        if (isDismissed) {
            itemAppeared.value = false
            // Delay the task removal after swipe animation
            delay(ANIMATION_DURATION.toLong())
            viewModel.deleteTask(task)
        }
    }

    itemAppeared.ExpandAndShrinkAnimation {
        SwipeToDismiss(
            state = dismissState,
            modifier = Modifier.fillMaxWidth(),
            // Swipe direction
            directions = setOf(DismissDirection.StartToEnd),
            // Background shown during swipe
            background = {
                val color by animateColorAsState(
                    when (dismissState.targetValue) {
                        DismissValue.Default -> Color.White
                        else -> MaterialTheme.colorScheme.error
                    }
                )

                val scale by animateFloatAsState(
                    if (dismissState.targetValue == DismissValue.Default) 0.75f else 1f
                )

                Box(
                    Modifier
                        .fillMaxSize()
                        .background(color),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Delete Icon",
                        modifier = Modifier.scale(scale)
                    )
                }
            },
            dismissContent = {
                // Content of the task (card)
                Column(
                    modifier = Modifier.background(MaterialTheme.colorScheme.background)
                ) {
                    TaskCard(
                        task = task,
                        toggleCompleted = viewModel::toggleTaskCompleted
                    )
                }
            }
        )
    }
}