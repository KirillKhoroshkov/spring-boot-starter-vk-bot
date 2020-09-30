package com.example.springbootstartervkbot

import com.example.springbootstartervkbot.properties.VkProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(VkProperties::class)
class VkBotSampleApplication

fun main(args: Array<String>) {
    runApplication<VkBotSampleApplication>(*args)
}