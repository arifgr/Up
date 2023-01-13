package com.example.up.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.up.common.Resource
import com.example.up.domain.model.Card
import com.example.up.domain.use_case.GetCards
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardsViewModel @Inject constructor(
    private val getCards: GetCards
) : ViewModel() {
    private var _cardsResponse = MutableLiveData<Resource<List<Card>>>()
    val cardsResponse: LiveData<Resource<List<Card>>> = _cardsResponse

    fun getCards() {
        viewModelScope.launch {
            getCards.invoke().collect {
                _cardsResponse.postValue(it)
            }
        }
    }
}