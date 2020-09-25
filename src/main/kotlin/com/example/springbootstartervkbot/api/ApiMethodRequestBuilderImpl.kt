package com.example.springbootstartervkbot.api

import com.example.springbootstartervkbot.config.ApiConfig
import com.example.springbootstartervkbot.config.VkConfig
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
    private lateinit var apiConfig: ApiConfig

    @Autowired
    private lateinit var vkConfig: VkConfig

    /**
     * @return full URI
     * Use it for GET-request
     */
    override fun build(methodName: String, paramsBlock: ApiMethodParams.() -> ApiMethodParams): URI {
        return URI("${build(methodName)}?${build(paramsBlock)}")
    }

    /**
     * @return URI without query params
     * Use it for POST-request
     */
    override fun build(methodName: String): URI {
        return UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("api.vk.com")
                .pathSegment("method")
                .pathSegment(URLEncoder.encode(methodName, StandardCharsets.UTF_8))
                .toURI()
    }

    /**
     * @return only query params
     * Use it as parameters in POST-request
     * You may need to specify the following header in http request:
     * Content-Type: application/x-www-form-urlencoded
     */
    override fun build(paramsBlock: ApiMethodParams.() -> ApiMethodParams): String {
        val uriComponentsBuilder = UriComponentsBuilder.newInstance()
                .queryParam("access_token", vkConfig.bot.accessToken)
                .queryParam("v", apiConfig.version)
        val params = ApiMethodParams().paramsBlock()
        for (param in params.encodedParams) {
            uriComponentsBuilder.queryParam(param.name, param.value)
        }
        return uriComponentsBuilder.toURI().rawQuery
    }
}