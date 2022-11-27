package id.heycoding.thedoctors.data.repository

import android.annotation.SuppressLint
import id.heycoding.thedoctors.data.remote.MainWebServices
import id.heycoding.thedoctors.data.remote.response.DoctorResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainRepository(private val mService: MainWebServices) {

    @SuppressLint("CheckResult")
    fun getDoctor(
        responseHandler: (DoctorResponse) -> Unit,
        errorHandler: (Throwable) -> Unit
    ) {
        val listDoctors = mService.getDoctors()
        listDoctors.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(it)
            }, {
                errorHandler(it)
            })
    }
}