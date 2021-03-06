package com.example.springbootstartervkbot.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class UpdateList(@JsonProperty("ts") val nextEventNumber: Int,
                      @JsonProperty("updates") val updates: List<Update>)