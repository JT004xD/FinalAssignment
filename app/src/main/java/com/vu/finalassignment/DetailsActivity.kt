package com.vu.finalassignment

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.TextView

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_details)

        val entity = intent.getParcelableExtra<Entity>("entity")

        val property1TextView: TextView = findViewById(R.id.property1_textview)
        val property2TextView: TextView = findViewById(R.id.property2_textview)
        val descriptionTextView: TextView = findViewById(R.id.description_textview)

        property1TextView.text = entity?.property1
        property2TextView.text = entity?.property2
        descriptionTextView.text = entity?.description

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}