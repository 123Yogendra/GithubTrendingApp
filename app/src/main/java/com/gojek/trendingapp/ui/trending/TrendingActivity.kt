package com.gojek.trendingapp.ui.trending

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gojek.trendingapp.R

class TrendingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.trending_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, TrendingFregment.newInstance())
                .commitNow()
        }
    }

}
