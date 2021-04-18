package com.ermolenko.simplecalculator

class Converter {
    private val precedence = mapOf(
        "+" to 1,
        "-" to 1,
        "*" to 5,
        "/" to 5
    )

    /**
     * Converts an expression to RPN
     * @param expression String
     *
     *
     * @return Array<String> Array of variables and operators in RPN format
     */
    fun convert(expression: String): Array<String> {
        val stack = mutableListOf<String>()
        val output = mutableListOf<String>()

        val originalStringComponents = this.convert2StringComponents(expression)
        for (component in originalStringComponents) {

            if (component == "(") {
                stack.add(component)
            } else if (component == ")") {
                while (stack.isNotEmpty()) {
                    val last = stack.removeAt(stack.size - 1)
                    if (last != "(") {
                        output.add(last)
                        continue
                    }
                    break
                }
            }
            // if it is an operator
            else if (precedence.containsKey(component)) {
                if (stack.size != 0) {

                    for (i in stack.size - 1 downTo 0) {
                        if (!precedence.containsKey(stack[i]))
                            break
                        if (precedence[component] ?: error("") <= precedence[stack[i]] ?: error("")) {
                            output.add(stack[i])
                            stack.removeAt(i)
                            continue
                        }
                    }
                }
                stack.add(component)

            } else {
                output.add(component)
            }
        }
        // While there's operators on the stack, pop them to the queue
        if (stack.isNotEmpty()) {
            while (stack.isNotEmpty()) {
                val element = stack.removeAt(stack.size - 1)
                if (element == "(" || element == ")") {
                    return arrayOf("null")
                }
                output.add(element)
            }
        }

        return output.toTypedArray()
    }

    /**
     * Convert expression to array. This is needed to transform the expression to RPN
     * @param expression The String expression
     *
     * @return Array of Strings, which contains the operators and the variables/numbers
     */

    private fun convert2StringComponents(expression: String): Array<String> {
        val result = mutableListOf<String>()
        var prevIndex = 0
        for (index in expression.indices) {
            when (expression[index]) {
                '+', '-', '*', '/', '(', ')' -> {
                    if (expression.substring(prevIndex, index).trim().isNotEmpty())
                        result.add(expression.substring(prevIndex, index))
                    result.add(expression[index].toString())
                    prevIndex = index + 1
                }
            }
        }
        if (prevIndex != expression.length)
            result.add(expression.substring(prevIndex, expression.length))

        return result.toTypedArray()
    }
}