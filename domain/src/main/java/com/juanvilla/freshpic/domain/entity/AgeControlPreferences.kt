package com.juanvilla.freshpic.domain.entity

data class AgeControlPreferences(
    val isAdult: Boolean?,
    val selectedRating: String?
) {

    fun arePreferencesValid(): Boolean {
        return isAdult == false || selectedRating == null
    }

}