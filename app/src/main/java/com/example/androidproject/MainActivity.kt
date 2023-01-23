package com.example.androidproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.androidproject.Api.ApiData.Companion.getRetrofit
import com.example.androidproject.Api.ApiInterface
import com.example.androidproject.ModelData.ApiMDItem
import com.example.androidproject.databinding.ActivityMainBinding
import com.example.sqlite_db.DBhelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var list = arrayListOf<ApiMDItem>()
    var dBhelper  = DBhelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        initClick()
        setContentView(binding.root)
    }

    fun initClick(){
//        binding.btnGetData.setOnClickListener {
//
//            binding.btnGetData.visibility = View.GONE
//            binding.btnNext.visibility = View.VISIBLE
//        }

        binding.btnNext.setOnClickListener {

            var intent = Intent(this,ViewApiData::class.java)
            startActivity(intent)
        }
    }

    fun apiCall() {
        var apiInterface = getRetrofit().create(ApiInterface::class.java)
        apiInterface.getData().enqueue(object : Callback<List<ApiMDItem>> {
            override fun onResponse(call: Call<List<ApiMDItem>>, response: Response<List<ApiMDItem>>) {

                list = response.body() as ArrayList<ApiMDItem>

                for (x in list) {
                    dBhelper.insertData(
                        x.title.toString(),
                        x.albumId,
                        x.url.toString(),
                        x.thumbnailUrl.toString()
                    )
                }
            }

            override fun onFailure(call: Call<List<ApiMDItem>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onStart() {
        super.onStart()
        apiCall()
    }
}
