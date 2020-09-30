package com.example.springbootstartervkbot.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class NewMessageEvent(@JsonProperty("message") val message: PrivateMessage,
                           @JsonProperty("client_info") val clientInfo: ClientInfo)