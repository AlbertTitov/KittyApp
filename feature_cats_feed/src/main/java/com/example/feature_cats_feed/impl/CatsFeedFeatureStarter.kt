package com.example.feature_cats_feed.impl

import androidx.fragment.app.Fragment
import com.example.feature_cats_feed.impl.screens.CatsFeedGeneralFragment
import com.example.module_base_sdk.feature_api.FeatureStarter

object CatsFeedFeatureStarter : FeatureStarter {

    override fun getFlowFragment(): Fragment = CatsFeedGeneralFragment()
}