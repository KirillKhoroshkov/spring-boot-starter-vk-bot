package com.example.springbootstartervkbot.api

import com.example.springbootstartervkbot.data.SessionData

interface ApiMethodRequestSender {
    
    fun sendTextMessageToPeer(peerId: Long, message: String, forwardMessageIds: Set<Long>, randomId: Long): Long
    
    fun getLongPollServer(groupId: Long): SessionData
}