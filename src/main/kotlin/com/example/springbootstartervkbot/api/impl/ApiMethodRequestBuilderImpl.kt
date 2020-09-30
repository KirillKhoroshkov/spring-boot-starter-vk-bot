package com.example.springbootstartervkbot.api.impl

import com.example.springbootstartervkbot.api.ApiMethodParameters
import com.example.springbootstartervkbot.api.ApiMethodRequestBuilder
import com.example.springbootstartervkbot.configuration.VkApiConfiguration
import com.example.springbootstartervkbot.properties.VkProperties
import com.example.springbootstartervkbot.util.toURI
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Component
class ApiMethodRequestBuilderImpl : ApiMethodRequestBuilder {

    @Autowired
    private lateinit var vkApiConfiguration: VkApiConfiguration

    @Autowired
    private lateinit var vkProperties: VkProperties

    /**
     * @return URI without query params
     */
    override fun buildUri(methodName: String): URI {
        return UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("api.vk.com")
                .pathSegment("method")
                .pathSegment(URLEncoder.encode(methodName, StandardCharsets.UTF_8))
                .toURI()
    }

    /**
     * @return only query params
     * You may need to specify the following header in http request:
     * Content-Type: application/x-www-form-urlencoded
     */
    override fun buildParams(parametersBlock: ApiMethodParameters.() -> ApiMethodParameters): String {
        val uriComponentsBuilder = UriComponentsBuilder.newInstance()
                .queryParam("access_token", vkProperties.bot.accessToken)
                .queryParam("v", vkApiConfiguration.version)
        val params = ApiMethodParameters().parametersBlock()
        for (param in params.encodedParams) {
            uriComponentsBuilder.queryParam(param.name, param.value)
        }
        return uriComponentsBuilder.toURI().rawQuery
    }
}