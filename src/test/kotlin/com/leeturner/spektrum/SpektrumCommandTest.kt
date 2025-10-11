package com.leeturner.spektrum

import com.leeturner.spektrum.config.SpektrumConfiguration
import io.micronaut.configuration.picocli.PicocliRunner
import io.micronaut.context.ApplicationContext
import io.micronaut.context.env.Environment
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.contains
import strikt.assertions.isEqualTo
import java.io.ByteArrayOutputStream
import java.io.PrintStream

@MicronautTest
class SpektrumCommandTest {
    @Inject
    private lateinit var spektrumConfiguration: SpektrumConfiguration

    @Test
    fun testWithCommandLineOption() {
        ApplicationContext.run(Environment.CLI, Environment.TEST).use { ctx ->
            ByteArrayOutputStream().use { baos ->
                System.setOut(PrintStream(baos))

                val args = arrayOf("-v")
                val exitCode = PicocliRunner.call(SpektrumCommand::class.java, ctx, *args)

                expectThat(exitCode).isEqualTo(0)
                expectThat(baos.toString()).contains("${spektrumConfiguration.name} v${spektrumConfiguration.version}")
            }
        }
    }
}
