package id.heycoding.thedoctors.data.remote.response

import com.google.gson.annotations.SerializedName

data class Formats(

    @field:SerializedName("small")
    val small: String,

    @field:SerializedName("thumbnail")
    val thumbnail: String,

    @field:SerializedName("large")
    val large: String,

    @field:SerializedName("medium")
    val medium: String
)