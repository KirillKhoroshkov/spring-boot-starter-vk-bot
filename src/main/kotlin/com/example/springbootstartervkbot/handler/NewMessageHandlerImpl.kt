package com.example.springbootstartervkbot.handler

import com.example.springbootstartervkbot.api.ApiMethodRequestSender
import com.example.springbootstartervkbot.data.NewMessageEvent
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
        apiMethodRequestSender.sendTextMessageToPeer(
                message.peerId,
                message.text,
                setOf(message.id),
                randomId
        )
    }
}