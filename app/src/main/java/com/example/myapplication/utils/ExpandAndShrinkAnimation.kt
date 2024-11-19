package com.example.myapplication.utils

import android.view.FrameMetrics.ANIMATION_DURATION
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@Composable
fun MutableState<Boolean>.ExpandAndShrinkAnimation(
    content: @Composable () -> Unit,
) {
    AnimatedVisibility(
        visible = this.value,
        enter = expandVertically(
            animationSpec = tween(
                durationMillis = ANIMATION_DURATION
            )
        ),
        exit = shrinkVertically(
            animationSpec = tween(
                durationMillis = ANIMATION_DURATION
            )
        )
    ) {
        content()
    }
}