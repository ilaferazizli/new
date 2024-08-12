package com.schooltools.retrofit

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.schooltools.retrofit.dataClasses.News
import com.schooltools.retrofit.databinding.RecyclerRowBinding
import com.squareup.picasso.Picasso

class Adapter(private val newsList :News): RecyclerView.Adapter<Adapter.Holder>() {
    class Holder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return newsList.items.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.binding.title.text = newsList.items[position].title
        holder.binding.snippet.text = newsList.items[position].snippet
        holder.binding.publisher.text = newsList.items[position].publisher
        val imageView: ImageView = holder.binding.image
        if (newsList.items[position].images.thumbnail != null) {
            Picasso.get().load(newsList.items[position].images.thumbnail).into(imageView)
        }
        holder.binding.moreInfo.setOnClickListener {
            val url = newsList.items[position].newsUrl
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            holder.itemView.context.startActivity(intent)
        }
    }
}