package com.example.myapplication.components

import android.view.FrameMetrics.ANIMATION_DURATION
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import com.example.myapplication.ui.theme.backgroundColor
import com.example.myapplication.utils.ExpandAndShrinkAnimation
import com.example.myapplication.view_models.TaskViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskItem(
    viewModel: TaskViewModel,
    task: Task,
) {
    // Add our dismiss state for the animation
    val dismissState = rememberDismissState()
    // Swipe from left to right to dismiss
    val isDismissed =
        dismissState.isDismissed(DismissDirection.StartToEnd)

    val itemAppeared = remember { mutableStateOf(false) }

    LaunchedEffect(
        key1 = true,
    ) {
        itemAppeared.value = true
    }

    /* If the task has been dismissed, we want to play the swipe-to-delete
    animation, and once it's done, we'll play the task exit animation to
    make the swipe-to-dismiss background leave the view */
    LaunchedEffect(
        key1 = dismissState.isDismissed(DismissDirection.StartToEnd)
    ) {
        if (isDismissed) {
            itemAppeared.value = false
            delay(ANIMATION_DURATION.toLong())
            viewModel.deleteTask(task)
        }
    }

    itemAppeared.ExpandAndShrinkAnimation {
        // Wrap our component in the SwipeToDismiss component
        SwipeToDismiss(
            state = dismissState,
            modifier = Modifier,
            // Set our direction for swipe to dismiss
            directions = setOf(
                DismissDirection.StartToEnd
            ),
            // As we drag our component, this will be what is shown behind it
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
            // The component we will be swiping to dismiss
            dismissContent = {
                /* We will see a red background behind our card, so we'll
                wrap it in this column with our background color to hide
                the background for a cleaner look */
                Column(
                    modifier = Modifier.background(
                        MaterialTheme.colorScheme.backgroundColor
                    )
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