package com.driuft.random_pets_starter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.asssignment5.R

class PetAdapter(private val nameList: List<String>, private val URLlist: List<String>): RecyclerView.Adapter<PetAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name : TextView
        val url: TextView

        init {
            name = view.findViewById(R.id.textView)
            url = view.findViewById(R.id.textView2)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.info, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PetAdapter.ViewHolder, position: Int) {
        val name = nameList[position]
        holder.name.text = name

        val url = URLlist[position]
        holder.url.text = url
        holder.url.setTextColor(holder.url.context.resources.getColor(android.R.color.holo_blue_light))
    }

    override fun getItemCount() = nameList.size
}