package com.example.springbootstartervkbot.listener.impl

import com.example.springbootstartervkbot.dto.NewMessageEvent
import com.example.springbootstartervkbot.dto.Update
import com.example.springbootstartervkbot.handler.NewMessageHandler
import com.example.springbootstartervkbot.listener.EventDispatcher
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