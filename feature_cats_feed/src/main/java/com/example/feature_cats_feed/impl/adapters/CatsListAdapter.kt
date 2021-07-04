package com.example.feature_cats_feed.impl.adapters

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.data.domain.model.CatEntity
import com.example.feature_cats_feed.R
import com.example.feature_cats_feed.databinding.ItemCatBinding
import com.example.feature_cats_feed.impl.adapters.diff_util.CatsListDiffUtilCallback
import com.example.module_base_sdk.utils.getDimenPx
import com.example.module_base_sdk.utils.loadImage
import com.example.module_base_sdk.utils.setOnClickListenerSafe
import com.example.module_base_sdk.view_holder.BindingViewHolder

class CatsListAdapter(
    private val isFavouriteClickListener: (CatEntity) -> Unit
) : RecyclerView.Adapter<CatsListAdapter.CatViewHolder>() {

    private val catsList: MutableList<CatEntity> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CatViewHolder(
            ItemCatBinding.inflate(inflater, parent, false),
            isFavouriteClickListener
        )
    }

    override fun getItemCount() = catsList.size

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val item = getItemFromPosition(position)
        holder.bind(item)
    }

    override fun onViewRecycled(holder: CatViewHolder) {
        super.onViewRecycled(holder)
        val image = holder.binding.catImage
        Glide.with(image.context).clear(image)
        image.setImageDrawable(null)
        holder.binding.run {
            isFavourite.visibility = GONE
            imageError.visibility = GONE
            imageSpinner.visibility = VISIBLE
            imageStub.visibility = VISIBLE
        }
    }

    private fun getItemFromPosition(position: Int) = catsList[position]

    fun submitItems(newItems: List<CatEntity>) {
        val diffResult = DiffUtil.calculateDiff(CatsListDiffUtilCallback(catsList, newItems))
        diffResult.dispatchUpdatesTo(this)

        catsList.clear()
        catsList.addAll(newItems)
    }

    inner class CatViewHolder(
        binding: ItemCatBinding,
        val isFavouriteClickListener: (CatEntity) -> Unit
    ) : BindingViewHolder<ItemCatBinding, CatEntity>(binding) {

        override fun bind(item: CatEntity) {
            binding.catImage.loadImage(
                url = item.url,
                roundedFrameCorners = binding.catImage.getDimenPx(R.dimen.common_corner_radius),
                successLoad = ::onSuccessLoad,
                errorLoad = ::onErrorLoad
            )

            binding.isFavourite.setOnClickListenerSafe {
                isFavouriteClickListener(item)
            }

            binding.isFavourite.setImageResource(if (item.isFavourite) R.drawable.ic_round_star else R.drawable.ic_round_star_outline)
        }

        private fun onSuccessLoad() = binding.run {
            isFavourite.visibility = VISIBLE
            imageSpinner.visibility = GONE
            imageStub.visibility = GONE
        }

        private fun onErrorLoad() = binding.run {
            imageSpinner.visibility = GONE
            imageError.visibility = VISIBLE
        }
    }
}