package id.heycoding.thedoctors.data.remote.response

import com.google.gson.annotations.SerializedName

data class Specialization(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: String
)