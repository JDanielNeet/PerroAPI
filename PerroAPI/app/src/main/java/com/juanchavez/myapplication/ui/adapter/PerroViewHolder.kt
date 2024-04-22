package com.juanchavez.myapplication.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.juanchavez.myapplication.data.remote.model.PerroDto
import com.juanchavez.myapplication.databinding.PerroElementBinding


class PerroViewHolder (private var binding: PerroElementBinding):
    RecyclerView.ViewHolder(binding.root){

        val ivThumbnail = binding.ivThumbnail

        fun bind(perro: PerroDto){
            binding.tvNombrePerro.text = perro.name
            binding.tvId.text = perro.idPerro.toString()
        }

}