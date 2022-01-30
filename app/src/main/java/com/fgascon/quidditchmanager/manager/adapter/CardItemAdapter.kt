package com.fgascon.quidditchmanager.manager.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fgascon.quidditchmanager.R
import com.fgascon.quidditchmanager.manager.CardItem

class CardItemAdapter(private val cardItemList: List<CardItem>): RecyclerView.Adapter<CardItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CardItemViewHolder(layoutInflater.inflate(R.layout.item_card, parent, false))
    }

    override fun onBindViewHolder(holder: CardItemViewHolder, position: Int) {
    }

    override fun getItemCount(): Int = cardItemList.size


}