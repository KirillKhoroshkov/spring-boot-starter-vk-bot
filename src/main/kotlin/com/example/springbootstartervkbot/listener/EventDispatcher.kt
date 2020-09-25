package com.example.springbootstartervkbot.listener

import com.example.springbootstartervkbot.data.Update

interface EventDispatcher {

    fun dispatch(update: Update)
}