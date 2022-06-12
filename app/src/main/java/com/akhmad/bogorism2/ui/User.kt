package com.akhmad.bogorism2.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class User(
    var placename: String,
    var description: String,
    var gambar: Int,
    var rating: String,
) : Parcelable
