package com.bignerdranch.android.geoquiz

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private const val TAG = "CheatViewModel"
const val CHEAT_STATE_KEY = "CHEAT_STATE_KEY"

class CheatViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    var isAnswerShown: Boolean
        get() = savedStateHandle.get(CHEAT_STATE_KEY) ?: false
        set(value) = savedStateHandle.set(CHEAT_STATE_KEY, value)
}

