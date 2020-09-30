package com.example.springbootstartervkbot

import com.example.springbootstartervkbot.api.ApiMethodParameter
import com.example.springbootstartervkbot.api.ApiMethodParameters
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ApiMethodParametersTests {

    @Test
    fun testTwoParamsWithIllegalChars() {
        val firstParam = "full name"
        val firstValue = "Гжегож Бженчишчикевич"
        val secondParam = "email"
        val secondValue = "first'second+third@example.com"
        val encodedParams = ApiMethodParameters()
                .param(firstParam, firstValue)
                .param(secondParam, secondValue)
                .encodedParams
        val expectedParams = listOf(
                ApiMethodParameter("full+name", "%D0%93%D0%B6%D0%B5%D0%B3%D0%BE%D0%B6" +
                        "+%D0%91%D0%B6%D0%B5%D0%BD%D1%87%D0%B8%D1%88" +
                        "%D1%87%D0%B8%D0%BA%D0%B5%D0%B2%D0%B8%D1%87"),
                ApiMethodParameter("email", "first%27second%2Bthird%40example.com")
        )
        assertEquals(expectedParams, encodedParams)
    }

    @Test
    fun testTwoListParamsWithIllegalChars() {
        val firstParam = "First"
        val firstValue = listOf("!@#\$%^&*", "!@#\$%^&*", "!@#\$%^&*")
        val secondParam = "Second"
        val secondValue = listOf("/*-+=", "/*-+=", "/*-+=", "/*-+=")
        val thirdParam = "Third"
        val thirdValue = listOf("{}:\",./?", "{}:\",./?")
        val encodedParams = ApiMethodParameters()
                .param(firstParam, firstValue)
                .param(secondParam, secondValue)
                .param(thirdParam, thirdValue)
                .encodedParams
        val expectedParams = listOf(
                ApiMethodParameter("First", "%21%40%23%24%25%5E%26*,%21%40%23%24%25%5E%26*,%21%40%23%24%25%5E%26*"),
                ApiMethodParameter("Second", "%2F*-%2B%3D,%2F*-%2B%3D,%2F*-%2B%3D,%2F*-%2B%3D"),
                ApiMethodParameter("Third", "%7B%7D%3A%22%2C.%2F%3F,%7B%7D%3A%22%2C.%2F%3F")
        )
        assertEquals(expectedParams, encodedParams)
    }
}