package com.example.feature_cats_feed.impl.screens

import androidx.fragment.app.viewModels
import com.example.data.domain.model.CatEntity
import com.example.feature_cats_feed.impl.screens.base.BaseCatsFeedFragment
import com.example.feature_cats_feed.impl.view_model.CatsFavouritesViewModel


class CatsFavouritesFragment : BaseCatsFeedFragment() {

    override val viewModel: CatsFavouritesViewModel by viewModels { viewModelFactory }

    override fun clickCatIsFavourite(cat: CatEntity) = viewModel.removeCatFromFavourite(cat)
}