package com.example.springbootstartervkbot.api

import java.net.URI

interface ApiMethodRequestBuilder {

    /**
     * @return URI without query params
     */
    fun buildUri(methodName: String): URI

    /**
     * @return only query params
     * You may need to specify the following header in http request:
     * Content-Type: application/x-www-form-urlencoded
     */
    fun buildParams(parametersBlock: ApiMethodParameters.() -> ApiMethodParameters): String
}