package com.example.feature_cats_feed.impl.screens.base

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.data.domain.model.CatEntity
import com.example.feature_cats_feed.R
import com.example.feature_cats_feed.databinding.FragmentCatsFeedBinding
import com.example.feature_cats_feed.di.CatsFeedComponentHolder
import com.example.feature_cats_feed.impl.adapters.CatsListAdapter
import com.example.feature_cats_feed.impl.view_model.base.BaseCatsFeedViewModel
import com.example.module_base_sdk.fragment.BindingFragment
import javax.inject.Inject

abstract class BaseCatsFeedFragment :
    BindingFragment<FragmentCatsFeedBinding>(FragmentCatsFeedBinding::inflate) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    protected abstract val viewModel: BaseCatsFeedViewModel

    private val catsAdapter = CatsListAdapter(::clickCatIsFavourite)

    protected lateinit var listLayoutManager: LinearLayoutManager

    private var errorDialog: Dialog? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (parentFragment as CatsFeedComponentHolder).getComponent().inject(this)
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            viewModel.getCatsList()
            viewModel.observeUpdatedCatsList()
        }
    }

    override fun initView() {
        listLayoutManager = LinearLayoutManager(context)
        initCatList()
    }

    private fun initCatList() = binding.catsList.apply {
        layoutManager = listLayoutManager
        setHasFixedSize(true)
        adapter = catsAdapter
        addScrollStateListener()
    }

    override fun bindView() {
        viewModel.catsState bindTo ::showCats
        viewModel.errorCommand bindTo ::showOneButtonDialog
        viewModel.loadingState bindTo ::setIsLoading
    }

    abstract fun clickCatIsFavourite(cat: CatEntity)

    private fun showCats(cats: List<CatEntity>) {
        val initialSize = catsAdapter.itemCount
        catsAdapter.submitItems(cats)

        /**
         *  ???????? initialSize == catsAdapter.itemCount, ???? ?????? ???????????? ????????????
         *  ???????? initialSize > catsAdapter.itemCount, ???? ?????? ???????????????? ???? ???????????? ??????????????????, ??
         *  ?? shift ????????, ???????????????????????? ?????? ?????????????? ??????????????????, ???? ??????????????????
         *
         *  initialSize > 0 ????????????, ?????? ???????????????????? ?????????????????? ???? 1-?? ???? 2-?? ???????????????? ?????? ?????????????????????? ??????????????
         *
         * **/
        if (initialSize > 0 && initialSize < catsAdapter.itemCount) {
            binding.catsList.smoothScrollBy(0, INSERT_NEW_CATS_SHIFT)
        }
    }

    private fun setIsLoading(isLoading: Boolean) {
        binding.loadingListSpinner.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showOneButtonDialog(error: Throwable) {
        errorDialog?.dismiss()
        errorDialog = AlertDialog.Builder(requireContext())
            .setMessage(error.message ?: getString(R.string.common_error_text))
            .setPositiveButton(getString(R.string.close_button_text)) { _, _ ->
                errorDialog?.dismiss()
            }
            .setCancelable(true)
            .show()
    }

    private fun RecyclerView.addScrollStateListener() =
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {}

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && listLayoutManager.findLastVisibleItemPosition() == listLayoutManager.itemCount - 1
                ) {
                    viewModel.getCatsList()
                }
            }
        })

    private companion object {
        const val INSERT_NEW_CATS_SHIFT = 60
    }
}