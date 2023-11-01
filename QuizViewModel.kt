package com.bignerdranch.android.geoquiz

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"
const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"
const val IS_CHEATER_KEY = "IS_CHEATER_KEY"

class QuizViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true),
        Question(R.string.question_sharks_mammals, false),
        Question(R.string.question_sea_otters_rock, true),
        Question(R.string.question_blue_whale, true),
        Question(R.string.question_sloth_digestion, true),
        Question(R.string.question_octopus_hearts, true),
        Question(R.string.question_greenland_largest_island, true),
        Question(R.string.question_infants_bones, true),
        Question(R.string.question_human_eye_colors, true),
        Question(R.string.question_new_york_islands, true),
        Question(R.string.question_south_africa_capitals, false),
        Question(R.string.question_atlantic_ocean, false),
        Question(R.string.question_mount_everest, true),
        Question(R.string.question_great_wall_of_china, true),
        Question(R.string.question_longest_rivers, false),
        Question(R.string.question_chunnel_length, true),
        Question(R.string.question_greenland_largest_island, true),
        Question(R.string.question_countries_in_south_america, false),
        Question(R.string.question_alaska_volcanoes, true),
        Question(R.string.question_china_coastline, false),
        Question(R.string.question_vatican_city_smallest_country, true)
    )

    private val cheatedQuestions = MutableList(questionBank.size) { false }
    private val answeredQuestions = BooleanArray(questionBank.size) { false }
    private var correctAnswers = 0

    var isCheater: Boolean
        get() = savedStateHandle.get(IS_CHEATER_KEY) ?: false
        set(value) = savedStateHandle.set(IS_CHEATER_KEY, value)

    var currentIndex: Int
        get() = savedStateHandle.get(CURRENT_INDEX_KEY) ?: 0
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun moveToPrevious() {
        currentIndex = (currentIndex - 1 + questionBank.size) % questionBank.size
    }

    fun isQuestionAnswered(index: Int): Boolean {
        return answeredQuestions[index]
    }

    fun setQuestionAnswered(index: Int) {
        answeredQuestions[index] = true
    }

    fun incrementCorrectAnswers() {
        correctAnswers++
    }

    fun getQuizScore(): Int {
        return (correctAnswers.toDouble() / questionBank.size.toDouble() * 100).toInt()
    }

    fun isAllQuestionsAnswered(): Boolean {
        return answeredQuestions.all { it }
    }

    fun isQuestionCheated(index: Int): Boolean {
        return cheatedQuestions[index]
    }

    fun setQuestionCheated(index: Int, cheated: Boolean) {
        cheatedQuestions[index] = cheated
    }
}