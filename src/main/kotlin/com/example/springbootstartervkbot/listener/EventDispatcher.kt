package com.example.springbootstartervkbot.listener

import com.example.springbootstartervkbot.dto.Update

interface EventDispatcher {

    fun dispatch(update: Update)
}