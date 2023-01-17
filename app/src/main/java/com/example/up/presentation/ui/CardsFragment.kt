package com.example.up.presentation.ui

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.up.R
import com.example.up.common.Resource
import com.example.up.databinding.FragmentCardsBinding
import com.example.up.domain.model.Card
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class CardsFragment : Fragment(R.layout.fragment_cards) {

    private val cardsViewModel: CardsViewModel by viewModels()

    private var _binding: FragmentCardsBinding? = null
    private val binding get() = _binding!!

    var cardsList = listOf<Card>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.viewPagerCards) {
            adapter = CardsViewPagerAdapter(listOf())
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                    writeBalances(cardsList, position)
                }

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                }

                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                }
            })

        }
        var time = 0
        GlobalScope.launch(Dispatchers.Main) {
            while (true) {
                binding.textViewTime.text = resources.getString(R.string.time, time)
                time++
                delay(1000)
            }

        }
        binding.imageButtonRefresh.setOnClickListener {
            cardsViewModel.getCards()
        }
        cardsViewModel.getCards()
        cardsViewModel.cardsResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                    binding.viewPagerCards.adapter =
                        it.data?.let { it1 -> CardsViewPagerAdapter(it1) }
                    cardsList = it.data!!
                    writeBalances(cardsList, 0)
                    binding.indicator.setViewPager(binding.viewPagerCards)
                    time = 0
                    binding.textViewTime.visibility = View.VISIBLE
                }

                is Resource.Error -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }

                else -> {
                    Toast.makeText(context, "An unknown error occurred", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun writeBalances(cardList: List<Card>, position: Int) {
        val balance = cardsList[position].balance?.value
        val pendingBalance = cardsList[position].pendingBalance?.value
        val formattedBalance = balance!!.subSequence(0, balance.length - 2)
            .toString() + "." + balance!!.subSequence(
            balance.length - 2,
            balance.length
        ).toString()
        val formattedPendingBalance =
            pendingBalance!!.subSequence(0, pendingBalance.length - 2)
                .toString() + "." + pendingBalance!!.subSequence(
                pendingBalance.length - 2,
                pendingBalance.length
            ).toString()
        binding.textViewBalance.text = formattedBalance
        binding.textViewPendingBalance.text = formattedPendingBalance
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCardsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}