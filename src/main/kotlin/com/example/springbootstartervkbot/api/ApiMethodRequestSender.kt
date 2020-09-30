package com.example.springbootstartervkbot.api

interface ApiMethodRequestSender {
    
    fun <T> send(methodName: String,
                 responseType: Class<T>,
                 parametersBlock: ApiMethodParameters.() -> ApiMethodParameters): T
}