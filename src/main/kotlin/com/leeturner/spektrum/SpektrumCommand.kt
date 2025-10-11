package com.leeturner.spektrum

import com.leeturner.spektrum.config.SpektrumConfiguration
import io.micronaut.configuration.picocli.PicocliRunner
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
