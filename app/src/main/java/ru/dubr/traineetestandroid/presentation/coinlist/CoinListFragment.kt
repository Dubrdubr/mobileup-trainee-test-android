package ru.dubr.traineetestandroid.presentation.coinlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.AndroidEntryPoint
import ru.dubr.traineetestandroid.R
import ru.dubr.traineetestandroid.databinding.FragmentCoinListBinding
import ru.dubr.traineetestandroid.databinding.PartResultBinding
import ru.dubr.traineetestandroid.domain.Coin
import ru.dubr.traineetestandroid.presentation.adapters.CoinAdapter

@AndroidEntryPoint
class CoinListFragment : Fragment(R.layout.fragment_coin_list) {

    private var fragmentCoinListBinding: FragmentCoinListBinding? = null

    private val viewModel: CoinListViewModel by viewModels()
    private lateinit var coinAdapter: CoinAdapter
    private lateinit var mainActivity: AppCompatActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = FragmentCoinListBinding.inflate(inflater, container, false)
        fragmentCoinListBinding = binding

        mainActivity = activity as AppCompatActivity
        mainActivity.supportActionBar?.hide()

        setupAdapter()

        viewModel.state.observe(viewLifecycleOwner) { viewState ->
            renderResult(binding.root, viewState)
        }
        binding.chipToolbar.chipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
            val chip = group.findViewById<Chip>(checkedIds.first())
            val currency = chip.text.toString()
            if (currency.isNotBlank()) {
                updateCoinList(currency)
            }
        }
        binding.apply {
            swipeRefresh.setOnRefreshListener {
                swipeRefresh.isRefreshing = false
                val currentCurrency = chipToolbar.chipGroup.getCurrentCurrency()
                if (currentCurrency != "null") {
                    viewModel.getAllCoins(currentCurrency)
                }

            }
        }

        return binding.root
    }

    private fun setupAdapter() {
        coinAdapter = CoinAdapter(object : CoinAdapter.Listener {
            override fun onItemClick(coin: Coin) {
                onItemPressed(coin)
            }
        })
        fragmentCoinListBinding?.coinRecyclerView?.apply {
            adapter = coinAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun renderResult(root: ViewGroup, state: CoinListViewModel.ViewStateCoinList) {
        val binding = PartResultBinding.bind(root)
        coinAdapter.submitList(state.coinList)
        binding.progressBar.visibility = if (state.isLoading) View.VISIBLE else View.INVISIBLE
        binding.errorContainer.visibility = if (state.showError) View.VISIBLE else View.INVISIBLE
        binding.tryAgainButton.setOnClickListener { viewModel.getAllCoins() }
    }

    private fun onItemPressed(coin: Coin) {
        val direction = CoinListFragmentDirections.actionCoinListFragmentToCoinInfoFragment(
            coin.id, coin.name
        )
        findNavController().navigate(direction)
    }

    private fun updateCoinList(currency: String) {
        viewModel.getAllCoins(currency)
    }

    // Get current currency symbol from chips
    private fun ChipGroup.getCurrentCurrency(): String {
        val chipId = fragmentCoinListBinding?.chipToolbar?.chipGroup?.checkedChipId
        val chip = chipId?.let {
            this.findViewById<Chip>(it)
        }
        return chip?.text.toString()
    }

    override fun onDestroyView() {
        fragmentCoinListBinding = null
        super.onDestroyView()
    }
}

