package com.example.springbootstartervkbot.handler

import com.example.springbootstartervkbot.api.ApiMethodRequestSender
import com.example.springbootstartervkbot.dto.NewMessageEvent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import kotlin.math.absoluteValue
import kotlin.random.Random

@Component
class NewMessageHandlerImpl : NewMessageHandler {

    @Autowired
    private lateinit var apiMethodRequestSender: ApiMethodRequestSender

    private val random = Random.Default

    override fun handleNewMessage(newMessageEvent: NewMessageEvent) {
        val message = newMessageEvent.message
        val randomId = random.nextLong().absoluteValue
        apiMethodRequestSender.send("messages.send", String::class.java) {
            param("peer_id", message.peerId)
            param("message", message.text)
            param("random_id", randomId)
        }
    }
}