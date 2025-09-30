package com.leeturner.spektrum

import io.micronaut.configuration.picocli.PicocliRunner
import picocli.CommandLine

@CommandLine.Command(name = "spektrum", description = ["..."],
        mixinStandardHelpOptions = true)
class SpektrumCommand : Runnable {

    @CommandLine.Option(names = ["-v", "--verbose"], description = ["..."])
    private var verbose : Boolean = false

    override fun run() {
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
