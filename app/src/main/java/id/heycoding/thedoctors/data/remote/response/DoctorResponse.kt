package id.heycoding.thedoctors.data.remote.response

import com.google.gson.annotations.SerializedName

data class DoctorResponse(

    @field:SerializedName("data")
    val data: List<DataItem>,

    @field:SerializedName("message")
    val message: String = "",

    @field:SerializedName("status")
    val status: Boolean = false
)