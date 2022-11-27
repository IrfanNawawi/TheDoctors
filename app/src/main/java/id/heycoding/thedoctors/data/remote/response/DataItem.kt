package id.heycoding.thedoctors.data.remote.response

import com.google.gson.annotations.SerializedName

data class DataItem(

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("about")
    val about: String,

    @field:SerializedName("photo")
    val photo: Photo,

    @field:SerializedName("experience")
    val experience: String,

    @field:SerializedName("about_preview")
    val aboutPreview: String,

    @field:SerializedName("doctor_id")
    val doctorId: String,

    @field:SerializedName("is_popular")
    val isPopular: Boolean,

    @field:SerializedName("price")
    val price: Price,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("specialization")
    val specialization: Specialization,

    @field:SerializedName("sip")
    val sip: String,

    @field:SerializedName("hospital")
    val hospital: List<HospitalItem>,

    @field:SerializedName("slug")
    val slug: String
)