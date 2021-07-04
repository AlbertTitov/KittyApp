package com.example.feature_cats_feed.impl.adapters

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.feature_cats_feed.impl.screens.CatsFavouritesFragment
import com.example.feature_cats_feed.impl.screens.CatsFeedFragment

internal class CatsFeedPagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int = CatsFeedFragmentPosition.values().size

    override fun getItem(position: Int) = when(CatsFeedFragmentPosition.getFromPosition(position)) {
        CatsFeedFragmentPosition.CATS_FEED       -> CatsFeedFragment()
        CatsFeedFragmentPosition.CATS_FAVOURITES -> CatsFavouritesFragment()
    }

    override fun getPageTitle(position: Int) = when(CatsFeedFragmentPosition.getFromPosition(position)) {
        CatsFeedFragmentPosition.CATS_FEED       -> CATS_FEED_TAB_NAME
        CatsFeedFragmentPosition.CATS_FAVOURITES -> CATS_FAVOURITES_TAB_NAME
    }

    private companion object {
        const val CATS_FEED_TAB_NAME = "Коты"
        const val CATS_FAVOURITES_TAB_NAME = "Избранное"
    }
}

private enum class CatsFeedFragmentPosition(val position: Int) {

    CATS_FEED(0),
    CATS_FAVOURITES(1);

    companion object {

        fun getFromPosition(position: Int) = values().first { it.position == position }
    }
}