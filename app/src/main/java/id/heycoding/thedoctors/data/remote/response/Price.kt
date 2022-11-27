package id.heycoding.thedoctors.data.remote.response

import com.google.gson.annotations.SerializedName

data class Price(

    @field:SerializedName("formatted")
    val formatted: String,

    @field:SerializedName("raw")
    val raw: Int
)