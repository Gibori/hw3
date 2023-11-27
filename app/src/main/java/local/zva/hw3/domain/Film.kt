package local.zva.hw3.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film (
    val title: String,
    val poster: Int,
    val description: String,
    val rating: Float = 0f,
    var isInFavorites: Boolean = false
) : Parcelable