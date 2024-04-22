package com.juanchavez.myapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.juanchavez.myapplication.data.remote.model.PerroDto
import com.juanchavez.myapplication.databinding.PerroElementBinding

class PerroAdapter(
    private val perros: List<PerroDto>,
    private val onPerroClicked: (PerroDto) -> Unit
) : RecyclerView.Adapter<PerroViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerroViewHolder{
        val binding = PerroElementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PerroViewHolder(binding)
    }

    override fun getItemCount(): Int = perros.size

    override fun onBindViewHolder(holder: PerroViewHolder, position: Int){
        val perro = perros[position]

        holder.bind(perro)

        Glide.with(holder.itemView.context)
            .load(perro.thumbnail)
            .into(holder.ivThumbnail)

        holder.itemView.setOnClickListener {
            onPerroClicked(perro)
        }

    }

}