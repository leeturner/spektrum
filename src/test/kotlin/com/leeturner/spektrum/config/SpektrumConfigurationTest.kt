package com.leeturner.spektrum.config

import io.micronaut.context.ApplicationContext
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class SpektrumConfigurationTest {
    @Test
    fun `speed config can be loaded`() {
        val cpuSpeed = 1234
        val config = mapOf("spektrum.speed.cpu" to cpuSpeed)

        val ctx = ApplicationContext.run(config)
        val speedConfig = ctx.getBean(SpeedConfiguration::class.java)

        expectThat(speedConfig.cpu).isEqualTo(cpuSpeed)

        ctx.close()
    }
}
