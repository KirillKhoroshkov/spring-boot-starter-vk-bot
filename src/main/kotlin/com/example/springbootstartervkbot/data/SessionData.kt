package com.example.springbootstartervkbot.data

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonTypeName

@JsonTypeName(value = "response")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
data class SessionData(@JsonProperty("key") val secretKey: String,
                       @JsonProperty("server") val serverAddress: String,
                       @JsonProperty("ts") val nextEventNumber: Int)