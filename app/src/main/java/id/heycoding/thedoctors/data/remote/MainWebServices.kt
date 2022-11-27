package id.heycoding.thedoctors.data.remote

import id.heycoding.thedoctors.data.remote.response.DoctorResponse
import io.reactivex.Observable
import retrofit2.http.*

interface MainWebServices {

    @GET(EndPoint.User.GET_ALL_DOCTORS)
    fun getDoctors(): Observable<DoctorResponse>

    object EndPoint {
        object User {
            const val GET_ALL_DOCTORS = "c9a2b598-9c93-4999-bd04-0194839ef2dc"
        }
    }
}