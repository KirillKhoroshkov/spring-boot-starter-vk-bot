package com.example.springbootstartervkbot.api

import java.net.URI

interface ApiMethodRequestBuilder {

    /**
     * @return full URI
     * Use it for GET-request
     */
    fun build(methodName: String, paramsBlock: ApiMethodParams.() -> ApiMethodParams): URI

    /**
     * @return URI without query params
     * Use it for POST-request
     */
    fun build(methodName: String): URI

    /**
     * @return only query params
     * Use it as parameters in POST-request
     * You may need to specify the following header in http request:
     * Content-Type: application/x-www-form-urlencoded
     */
    fun build(paramsBlock: ApiMethodParams.() -> ApiMethodParams): String
}