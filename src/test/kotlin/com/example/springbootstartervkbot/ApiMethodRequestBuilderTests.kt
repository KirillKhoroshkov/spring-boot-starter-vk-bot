package com.example.springbootstartervkbot

import com.example.springbootstartervkbot.api.ApiMethodRequestBuilder
import com.example.springbootstartervkbot.api.impl.ApiMethodRequestBuilderImpl
import com.example.springbootstartervkbot.configuration.VkApiConfiguration
import com.example.springbootstartervkbot.properties.VkProperties
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import java.net.URI

@SpringBootTest(classes = [ApiMethodRequestBuilderTests.TestConfig::class])
class ApiMethodRequestBuilderTests {

    @Autowired
    private lateinit var apiMethodRequestBuilder: ApiMethodRequestBuilder

    @Configuration
    @Import(ApiMethodRequestBuilderImpl::class)
    class TestConfig {

        @Bean
        fun apiConfig(): VkApiConfiguration {
            val apiConfig = mock(VkApiConfiguration::class.java)
            given(apiConfig.version).willReturn("5.0122")
            return apiConfig
        }

        @Bean
        fun vkConfig(): VkProperties {
            val bot = VkProperties.Bot(accessToken = "aaa1bbb2ccc3")
            val vkConfig = mock(VkProperties::class.java)
            given(vkConfig.bot).willReturn(bot)
            return vkConfig
        }
    }

    @Test
    fun testBuildingUriWithoutQueryParam() {
        val builtUri = apiMethodRequestBuilder.buildUri("a  b")
        val expectedUri = URI("https://api.vk.com/method/a++b")
        assertEquals(expectedUri, builtUri)
    }

    @Test
    fun testBuildingOnlyOneQueryParam() {
        val builtParams = apiMethodRequestBuilder.buildParams {
            param("rus", "абвгд")
        }
        val expectedParams = "access_token=aaa1bbb2ccc3&" +
                "v=5.0122&" +
                "rus=%D0%B0%D0%B1%D0%B2%D0%B3%D0%B4"
        assertEquals(expectedParams, builtParams)
    }

    @Test
    fun testBuildingOnlyThreeQueryParams() {
        val builtParams = apiMethodRequestBuilder.buildParams {
            param("rus", "абвгд")
            param("!@#\$%^&*", "!@#\$%^&*")
            param("/*-+=", "/*-+=")
        }
        val expectedParams = "access_token=aaa1bbb2ccc3&" +
                "v=5.0122&" +
                "rus=%D0%B0%D0%B1%D0%B2%D0%B3%D0%B4&" +
                "%21%40%23%24%25%5E%26*=%21%40%23%24%25%5E%26*&" +
                "%2F*-%2B%3D=%2F*-%2B%3D"
        assertEquals(expectedParams, builtParams)
    }

    @Test
    fun testBuildingOnlyTwoQueryListParams() {
        val builtParams = apiMethodRequestBuilder.buildParams {
            param("!@#\$%^&*", listOf("!@#\$%^&*", "!@#\$%^&*"))
            param("/*-+=", listOf("/*-+=", "/*-+=", "/*-+="))
        }
        val expectedParams = "access_token=aaa1bbb2ccc3&" +
                "v=5.0122&" +
                "%21%40%23%24%25%5E%26*=%21%40%23%24%25%5E%26*,%21%40%23%24%25%5E%26*&" +
                "%2F*-%2B%3D=%2F*-%2B%3D,%2F*-%2B%3D,%2F*-%2B%3D"
        assertEquals(expectedParams, builtParams)
    }
}