package com.example.springbootstartervkbot.api

import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class ApiMethodParams {

    private val params: MutableList<ApiMethodParam> = mutableListOf()

    val encodedParams
        get() = params.toList()

    fun param(name: String, value: Any): ApiMethodParams {
        val encodedName = URLEncoder.encode(name, StandardCharsets.UTF_8)
        val encodedValue = URLEncoder.encode(value.toString(), StandardCharsets.UTF_8)
        params.add(ApiMethodParam(encodedName, encodedValue))
        return this
    }

    fun param(name: String, values: Iterable<Any>): ApiMethodParams {
        val encodedName = URLEncoder.encode(name, StandardCharsets.UTF_8)
        val encodedValues = values.joinToString(
                separator = ",",
                transform = { URLEncoder.encode(it.toString(), StandardCharsets.UTF_8) }
        )
        params.add(ApiMethodParam(encodedName, encodedValues))
        return this
    }
}