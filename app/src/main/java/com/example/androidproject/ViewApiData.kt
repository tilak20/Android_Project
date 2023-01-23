package com.example.androidproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidproject.Adapter.SetDataAdapter
import com.example.androidproject.ModelData.ApiMDItem
import com.example.androidproject.databinding.ActivityViewApidataBinding
import com.example.sqlite_db.DBhelper

class ViewApiData : AppCompatActivity() {

    lateinit var binding: ActivityViewApidataBinding
    var list = arrayListOf<ApiMDItem>()
    var dBhelper = DBhelper(this)
    lateinit var setDataAdapter : SetDataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityViewApidataBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)

        CustomerRv(list)

        setContentView(binding.root)
    }

    fun CustomerRv(list: ArrayList<ApiMDItem>) {

        setDataAdapter = SetDataAdapter(this, list)
        val lm = LinearLayoutManager(this)
        binding.RCView1.layoutManager = lm
        binding.RCView1.adapter = setDataAdapter
    }

    override fun onStart() {
        super.onStart()
        list = dBhelper.readData()
        CustomerRv(list)
    }
}