package com.example.springbootstartervkbot

import com.example.springbootstartervkbot.api.ApiMethodRequestBuilder
import com.example.springbootstartervkbot.api.ApiMethodRequestBuilderImpl
import com.example.springbootstartervkbot.config.ApiConfig
import com.example.springbootstartervkbot.config.VkConfig
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
        fun apiConfig(): ApiConfig {
            val apiConfig = mock(ApiConfig::class.java)
            given(apiConfig.version).willReturn("5.0122")
            return apiConfig
        }

        @Bean
        fun vkConfig(): VkConfig {
            val bot = VkConfig.Bot(accessToken = "aaa1bbb2ccc3")
            val vkConfig = mock(VkConfig::class.java)
            given(vkConfig.bot).willReturn(bot)
            return vkConfig
        }
    }

    @Test
    fun testBuildingFullUriWithOneQueryParam() {
        val builtUri = apiMethodRequestBuilder.build("a  b") {
            param("!@#\$%^&*", "!@#\$%^&*")
        }
        val expectedUri = URI("https://api.vk.com/method/a++b?" +
                "access_token=aaa1bbb2ccc3&" +
                "v=5.0122&" +
                "%21%40%23%24%25%5E%26*=%21%40%23%24%25%5E%26*")
        assertEquals(expectedUri, builtUri)
    }

    @Test
    fun testBuildingFullUriWithThreeQueryParams() {
        val builtUri = apiMethodRequestBuilder.build("a  b") {
            param("!@#\$%^&*", "!@#\$%^&*")
            param("/*-+=", "/*-+=")
            param("{}:\",./?", "{}:\",./?")
        }
        val expectedUri = URI("https://api.vk.com/method/a++b?" +
                "access_token=aaa1bbb2ccc3&" +
                "v=5.0122&" +
                "%21%40%23%24%25%5E%26*=%21%40%23%24%25%5E%26*&" +
                "%2F*-%2B%3D=%2F*-%2B%3D&" +
                "%7B%7D%3A%22%2C.%2F%3F=%7B%7D%3A%22%2C.%2F%3F")
        assertEquals(expectedUri, builtUri)
    }

    @Test
    fun testBuildingFullUriWithTwoQueryListParams() {
        val builtUri = apiMethodRequestBuilder.build("a  b") {
            param("!@#\$%^&*", listOf("!@#\$%^&*", "!@#\$%^&*"))
            param("/*-+=", listOf("/*-+=", "/*-+=", "/*-+="))
        }
        val expectedUri = URI("https://api.vk.com/method/a++b?" +
                "access_token=aaa1bbb2ccc3&" +
                "v=5.0122&" +
                "%21%40%23%24%25%5E%26*=%21%40%23%24%25%5E%26*,%21%40%23%24%25%5E%26*&" +
                "%2F*-%2B%3D=%2F*-%2B%3D,%2F*-%2B%3D,%2F*-%2B%3D")
        assertEquals(expectedUri, builtUri)
    }

    @Test
    fun testBuildingUriWithoutQueryParam() {
        val builtUri = apiMethodRequestBuilder.build("a  b")
        val expectedUri = URI("https://api.vk.com/method/a++b")
        assertEquals(expectedUri, builtUri)
    }

    @Test
    fun testBuildingOnlyOneQueryParam() {
        val builtParams = apiMethodRequestBuilder.build {
            param("rus", "абвгд")
        }
        val expectedParams = "access_token=aaa1bbb2ccc3&" +
                "v=5.0122&" +
                "rus=%D0%B0%D0%B1%D0%B2%D0%B3%D0%B4"
        assertEquals(expectedParams, builtParams)
    }

    @Test
    fun testBuildingOnlyThreeQueryParams() {
        val builtParams = apiMethodRequestBuilder.build {
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
        val builtParams = apiMethodRequestBuilder.build {
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