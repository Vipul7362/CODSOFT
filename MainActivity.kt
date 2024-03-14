package com.example.quoteoftheday



import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var showQuoteButton:Button
    private lateinit var quote:TextView
    private lateinit var sharequote:Button
    private lateinit var favor:Button

    private val quotes = listOf(
        "Believe you can and you're halfway there.- Theodore Roosevelt",
        "Don't watch the clock; do what it does. Keep going. - Sam Levenson",
        "I've missed more than 9000 shots in my career. I've lost almost all my games. In all these years, failure is why I succeed. - Michael Jordan",
        "Life is what happens when you're busy making other plans. - John Lennon",
        "Spread love everywhere you go. Let no one ever come to you without leaving happier. - Mother Teresa",
        "The best way to predict the future is to create it. - Peter Drucker",
        "Believe you can and you're halfway there. - Theodore Roosevelt",
        "The only way to do great work is to love what you do. - Steve Jobs",
        "The greatest glory in living lies not in never falling, but in rising every time we fall. - Nelson Mandela",
        "The pessimist sees difficulty in every opportunity. The optimist sees opportunity in every difficulty. - Winston Churchill"
    )

    private val sharedPref: SharedPreferences by lazy {
        getSharedPreferences("favorite_quotes", Context.MODE_PRIVATE)
    }

    private val favoriteQuotes: MutableList<String> by lazy {
        mutableListOf<String>().apply {
            val savedQuotes = sharedPref.getStringSet("quotes", emptySet())
            addAll(savedQuotes.orEmpty())
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        quote=findViewById(R.id.quoteTextView)
        favor=findViewById(R.id.fav)
        val constraintLayout = findViewById<ConstraintLayout>(R.id.activity_main)

        showQuoteButton=findViewById(R.id.Refresh)
        showQuoteButton.setOnClickListener {
            var random = Random()
            var randomQuoteIndex = random.nextInt(quotes.size)
            quote.text = quotes[randomQuoteIndex]
            quote.setTextColor(getRandomColor())
          constraintLayout.setBackgroundColor(getRandomColor())
        }

        sharequote=findViewById(R.id.shareQuoteButton)
        sharequote.setOnClickListener {
            val currentQuote = quote.text.toString()
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            sharingIntent.putExtra(Intent.EXTRA_TEXT, currentQuote)
            startActivity(Intent.createChooser(sharingIntent, "Share quote using"))
        }
        favor.setOnClickListener {
            val currentQuote = quote.text.toString()
            if (currentQuote in favoriteQuotes) {
                favoriteQuotes.remove(currentQuote)
                Toast.makeText(this, "Removed from favorites", Toast.LENGTH_SHORT).show()
            } else {
                favoriteQuotes.add(currentQuote)
                Toast.makeText(this, "Added to favorites", Toast.LENGTH_SHORT).show()

                val intent = Intent(this,favquote::class.java)
                intent.putExtra("current_quote", currentQuote)
                startActivity(intent)

                saveFavoriteQuotes()
            }

        }
    }
    private fun getRandomColor(): Int {
        val random = java.util.Random()
        return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
    }



    private fun saveFavoriteQuotes() {
        sharedPref.edit().putStringSet("quotes", favoriteQuotes.toSet()).apply()
    }

    override fun onDestroy() {
        super.onDestroy()
        saveFavoriteQuotes()
    }
}



