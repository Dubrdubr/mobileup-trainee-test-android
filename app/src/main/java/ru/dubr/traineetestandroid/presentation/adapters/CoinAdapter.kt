package ru.dubr.traineetestandroid.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.dubr.traineetestandroid.R
import ru.dubr.traineetestandroid.databinding.ItemCoinBinding
import ru.dubr.traineetestandroid.domain.Coin

class CoinAdapter(
    private val listener: Listener
) : ListAdapter<Coin, CoinAdapter.CoinViewHolder>(CoinDiffCallback()), View.OnClickListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCoinBinding.inflate(layoutInflater, parent, false)

        binding.root.setOnClickListener(this)

        return CoinViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coin = getItem(position)
        if (coin != null) {
            holder.bind(coin)
        }
    }

    override fun onClick(v: View) {
        val coin = v.tag as Coin
        listener.onItemClick(coin)
    }

    class CoinViewHolder(
        private val binding: ItemCoinBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(coin: Coin) {
            binding.apply {
                root.tag = coin
                nameTextView.text = coin.name
                symbolTextView.text = coin.symbol
                priceTextView.text = coin.currentPrice.toString()
                percentageTextView.text = coin.priceChangePercentage24h.toString()
                Glide.with(iconImageView)
                    .load(coin.image)
                    .circleCrop()
                    .error(R.drawable.ic_coin_placeholder)
                    .into(iconImageView)
            }
        }
    }

    private class CoinDiffCallback() : DiffUtil.ItemCallback<Coin>() {

        override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem == newItem
        }
    }

    interface Listener {
        fun onItemClick(coin: Coin)
    }
}