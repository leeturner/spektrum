package com.leeturner.spektrum.config

import io.micronaut.context.annotation.ConfigurationProperties

@ConfigurationProperties("spektrum")
class SpektrumConfiguration {
    var name: String = ""
    var version: String = ""
}
