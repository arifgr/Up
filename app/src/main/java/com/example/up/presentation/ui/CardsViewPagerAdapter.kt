package com.example.up.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.up.databinding.ItemCardInfoBinding
import com.example.up.domain.model.Card

class CardsViewPagerAdapter(private var cards: List<Card>) :
    RecyclerView.Adapter<CardsViewPagerAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemCardInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItems(card: Card) {
            Glide.with(binding.root).load(card.image).into(binding.imageViewCard)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCardInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(cards[position])
    }

    override fun getItemCount(): Int {
        return cards.size
    }
}