package com.leeturner.spektrum

import com.leeturner.spektrum.config.SpektrumConfiguration
import com.leeturner.spektrum.tmp.TickEvent
import io.micronaut.configuration.picocli.PicocliRunner
import io.micronaut.context.event.ApplicationEventPublisher
import io.micronaut.core.io.ResourceLoader
import jakarta.inject.Inject
import picocli.CommandLine
import java.util.concurrent.Callable
import kotlin.system.exitProcess

@CommandLine.Command(
    name = "spektrum",
    description = ["..."],
    mixinStandardHelpOptions = true,
)
class SpektrumCommand(
    @Inject private val spektrumConfiguration: SpektrumConfiguration,
    @Inject private val resourceLoader: ResourceLoader,
) : Callable<Int> {
    @CommandLine.Option(names = ["-v", "--verbose"], description = ["..."])
    private var verbose: Boolean = false

  @Inject
  lateinit var eventPublisher: ApplicationEventPublisher<TickEvent>

    override fun call(): Int {
        // Load and print banner
        resourceLoader.getResourceAsStream("classpath:banner.txt").ifPresent { inputStream ->
            inputStream.bufferedReader().use { reader ->
                println(reader.readText())
            }
        }
        // business logic here
        if (verbose) {
            println("${spektrumConfiguration.name} v${spektrumConfiguration.version}")
        }

      repeat(100) {
        eventPublisher.publishEvent(TickEvent("TickEvent"))
      }

        // success
        return 0
    }

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            val exitCode = PicocliRunner.call(SpektrumCommand::class.java, *args)
            exitProcess(exitCode ?: 0)
        }
    }
}
