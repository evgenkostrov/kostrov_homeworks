package com.epam.kostrov_homeworks

class Description(bmi: Double) {

    val description: String = when (bmi) {
        in 0.00..18.5 -> "Underweight"
        in 18.5..24.9 -> "Healthy Weight"
        in 24.9..29.9 -> "Overweight"
        in 29.9..Double.MAX_VALUE -> "Obesity"
        else -> ""
    }
}