package com.example.weather.common.RecyclerView

import android.graphics.drawable.Icon
import android.widget.ImageView
import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector

data class Item(
    val imageResId: String,
    val temp: String
)
