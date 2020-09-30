package com.example.springbootstartervkbot.api

import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class ApiMethodParameters {

    private val parameters: MutableList<ApiMethodParameter> = mutableListOf()

    val encodedParams
        get() = parameters.toList()

    fun param(name: String, value: Any): ApiMethodParameters {
        val encodedName = URLEncoder.encode(name, StandardCharsets.UTF_8)
        val encodedValue = URLEncoder.encode(value.toString(), StandardCharsets.UTF_8)
        parameters.add(ApiMethodParameter(encodedName, encodedValue))
        return this
    }

    fun param(name: String, values: Iterable<Any>): ApiMethodParameters {
        val encodedName = URLEncoder.encode(name, StandardCharsets.UTF_8)
        val encodedValues = values.joinToString(
                separator = ",",
                transform = { URLEncoder.encode(it.toString(), StandardCharsets.UTF_8) }
        )
        parameters.add(ApiMethodParameter(encodedName, encodedValues))
        return this
    }
}