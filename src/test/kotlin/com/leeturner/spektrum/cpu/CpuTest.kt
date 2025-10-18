package com.leeturner.spektrum.cpu

import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

@MicronautTest(rebuildContext = true)
class CpuTest(
    private val registers: Registers,
    private val cpu: Cpu,
) {
    @Nested
    inner class AdcAB {
        @Test
        fun `test decode instruction accumulator 0`() {
            registers.getGeneralPurposeRegisterSet().registerB = 3u

            cpu.decodeInstruction(OpCode.ADC_A_B)

            expectThat(registers.getAccumulatorAndFlagRegisterSet().accumulator).isEqualTo(3u)
        }

        @Test
        fun `test decode instruction accumulator value set - ADC A, `() {
            registers.getGeneralPurposeRegisterSet().registerB = 3u
            registers.getAccumulatorAndFlagRegisterSet().accumulator = 4u

            cpu.decodeInstruction(OpCode.ADC_A_B)

            expectThat(registers.getAccumulatorAndFlagRegisterSet().accumulator).isEqualTo(7u)
        }

        @Test
        fun `test decode instruction accumulator value set with carry`() {
            registers.getAccumulatorAndFlagRegisterSet().flags = 1u
            registers.getGeneralPurposeRegisterSet().registerB = 3u
            registers.getAccumulatorAndFlagRegisterSet().accumulator = 4u

            cpu.decodeInstruction(OpCode.ADC_A_B)

            expectThat(registers.getAccumulatorAndFlagRegisterSet().accumulator).isEqualTo(8u)
        }
    }
}
