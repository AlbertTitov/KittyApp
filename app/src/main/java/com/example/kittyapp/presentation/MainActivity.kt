package com.example.kittyapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.feature_cats_feed.impl.CatsFeedFeatureStarter
import com.example.kittyapp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val catsFeedFragment = CatsFeedFeatureStarter.getFlowFragment()

        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.featureContainer,
                catsFeedFragment
            )
            .commit()
    }
}