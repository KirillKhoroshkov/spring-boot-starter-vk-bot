package com.example.springbootstartervkbot.api.impl

import com.example.springbootstartervkbot.api.ApiMethodParameters
import com.example.springbootstartervkbot.api.ApiMethodRequestBuilder
import com.example.springbootstartervkbot.api.ApiMethodRequestSender
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component

@Component
class ApiMethodRequestSenderImpl(restTemplateBuilder: RestTemplateBuilder) : ApiMethodRequestSender {

    @Autowired
    private lateinit var apiMethodRequestBuilder: ApiMethodRequestBuilder

    private val restTemplate = restTemplateBuilder.build()

    override fun <T> send(methodName: String,
                          responseType: Class<T>,
                          parametersBlock: ApiMethodParameters.() -> ApiMethodParameters): T {
        val uri = apiMethodRequestBuilder.buildUri(methodName)
        val params = apiMethodRequestBuilder.buildParams(parametersBlock)
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_FORM_URLENCODED
        val request = HttpEntity(params, headers)
        val response = restTemplate.postForObject(uri, request, responseType)
        return response!!
    }

    /*fun getLongPollServer(groupId: Long): SessionData {
        val uri = apiMethodRequestBuilder.buildParams("groups.getLongPollServer") {
            param("group_id", groupId)
        }
        val response = restTemplate.getForEntity(uri, SessionData::class.java)
        return response.body!!
    }*/
}