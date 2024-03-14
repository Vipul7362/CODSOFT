package com.example.quoteoftheday

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class favquote : AppCompatActivity() {

    private lateinit var favoriteQuoteTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fav_quote)

        favoriteQuoteTextView = findViewById(R.id.favq)

        val currentQuote = intent.getStringExtra("current_quote")
        favoriteQuoteTextView.text = currentQuote
    }

}

