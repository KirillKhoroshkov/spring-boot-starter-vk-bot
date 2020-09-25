package com.example.springbootstartervkbot.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.validation.annotation.Validated
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Positive

@Validated
@ConstructorBinding
@ConfigurationProperties(prefix = "vk")
data class VkConfig(@Valid val group: Group, @Valid val bot: Bot) {

    data class Group(@field:Positive val id: Long)

    data class Bot(@field:NotBlank val accessToken: String)
}