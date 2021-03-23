package com.example.unreadcounterdrawable

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat

class MainActivity : AppCompatActivity() {

    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val iv = findViewById<ImageView>(R.id.iv)
        updateCounter(iv)

        iv.setOnClickListener {
            updateCounter(iv)
        }
    }

    private fun updateCounter(iv: ImageView) {
        iv.setImageDrawable(
                UnseenItemsDrawable(
                        originalDrawable = ResourcesCompat.getDrawable(resources, R.drawable.lite_ic_inbox, theme)!!,
                        unseenNumber = counter
                )
        )
        counter += 5
    }
}