package ru.dubr.traineetestandroid.presentation.adapters

import android.graphics.Color
import android.icu.number.NumberFormatter
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
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*
import java.util.Locale.US

class CoinAdapter(
    private val listener: Listener,
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
        private val binding: ItemCoinBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(coin: Coin) {

            val numberFormat = getNumberFormat(coin.currency)

            binding.apply {
                root.tag = coin
                nameTextView.text = coin.name
                symbolTextView.text = coin.symbol
                priceTextView.text = numberFormat.format(coin.currentPrice)
                setPercentageText(coin.priceChangePercentage24h)
                Glide.with(iconImageView)
                    .load(coin.image)
                    .circleCrop()
                    .error(R.drawable.ic_coin_placeholder)
                    .into(iconImageView)
            }
        }

        private fun setPercentageText(changePercentage: Double) {
            val context = binding.root.context

            binding.percentageTextView.apply {
                text = if (changePercentage < 0.0) {
                    setTextColor(Color.parseColor("#EB5757"))
                    context.getString(R.string.percentage_format_negative, changePercentage)
                } else {
                    setTextColor(Color.parseColor("#2A9D8F"))
                    context.getString(R.string.percentage_format_positive, changePercentage)
                }
            }
        }

        private fun getNumberFormat(coinCurrency: String): NumberFormat {
            val numberFormat = NumberFormat.getCurrencyInstance(Locale.US)
            numberFormat.currency = Currency.getInstance(coinCurrency.uppercase())
            return numberFormat
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