package id.heycoding.thedoctors.data.remote.response

import com.google.gson.annotations.SerializedName

data class Icon(

    @field:SerializedName("size_formatted")
    val sizeFormatted: String,

    @field:SerializedName("formats")
    val formats: Formats,

    @field:SerializedName("url")
    val url: String
)