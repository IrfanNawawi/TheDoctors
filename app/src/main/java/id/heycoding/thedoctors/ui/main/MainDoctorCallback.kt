package id.heycoding.thedoctors.ui.main

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import id.heycoding.thedoctors.data.remote.response.DataItem

class MainDoctorCallback(
    private val oldList: List<DataItem>,
    private val newList: List<DataItem>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val (_, valueOld, nameOld) = oldList[oldItemPosition]
        val (_, valueNew, nameNew) = newList[newItemPosition]
        return nameOld == nameNew && valueOld == valueNew
    }

    @Nullable
    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}