package com.example.springbootstartervkbot.data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode

@JsonIgnoreProperties(ignoreUnknown = true)
data class Update(@JsonProperty("type") val type: String,
                  @JsonProperty("object") val jsonObject: JsonNode)