package com.pschsch.vkgarbagecleaner.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pschsch.vkgarbagecleaner.R
import com.pschsch.vkgarbagecleaner.model.Item
import kotlinx.android.synthetic.main.recycler_view_vk_video_item.view.*

class MainAdapter(private val items: List<Item>) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recycler_view_vk_video_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = items.size

    private fun MainViewHolder.bind(position: Int) {
        videoName.text = items[position].title
        watchCount.text =
            watchCount.context.getString(R.string.views_count, items[position].views.toString())
        dateOfAdd.text = items[position].addingDate.toString()
    }

    inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val videoName: TextView = view.main_item_video_name
        val watchCount: TextView = view.main_item_watch_count
        val dateOfAdd: TextView = view.main_item_data_of_add
    }
}