package ru.dubr.traineetestandroid.presentation.coininfo

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import ru.dubr.traineetestandroid.R
import ru.dubr.traineetestandroid.databinding.FragmentCoinInfoBinding
import ru.dubr.traineetestandroid.databinding.PartResultBinding
import ru.dubr.traineetestandroid.domain.CoinInfo

@AndroidEntryPoint
class CoinInfoFragment : Fragment() {

    private var fragmentCoinInfoBinding: FragmentCoinInfoBinding? = null

    private val args by navArgs<CoinInfoFragmentArgs>()
    private val viewModel by viewModels<CoinInfoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = FragmentCoinInfoBinding.inflate(inflater, container, false)
        fragmentCoinInfoBinding = binding

//        binding.toolBar.setNavigationOnClickListener { findNavController().navigateUp() }
//        binding.toolBar.setNavigationIcon(R.drawable.bitcoin)

        viewModel.state.observe(viewLifecycleOwner) {
            val coinInfo = it.coinInfo
            renderResult(binding.root, it)

            if (coinInfo != null) {
                setupUi(binding, coinInfo)
            }
        }

        return binding.root
    }

    private fun setupUi(binding: FragmentCoinInfoBinding, coinInfo: CoinInfo) {
        binding.apply {
            description.text = Html.fromHtml(coinInfo.description, Html.FROM_HTML_MODE_COMPACT)
            categories.text = coinInfo.categories.joinToString(", ")
            Glide.with(this@CoinInfoFragment)
                .load(coinInfo.image.large)
                .circleCrop()
                .into(iconImageView)
        }
    }

    private fun renderResult(root: ViewGroup, state: CoinInfoViewModel.ViewStateCoinInfo) {
        val partResultBinding = PartResultBinding.bind(root)

        partResultBinding.apply {
            progressBar.visibility = if (state.isLoading) View.VISIBLE else View.GONE
            errorContainer.visibility = if (state.showError) View.VISIBLE else View.GONE
            tryAgainButton.setOnClickListener { viewModel.getCoinInfo() }
        }

        fragmentCoinInfoBinding?.labelsGroup?.visibility =
            if (state.showInfoContainer) View.VISIBLE else View.GONE
    }

    override fun onDestroy() {
        fragmentCoinInfoBinding = null
        super.onDestroy()
    }
}