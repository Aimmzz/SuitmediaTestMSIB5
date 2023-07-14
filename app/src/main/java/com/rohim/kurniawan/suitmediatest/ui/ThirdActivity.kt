package com.rohim.kurniawan.suitmediatest.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.rohim.kurniawan.suitmediatest.adapter.UserAdapter
import com.rohim.kurniawan.suitmediatest.databinding.ActivityThirdBinding
import com.rohim.kurniawan.suitmediatest.model.ApiResponse
import com.rohim.kurniawan.suitmediatest.model.DataItem
import com.rohim.kurniawan.suitmediatest.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ThirdActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var binding: ActivityThirdBinding
    private lateinit var adapterUser: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = activity3

        binding.refresh.setOnRefreshListener(this)

        adapterUser = UserAdapter()
        binding.rvUser.layoutManager = LinearLayoutManager(this)
        binding.rvUser.adapter = adapterUser
        binding.rvUser.setHasFixedSize(true)
        adapterUser.setClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(user: DataItem) {
                Intent(this@ThirdActivity, SecondActivity::class.java).also {
                    it.putExtra(SecondActivity.EXTRA_NAME, user.firstName)
                    startActivity(it)
                    page = 1
                    finish()
                }
            }
        })

        getAllUsers(false)
    }

    private fun getAllUsers(isRefresh: Boolean){
        isLoading = true
        if (!isRefresh) {
            binding.progressbar.visibility = View.VISIBLE}

        Handler().postDelayed({
            val params = HashMap<String, String>()
            params["page"] = page.toString()
            ApiConfig.getApiService().getUser(params).enqueue(object : Callback<ApiResponse> {
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    if (response.isSuccessful) {
                        totalPage = response.body()?.totalPages!!
                        val listUsers = response.body()?.data
                        Log.d(activity3, "onResponse: $listUsers")
                        if(listUsers!!.isNotEmpty()){
                            adapterUser.setList(listUsers as ArrayList<DataItem>)
                        }
                        binding.progressbar.visibility = View.GONE
                        isLoading = false
                        binding.refresh.isRefreshing =false
                    }
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    Log.d("onFailure", t.message.toString())
                    Toast.makeText(this@ThirdActivity, "Connection Failed...", Toast.LENGTH_SHORT).show()
                    binding.progressbar.visibility = View.GONE
                    isLoading = false
                    binding.refresh.isRefreshing =false
                }

            }) }, 3000)
    }

    override fun onRefresh() {
        adapterUser.clearUsers()
        page = 2
        getAllUsers(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        private var activity3 ="Third Screen"
        private var isLoading = false
        private var page: Int = 1
        private var totalPage: Int = 1
    }
}