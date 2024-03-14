package com.example.quoteoftheday

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FavoriteQuotesAdapter(private val favoriteQuotes: List<String>) : RecyclerView.Adapter<FavoriteQuotesAdapter.FavoriteQuotesViewHolder>() {

    class FavoriteQuotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val quoteTextView: TextView = itemView.findViewById(R.id.quoteTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteQuotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.quote_item, parent, false)
        return FavoriteQuotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteQuotesViewHolder, position: Int) {
        holder.quoteTextView.text = favoriteQuotes[position]
    }

    override fun getItemCount() = favoriteQuotes.size
}