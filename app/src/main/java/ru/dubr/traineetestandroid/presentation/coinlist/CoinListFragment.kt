package ru.dubr.traineetestandroid.presentation.coinlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = FragmentCoinListBinding.inflate(inflater, container, false)

        setupAdapter(binding)
        viewModel.state.observe(viewLifecycleOwner) { viewState ->
            renderResult(binding.root, viewState)
        }

        return binding.root
    }

    private fun setupAdapter(binding: FragmentCoinListBinding) {
        coinAdapter = CoinAdapter(object : CoinAdapter.Listener {
            override fun onItemClick(coin: Coin) {
                onItemPressed(coin)
            }
        })
        binding.coinRecyclerView.adapter = coinAdapter
        binding.coinRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun renderResult(root: ViewGroup, state: CoinListViewModel.ViewStateCoinList) {
        val binding = PartResultBinding.bind(root)
        coinAdapter.submitList(state.coinList)
        binding.progressBar.visibility = if (state.isLoading) View.VISIBLE else View.GONE
        binding.errorContainer.visibility = if (state.showError) View.VISIBLE else View.GONE
        binding.tryAgainButton.setOnClickListener { viewModel.getAllCoins() }
    }

    private fun onItemPressed(coin: Coin) {
        val direction = CoinListFragmentDirections.actionCoinListFragmentToCoinInfoFragment(
            coin.id,
            coin.name)
        findNavController().navigate(direction)
    }

    override fun onDestroyView() {
        fragmentCoinListBinding = null
        super.onDestroyView()
    }
}

