package com.leeturner.spektrum.config

import io.micronaut.context.ApplicationContext
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class SpektrumConfigurationTest {
    @Test
    fun `generic application config can be loaded`() {
        val name = "spektrum"
        val version = "0.1"
        val config = mapOf("spektrum.name" to name, "spektrum.version" to version)

        ApplicationContext.run(config).use { ctx ->
            val spektrumConfig = ctx.getBean(SpektrumConfiguration::class.java)

            expectThat(spektrumConfig.name).isEqualTo(name)
            expectThat(spektrumConfig.version).isEqualTo(version)
        }
    }

    @Test
    fun `speed config can be loaded`() {
        val cpuSpeed = 1234
        val config = mapOf("spektrum.speed.cpu" to cpuSpeed)

        ApplicationContext.run(config).use { ctx ->
            val speedConfig = ctx.getBean(SpeedConfiguration::class.java)

            expectThat(speedConfig.cpu).isEqualTo(cpuSpeed)
        }
    }
}
