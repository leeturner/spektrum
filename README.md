# Spektrum

[![Build](https://github.com/leeturner/spektrum/actions/workflows/build.yml/badge.svg)](https://github.com/leeturner/spektrum/actions/workflows/build.yml)
[![Licence](https://img.shields.io/github/license/leeturner/spektrum)](LICENSE)

A Sinclair ZX Spectrum emulator written in Kotlin and Micronaut. The goal is to faithfully recreate the classic home
computer, starting from its heart: the Zilog Z80 CPU.

## Status

Spektrum is at a very early stage, and there's nothing to see on screen yet. So far it has:

- The Z80 register set: the main and alternate register sets, flags, index registers, stack pointer and program
  counter
- An opcode decoder, built from a specification of each instruction's addressing mode and cycle count
- The first instructions: `ADC A,r` for each of the general purpose registers
- A command line entry point and a setting for the emulation speed

Next up is the rest of the Z80 instruction set, then memory, the ULA and the display.

## Requirements

- Java 25 or later

## Usage

Build and run it with Gradle:

```shell
./gradlew build
./gradlew run --args="--verbose"
```

For now this prints the banner and version. Most of the work happens in the tests, which run with `./gradlew test`.

The emulation speed is set in `src/main/resources/application.properties`:

```properties
spektrum.speed.cpu=100
```

## Built with

- [Kotlin](https://kotlinlang.org/) and [Micronaut](https://micronaut.io/)
- [Picocli](https://picocli.info/) for the command line
- Gradle, with [Detekt](https://detekt.dev/) and [Kotlinter](https://github.com/jeremymailen/kotlinter-gradle)

## Useful links

- [Online Z80 IDE](https://clrhome.org/asm/)
- [Another online Z80 IDE](https://ide.retrogamecoders.com/?platform=zx&file=hello.asm)
- [Z80 CPU User Manual](https://www.zilog.com/docs/z80/um0080.pdf)
- [Programming the Z80, third edition, by Rodnay Zaks](http://www.z80.info/zip/zaks_book.pdf)
- [How to Write a Computer Emulator, by Marat Fayzullin](https://fms.komkon.org/EMUL8/HOWTO.html)

## Acknowledgements

Spektrum is a tribute to Sir Clive Sinclair and the ZX Spectrum, which introduced a generation to computing.

## Licence

Apache 2.0. See [LICENSE](LICENSE).
