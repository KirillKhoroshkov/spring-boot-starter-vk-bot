package com.example.springbootstartervkbot.listener

import com.example.springbootstartervkbot.data.NewMessageEvent
import com.example.springbootstartervkbot.data.Update
import com.example.springbootstartervkbot.handler.NewMessageHandler
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class EventDispatcherImpl : EventDispatcher {

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var newMessageHandler: NewMessageHandler

    override fun dispatch(update: Update) {
        when (update.type) {
            "message_new" -> {
                val message = objectMapper.treeToValue(update.jsonObject, NewMessageEvent::class.java)
                newMessageHandler.handleNewMessage(message)
            }
        }
    }
}