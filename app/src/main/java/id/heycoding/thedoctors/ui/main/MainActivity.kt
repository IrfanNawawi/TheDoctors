package id.heycoding.thedoctors.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import id.heycoding.thedoctors.R
import id.heycoding.thedoctors.data.remote.response.DataItem
import id.heycoding.thedoctors.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private var activityMainBinding: ActivityMainBinding? = null
    private val mainViewModel: MainViewModel by viewModel()

    private lateinit var mainDoctorAdapter: MainDoctorAdapter
    private lateinit var doctorList: ArrayList<DataItem>
    private var specializationset = ""
    private var hospitalset = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding?.root)

        initView()
        doctorObserve()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun doctorObserve() {
        mainViewModel.isLoading.observe(this) { showLoading(it) }
        mainViewModel.message.observe(this) { showError(it) }
        mainViewModel.apply {
            getAllDoctors()
            listDoctorData.observe(this@MainActivity) {
                if (it != null) {
                    mainDoctorAdapter.setDoctorData(it)
                    mainDoctorAdapter.notifyDataSetChanged()
                    doctorList = it as ArrayList<DataItem>
                }
            }
        }
    }

    private fun showLoading(loading: Boolean?) {
        if (loading == true) {
            activityMainBinding?.shimmerRvDoctor?.startShimmer()
            activityMainBinding?.shimmerRvDoctor?.visibility = View.VISIBLE
        } else {
            activityMainBinding?.shimmerRvDoctor?.stopShimmer()
            activityMainBinding?.shimmerRvDoctor?.visibility = View.GONE
        }
    }

    private fun showError(message: String?) {
        showToast(message)
    }

    private fun showToast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun initView() {
        doctorList = ArrayList()
        mainDoctorAdapter = MainDoctorAdapter()
        activityMainBinding?.searchDoctor?.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterDoctor(newText)
                return false
            }
        })

        val adapterHospital = ArrayAdapter.createFromResource(
            this,
            R.array.hospitals,
            android.R.layout.simple_spinner_item
        )
        adapterHospital.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        activityMainBinding?.spinnerHospital?.adapter = adapterHospital
        activityMainBinding?.spinnerHospital?.count?.minus(1)
        activityMainBinding?.spinnerHospital?.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    hospitalset = parent.getItemAtPosition(position).toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }

        val adapterSpecialization = ArrayAdapter.createFromResource(
            this,
            R.array.specialization,
            android.R.layout.simple_spinner_item
        )
        adapterSpecialization.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        activityMainBinding?.spinnerSpecialization?.adapter = adapterSpecialization
        activityMainBinding?.spinnerSpecialization?.count?.minus(1)
        activityMainBinding?.spinnerSpecialization?.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    specializationset = parent.getItemAtPosition(position).toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }

        activityMainBinding?.apply {
            rvDoctor.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                setHasFixedSize(true)
                adapter = mainDoctorAdapter
            }
        }
    }

    private fun filterDoctor(text: String?) {
        val filteredList: ArrayList<DataItem> = ArrayList()
        for (item in doctorList) {
            if (item.name.lowercase().contains(text?.lowercase().toString()) &&
                item.hospital[0].name.contains(hospitalset) &&
                item.specialization.name.contains(specializationset)
            ) filteredList.add(item)
        }

        if (filteredList.isEmpty()) doctorObserve() else mainDoctorAdapter.filterList(
            filteredList
        )
    }

    override fun onResume() {
        super.onResume()
        doctorObserve()
    }

    override fun onDestroy() {
        super.onDestroy()
        activityMainBinding = null
    }
}