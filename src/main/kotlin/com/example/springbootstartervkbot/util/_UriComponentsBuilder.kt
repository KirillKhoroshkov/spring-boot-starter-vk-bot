package com.example.springbootstartervkbot.util

import org.springframework.web.util.UriComponentsBuilder
import java.net.URI

/** For already encoded UriComponentsBuilder */
fun UriComponentsBuilder.toURI(): URI {
    return URI(this.build(false).toUriString())
}