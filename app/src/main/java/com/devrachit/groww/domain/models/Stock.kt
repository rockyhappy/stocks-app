package com.devrachit.groww.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Stock(
    val changeAmount: String,
    val changePercentage: String,
    val price: String,
    val ticker: String,
    val volume: String
):Parcelable

