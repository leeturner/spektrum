package com.leeturner.spektrum.config

import io.micronaut.context.annotation.ConfigurationProperties

@ConfigurationProperties("spektrum.speed")
class SpeedConfiguration {
    var cpu: Int = 0
}
