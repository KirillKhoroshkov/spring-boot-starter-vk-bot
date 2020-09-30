package com.example.springbootstartervkbot.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class PrivateMessage(@JsonProperty("id") val id: Long,
                          @JsonProperty("peer_id") val peerId: Long,
                          @JsonProperty("from_id") val fromId: Long,
                          @JsonProperty("text") val text: String)