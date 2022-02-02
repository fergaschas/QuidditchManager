package com.fgascon.quidditchmanager.manager.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fgascon.quidditchmanager.R
import com.fgascon.quidditchmanager.data.model.Notification

class CardItemAdapter(
    private val cardItemList: List<Notification>
) : RecyclerView.Adapter<CardItemAdapter.CardItemViewHolder>() {

    class CardItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // defines every usable view
        val title: TextView = view.findViewById(R.id.cardTitle)
        val description: TextView = view.findViewById(R.id.cardDescription)
        val image: ImageView = view.findViewById(R.id.cardImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardItemViewHolder {
        //create and returns a card view
        val layoutInflater = LayoutInflater.from(parent.context)
        return CardItemViewHolder(layoutInflater.inflate(R.layout.item_card, parent, false))
    }

    override fun onBindViewHolder(holder: CardItemViewHolder, position: Int) {
        // fills data in holder views
        val item = cardItemList[position]

        holder.apply {
            title.text = item.title
            description.text = item.description
            image.setImageResource(R.drawable.ic_launcher_foreground)
        }

    }

    override fun getItemCount(): Int = cardItemList.size
}