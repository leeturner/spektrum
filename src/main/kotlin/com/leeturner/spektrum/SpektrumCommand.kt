package com.leeturner.spektrum

import com.leeturner.spektrum.config.SpeedConfiguration
import io.micronaut.configuration.picocli.PicocliRunner
import jakarta.inject.Inject
import picocli.CommandLine

@CommandLine.Command(
    name = "spektrum",
    description = ["..."],
    mixinStandardHelpOptions = true,
)
class SpektrumCommand : Runnable {
    @Inject
    lateinit var speedConfiguration: SpeedConfiguration

    @CommandLine.Option(names = ["-v", "--verbose"], description = ["..."])
    private var verbose: Boolean = false

    override fun run() {
        println("Config: ${speedConfiguration.cpu}")

        // business logic here
        if (verbose) {
            println("Hi!")
        }
    }

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            PicocliRunner.run(SpektrumCommand::class.java, *args)
        }
    }
}
