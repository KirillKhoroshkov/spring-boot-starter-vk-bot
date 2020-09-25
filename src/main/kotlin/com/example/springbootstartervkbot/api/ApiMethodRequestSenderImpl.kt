package com.example.springbootstartervkbot.api

import com.example.springbootstartervkbot.data.SendMessageResponse
import com.example.springbootstartervkbot.data.SessionData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Component

@Component
class ApiMethodRequestSenderImpl(restTemplateBuilder: RestTemplateBuilder) : ApiMethodRequestSender {

    @Autowired
    private lateinit var apiMethodRequestBuilder: ApiMethodRequestBuilder

    private val restTemplate = restTemplateBuilder.build()

    override fun sendTextMessageToPeer(peerId: Long, message: String, forwardMessageIds: Set<Long>, randomId: Long): Long {
        val uri = apiMethodRequestBuilder.build("messages.send") {
            param("peer_id", peerId)
            param("random_id", randomId)
            if (forwardMessageIds.isNotEmpty()) {
                param("forward_messages", forwardMessageIds)
            }
            param("message", message)
        }
        val response = restTemplate.getForEntity(uri, SendMessageResponse::class.java)
        return response.body!!.messageId
    }

    override fun getLongPollServer(groupId: Long): SessionData {
        val uri = apiMethodRequestBuilder.build("groups.getLongPollServer") {
            param("group_id", groupId)
        }
        val response = restTemplate.getForEntity(uri, SessionData::class.java)
        return response.body!!
    }
}