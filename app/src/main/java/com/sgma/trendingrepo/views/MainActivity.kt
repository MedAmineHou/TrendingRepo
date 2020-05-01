package com.sgma.trendingrepo.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sgma.trendingrepo.R
import com.sgma.trendingrepo.views.trending.TrendingActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivity(Intent(this, TrendingActivity::class.java))
        finish()
    }
}
