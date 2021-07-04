package com.example.feature_cats_feed.impl.screens

import android.os.Bundle
import com.example.di_proxy.InjectUtils
import com.example.feature_cats_feed.databinding.FragmentCatsFeedGeneralBinding
import com.example.feature_cats_feed.di.CatsFeedComponent
import com.example.feature_cats_feed.di.CatsFeedComponentHolder
import com.example.feature_cats_feed.di.DaggerCatsFeedComponent
import com.example.feature_cats_feed.impl.adapters.CatsFeedPagerAdapter
import com.example.module_base_sdk.fragment.BindingFragment


/**
 *  Если во flow данного модуля будут переходы на другие экраны, то предполагаю, что данный фрагмент
 *  будет фрагментом-контейнером и в нём будут заменяться путём fm.replace() фрагменты данной фичи,
 *  соответственно каждый такой фрагмент сможет заинжектить компонент CatsFeedComponent, обращаясь к
 *  (parentFragment as CatsFeedComponentHolder)
 *
 **/

class CatsFeedGeneralFragment :
    BindingFragment<FragmentCatsFeedGeneralBinding>(FragmentCatsFeedGeneralBinding::inflate), CatsFeedComponentHolder {

    private lateinit var _component: CatsFeedComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _component = createCatsFeedComponent()
    }

    override fun getComponent() = _component

    override fun initView() {
        binding.viewPager.adapter = CatsFeedPagerAdapter(childFragmentManager)
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }

    private fun createCatsFeedComponent(): CatsFeedComponent = DaggerCatsFeedComponent
        .builder()
        .catsAppComponent(InjectUtils.provideBaseComponent(requireActivity().applicationContext))
        .build()
}