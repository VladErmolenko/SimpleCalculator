package com.ermolenko.simplecalculator

interface ValueProvider {
    fun getValue(variableName: String): Double
}