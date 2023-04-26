package com.kg.pixabay

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.kg.pixabay.databinding.ItemImagesBinding

class PixaAdapter(val list: ArrayList<ImageModel>) : Adapter<PixaAdapter.PixaViewHolder>() {

    fun addImages(listImages: ArrayList<ImageModel>) {
        list.addAll(listImages)
    }


    class PixaViewHolder(var binding: ItemImagesBinding) : ViewHolder(binding.root) {
        fun bind(imageModel: ImageModel) {
            binding.imgPhoto.load(imageModel.largeImageURL)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PixaViewHolder {
        return PixaViewHolder(
            ItemImagesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PixaViewHolder, position: Int) {
        holder.bind(list[position])
    }
}