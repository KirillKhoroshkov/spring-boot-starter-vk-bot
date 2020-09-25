package com.example.springbootstartervkbot.data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ClientInfo(@JsonProperty("lang_id") val languageId: Int)