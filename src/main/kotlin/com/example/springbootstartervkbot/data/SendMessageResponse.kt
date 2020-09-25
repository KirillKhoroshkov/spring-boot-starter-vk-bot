package com.example.springbootstartervkbot.data

import com.fasterxml.jackson.annotation.JsonProperty

data class SendMessageResponse(@JsonProperty("response") val messageId: Long)