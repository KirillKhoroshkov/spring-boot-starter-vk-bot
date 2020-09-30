package com.example.springbootstartervkbot.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class SendMessageResponse(@JsonProperty("response") val messageId: Long)