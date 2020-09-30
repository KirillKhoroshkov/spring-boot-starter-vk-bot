package com.example.springbootstartervkbot.handler

import com.example.springbootstartervkbot.dto.NewMessageEvent

interface NewMessageHandler {

    fun handleNewMessage(newMessageEvent: NewMessageEvent)
}