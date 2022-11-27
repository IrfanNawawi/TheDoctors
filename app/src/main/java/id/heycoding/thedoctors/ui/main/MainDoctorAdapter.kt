package id.heycoding.thedoctors.ui.main

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.heycoding.thedoctors.R
import id.heycoding.thedoctors.data.remote.response.DataItem
import id.heycoding.thedoctors.databinding.ItemDoctorBinding
import id.heycoding.thedoctors.util.Helper


class MainDoctorAdapter : RecyclerView.Adapter<MainDoctorAdapter.ViewHolder>() {
    private var listDoctorData = ArrayList<DataItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemDoctorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listDoctorData[position])
    }

    override fun getItemCount(): Int = listDoctorData.size

    fun filterList(filterList: ArrayList<DataItem>) {
        listDoctorData = filterList
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemDoctorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(doctor: DataItem) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(doctor.photo.url)
                    .into(imgPhoto)

                tvNameUser.text = doctor.name
                doctor.hospital.map {
                    tvHospital.text = it.name
                }
                tvSpecialization.text = doctor.specialization.name
                tvAbout.text = doctor.aboutPreview.replace("&nbsp;", " ")
                tvPrice.text = Helper.rupiahFormat(doctor.price.raw)
                tvOnline.text = if (doctor.specialization.name == "Anak") {
                    val imgOffline: Drawable =
                        tvOnline.context.resources.getDrawable(R.drawable.bg_dot_offline)
                    tvOnline.setCompoundDrawablesWithIntrinsicBounds(imgOffline, null, null, null)
                    tvOnline.context.getString(R.string.txt_offline)
                } else {
                    val imgOnline: Drawable =
                        tvOnline.context.resources.getDrawable(R.drawable.bg_dot_online)
                    tvOnline.setCompoundDrawablesWithIntrinsicBounds(imgOnline, null, null, null)
                    tvOnline.context.getString(R.string.txt_online)
                }
            }
        }
    }

    fun setDoctorData(doctor: List<DataItem>) {
        val diffCallback = MainDoctorCallback(listDoctorData, doctor)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        listDoctorData.clear()
        listDoctorData.addAll(doctor)
        diffResult.dispatchUpdatesTo(this)
    }
}