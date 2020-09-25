package com.example.springbootstartervkbot.listener

import com.example.springbootstartervkbot.api.ApiMethodRequestSender
import com.example.springbootstartervkbot.config.VkConfig
import com.example.springbootstartervkbot.data.UpdateList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.ApplicationContext
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.util.UriTemplate
import java.lang.Exception
import javax.annotation.PostConstruct

@Component
class LongPollEventListener(restTemplateBuilder: RestTemplateBuilder) : Thread() {

    @Autowired
    private lateinit var vkConfig: VkConfig

    @Autowired
    private lateinit var apiMethodRequestSender: ApiMethodRequestSender

    @Autowired
    private lateinit var eventDispatcher: EventDispatcher

    @Autowired
    private lateinit var applicationContext: ApplicationContext

    /*TODO webclient*/
    private val restTemplate = restTemplateBuilder.build()

    private lateinit var uriTemplate: UriTemplate
    private var nextEventNumber = 0

    @PostConstruct
    private fun init() {
        val session = apiMethodRequestSender.getLongPollServer(vkConfig.group.id)
        uriTemplate = UriTemplate(
                "${session.serverAddress}?" +
                "act=a_check&" +
                "key=${session.secretKey}&" +
                "wait=25&" +
                "mode=2&" +
                "version=2&" +
                "ts={nextEventNumber}"
        )
        nextEventNumber = session.nextEventNumber
        start()
    }

    override fun run() {
        while (true) {
            try {
                val uri = uriTemplate.expand(nextEventNumber)
                val response = restTemplate.getForEntity(uri, UpdateList::class.java)
                if (response.statusCode == HttpStatus.OK) {
                    val update = response.body!!
                    for (event in update.updates) {
                        eventDispatcher.dispatch(event)
                    }
                    nextEventNumber = update.nextEventNumber
                }
            } catch (exception: Exception) {
                (applicationContext as ConfigurableApplicationContext).close()
                throw exception
            }
        }
    }
}