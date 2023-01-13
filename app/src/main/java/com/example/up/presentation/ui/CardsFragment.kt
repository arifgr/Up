package com.example.up.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.up.R
import com.example.up.common.Resource
import com.example.up.databinding.FragmentCardsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CardsFragment : Fragment(R.layout.fragment_cards) {

    private val cardsViewModel: CardsViewModel by viewModels()

    private var _binding: FragmentCardsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cardsViewModel.getCards()

        cardsViewModel.cardsResponse.observe(this){
            when(it){
                is Resource.Loading -> {
                    Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    binding.textViewCard.text = it.data?.get(0)?.image
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
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