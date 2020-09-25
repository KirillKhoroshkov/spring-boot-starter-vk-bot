package com.example.springbootstartervkbot

import com.example.springbootstartervkbot.util.toURI
import org.junit.jupiter.api.Test
import org.springframework.web.util.UriComponentsBuilder
import org.junit.jupiter.api.Assertions.*
import java.net.URI

class UriComponentsBuilderToUriTests {

    @Test
    fun testBuilderWithPercentEncodedChars() {
        val uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("example.com")
                .pathSegment("method.%25%26")
                .queryParam("aa%28", "aa%22")
                .queryParam("bb%29", "bb%27")
                .toURI()
        assertEquals(URI("https://example.com/method.%25%26?aa%28=aa%22&bb%29=bb%27"), uri)
    }
}