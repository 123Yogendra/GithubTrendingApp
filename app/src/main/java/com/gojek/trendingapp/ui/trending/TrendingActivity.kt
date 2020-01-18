package com.gojek.trendingapp.ui.trending

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_trending, menu)
        return true
    }

}
