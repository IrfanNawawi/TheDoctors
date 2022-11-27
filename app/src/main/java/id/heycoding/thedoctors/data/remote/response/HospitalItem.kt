package id.heycoding.thedoctors.data.remote.response

import com.google.gson.annotations.SerializedName

data class HospitalItem(

    @field:SerializedName("image")
    val image: Image,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("icon")
    val icon: Icon,

    @field:SerializedName("id")
    val id: String
)