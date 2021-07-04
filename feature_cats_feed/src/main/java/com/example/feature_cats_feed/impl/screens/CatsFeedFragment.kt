package com.example.feature_cats_feed.impl.screens

import androidx.fragment.app.viewModels
import com.example.data.domain.model.CatEntity
import com.example.feature_cats_feed.impl.screens.base.BaseCatsFeedFragment
import com.example.feature_cats_feed.impl.view_model.CatsFeedViewModel


class CatsFeedFragment : BaseCatsFeedFragment() {

    override val viewModel: CatsFeedViewModel by viewModels { viewModelFactory }

    override fun clickCatIsFavourite(cat: CatEntity) = viewModel.toggleCatIsFavourite(cat)
}