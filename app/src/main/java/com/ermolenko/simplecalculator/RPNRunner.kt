package com.ermolenko.simplecalculator

import java.lang.NullPointerException
import java.util.*

class RPNRunner(private val valueProvider: ValueProvider? = null) {
    private val expressionConverter = Converter()

    /**
     * Calculate expression
     *
     *  @param expression Normal expression, which will be transformed to RPN
     * and will be calculated. Default value is
     *
     * @return Double In case the expression is an empty string, then the method will
     * return with null
     */
    fun calculate(expression: String): Double? {
        if (expression.trim().isEmpty())
            return null
        val rpnArray = expressionConverter.convert(expression)
        if (rpnArray.isEmpty())
            return null
        val stack = Stack<Double>()
        var hasNextOperation = false



        for (element in rpnArray.indices) {

            try {
                when (rpnArray[element]) {
                    "+" -> {
                        val res = this.getElementValue(stack, hasNextOperation)
                        stack.push(res!!.first + res.second!!)
                    }
                    "-" -> {
                        if (element != rpnArray.size - 1) {
                            if (rpnArray[element + 1] == "+" || rpnArray[element + 1] == "*" || rpnArray[element + 1] == "/" || rpnArray[element + 1] == "-")
                                hasNextOperation = true
                        }
                        val res = this.getElementValue(stack, hasNextOperation)
                        if (res?.second == null) {
                            stack.push(0 - res!!.first)
                            hasNextOperation = false
                        } else {
                            stack.push(res.second?.minus(res.first))
                        }
                    }
                    "*" -> {
                        val res = this.getElementValue(stack, hasNextOperation)
                        stack.push(res!!.first * res.second!!)
                    }
                    "/" -> {
                        val res = this.getElementValue(stack, hasNextOperation)
                        stack.push(res!!.second?.div(res.first))
                    }
                    else -> {
                        //if(element=="-")
                        stack.push(this.getDoubleValue(rpnArray[element]))
                    }
                }
            } catch (e: NullPointerException) {
                return null
            }

        }

        if (stack.size > 1) {
            return null
        }
        return stack.pop()
    }

    private fun getDoubleValue(element: String): Double {
        val value = doubleOrString(element)

        return if (value is Number) {
            value as Double
        } else {
            this.valueProvider?.getValue(value as String)!!
        }
    }

    private fun getElementValue(
        stack: Stack<Double>,
        hasNextOperation: Boolean
    ): Pair<Double, Double?>? {
        if (stack.isEmpty())
            return null
        val first: Double?
        val second: Double?

        var value = doubleOrString(stack.pop())
        first = if (value is Number) {
            value as Double
        } else {
            this.valueProvider?.getValue(value as String)
        }

        if (stack.isEmpty() || hasNextOperation)
            return Pair(first!!, null)

        value = doubleOrString(stack.pop())
        second = if (value is Number) {
            value as Double
        } else {
            this.valueProvider?.getValue(value as String)
        }

        return Pair(first!!, second!!)
    }

    private fun doubleOrString(element: Any) = try {
        element.toString().toDouble()
    } catch (e: NumberFormatException) {
        element
    }
}