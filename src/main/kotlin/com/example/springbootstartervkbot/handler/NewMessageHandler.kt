package com.example.springbootstartervkbot.handler

import com.example.springbootstartervkbot.data.NewMessageEvent

interface NewMessageHandler {

    fun handleNewMessage(newMessageEvent: NewMessageEvent)
}