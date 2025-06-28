package com.devrachit.groww.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DisplayPassData(
    val title: String,
    val list: List<Stock>
):Parcelable
