package com.example.androidproject.Adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidproject.ModelData.ApiMDItem
import com.example.androidproject.R
import com.example.androidproject.ViewApiData
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.squareup.picasso.Picasso

class SetDataAdapter(var viewApiData: ViewApiData,var list: ArrayList<ApiMDItem>) : RecyclerView.Adapter<SetDataAdapter.ViewData>() {

    class ViewData(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var txtTitle = itemView.findViewById<TextView>(R.id.txtTitle)
        var imgThumbnail = itemView.findViewById<ImageView>(R.id.imgThumbnail)
        var CrdView1 = itemView.findViewById<CardView>(R.id.CrdView1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewData {
        val view = LayoutInflater.from(viewApiData).inflate(R.layout.set_api_data, parent, false)
        return ViewData(view)
    }

    override fun onBindViewHolder(holder: ViewData, position: Int) {
        holder.txtTitle.text = list[position].title.toString()
//        Glide.with(viewApiData).load(Uri.parse(list[position].thumbnailUrl))
//            .placeholder(R.drawable.ic_launcher_background).into(holder.imgThumbnail)

        Picasso.get().load(list[position].thumbnailUrl).into(holder.imgThumbnail);

        holder.CrdView1.setOnClickListener {
            BottomDialog(position)
        }
    }

    fun BottomDialog(position: Int) {
        var dialog = BottomSheetDialog(viewApiData)
        dialog.setContentView(R.layout.full_data)

        var imgFull = dialog.findViewById<ImageView>(R.id.imgFull)
        var txtTitle = dialog.findViewById<TextView>(R.id.txtTitle)
        Picasso.get().load(list[position].url).into(imgFull);
        txtTitle?.text = list[position].title.toString()

        dialog.show()
    }

    override fun getItemCount(): Int {
        return list.size
    }

}