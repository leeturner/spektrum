package com.leeturner.spektrum.cpu

import com.leeturner.spektrum.cpu.FlagRegisterOptions.CARRY_BIT
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
        fun `test decode instruction when accumulator value set`() {
            registers.getGeneralPurposeRegisterSet().registerB = 3u
            registers.getAccumulatorAndFlagRegisterSet().accumulator = 4u

            cpu.decodeInstruction(OpCode.ADC_A_B)

            expectThat(registers.getAccumulatorAndFlagRegisterSet().accumulator).isEqualTo(7u)
        }

        @Test
        fun `test decode instruction accumulator value set with carry`() {
            registers.getAccumulatorAndFlagRegisterSet().flags.set(CARRY_BIT)
            registers.getGeneralPurposeRegisterSet().registerB = 3u
            registers.getAccumulatorAndFlagRegisterSet().accumulator = 4u

            cpu.decodeInstruction(OpCode.ADC_A_B)

            expectThat(registers.getAccumulatorAndFlagRegisterSet().accumulator).isEqualTo(8u)
        }
    }

    @Nested
    inner class AdcAC {
        @Test
        fun `test decode instruction accumulator 0`() {
            registers.getGeneralPurposeRegisterSet().registerC = 3u

            cpu.decodeInstruction(OpCode.ADC_A_C)

            expectThat(registers.getAccumulatorAndFlagRegisterSet().accumulator).isEqualTo(3u)
        }

        @Test
        fun `test decode instruction when accumulator value set`() {
            registers.getGeneralPurposeRegisterSet().registerC = 3u
            registers.getAccumulatorAndFlagRegisterSet().accumulator = 4u

            cpu.decodeInstruction(OpCode.ADC_A_C)

            expectThat(registers.getAccumulatorAndFlagRegisterSet().accumulator).isEqualTo(7u)
        }

        @Test
        fun `test decode instruction accumulator value set with carry`() {
            registers.getAccumulatorAndFlagRegisterSet().flags.set(CARRY_BIT)
            registers.getGeneralPurposeRegisterSet().registerC = 3u
            registers.getAccumulatorAndFlagRegisterSet().accumulator = 4u

            cpu.decodeInstruction(OpCode.ADC_A_C)

            expectThat(registers.getAccumulatorAndFlagRegisterSet().accumulator).isEqualTo(8u)
        }
    }

    @Nested
    inner class AdcAD {
        @Test
        fun `test decode instruction accumulator 0`() {
            registers.getGeneralPurposeRegisterSet().registerD = 3u

            cpu.decodeInstruction(OpCode.ADC_A_D)

            expectThat(registers.getAccumulatorAndFlagRegisterSet().accumulator).isEqualTo(3u)
        }

        @Test
        fun `test decode instruction when accumulator value set`() {
            registers.getGeneralPurposeRegisterSet().registerD = 3u
            registers.getAccumulatorAndFlagRegisterSet().accumulator = 4u

            cpu.decodeInstruction(OpCode.ADC_A_D)

            expectThat(registers.getAccumulatorAndFlagRegisterSet().accumulator).isEqualTo(7u)
        }

        @Test
        fun `test decode instruction accumulator value set with carry`() {
            registers.getAccumulatorAndFlagRegisterSet().flags.set(CARRY_BIT)
            registers.getGeneralPurposeRegisterSet().registerD = 3u
            registers.getAccumulatorAndFlagRegisterSet().accumulator = 4u

            cpu.decodeInstruction(OpCode.ADC_A_D)

            expectThat(registers.getAccumulatorAndFlagRegisterSet().accumulator).isEqualTo(8u)
        }
    }

    @Nested
    inner class AdcAE {
        @Test
        fun `test decode instruction accumulator 0`() {
            registers.getGeneralPurposeRegisterSet().registerE = 3u

            cpu.decodeInstruction(OpCode.ADC_A_E)

            expectThat(registers.getAccumulatorAndFlagRegisterSet().accumulator).isEqualTo(3u)
        }

        @Test
        fun `test decode instruction when accumulator value set`() {
            registers.getGeneralPurposeRegisterSet().registerE = 3u
            registers.getAccumulatorAndFlagRegisterSet().accumulator = 4u

            cpu.decodeInstruction(OpCode.ADC_A_E)

            expectThat(registers.getAccumulatorAndFlagRegisterSet().accumulator).isEqualTo(7u)
        }

        @Test
        fun `test decode instruction accumulator value set with carry`() {
            registers.getAccumulatorAndFlagRegisterSet().flags.set(CARRY_BIT)
            registers.getGeneralPurposeRegisterSet().registerE = 3u
            registers.getAccumulatorAndFlagRegisterSet().accumulator = 4u

            cpu.decodeInstruction(OpCode.ADC_A_E)

            expectThat(registers.getAccumulatorAndFlagRegisterSet().accumulator).isEqualTo(8u)
        }
    }

    @Nested
    inner class AdcAH {
        @Test
        fun `test decode instruction accumulator 0`() {
            registers.getGeneralPurposeRegisterSet().registerH = 3u

            cpu.decodeInstruction(OpCode.ADC_A_H)

            expectThat(registers.getAccumulatorAndFlagRegisterSet().accumulator).isEqualTo(3u)
        }

        @Test
        fun `test decode instruction when accumulator value set`() {
            registers.getGeneralPurposeRegisterSet().registerH = 3u
            registers.getAccumulatorAndFlagRegisterSet().accumulator = 4u

            cpu.decodeInstruction(OpCode.ADC_A_H)

            expectThat(registers.getAccumulatorAndFlagRegisterSet().accumulator).isEqualTo(7u)
        }

        @Test
        fun `test decode instruction accumulator value set with carry`() {
            registers.getAccumulatorAndFlagRegisterSet().flags.set(CARRY_BIT)
            registers.getGeneralPurposeRegisterSet().registerH = 3u
            registers.getAccumulatorAndFlagRegisterSet().accumulator = 4u

            cpu.decodeInstruction(OpCode.ADC_A_H)

            expectThat(registers.getAccumulatorAndFlagRegisterSet().accumulator).isEqualTo(8u)
        }
    }

    @Nested
    inner class AdcAL {
        @Test
        fun `test decode instruction accumulator 0`() {
            registers.getGeneralPurposeRegisterSet().registerL = 3u

            cpu.decodeInstruction(OpCode.ADC_A_L)

            expectThat(registers.getAccumulatorAndFlagRegisterSet().accumulator).isEqualTo(3u)
        }

        @Test
        fun `test decode instruction when accumulator value set`() {
            registers.getGeneralPurposeRegisterSet().registerL = 3u
            registers.getAccumulatorAndFlagRegisterSet().accumulator = 4u

            cpu.decodeInstruction(OpCode.ADC_A_L)

            expectThat(registers.getAccumulatorAndFlagRegisterSet().accumulator).isEqualTo(7u)
        }

        @Test
        fun `test decode instruction accumulator value set with carry`() {
            registers.getAccumulatorAndFlagRegisterSet().flags.set(CARRY_BIT)
            registers.getGeneralPurposeRegisterSet().registerL = 3u
            registers.getAccumulatorAndFlagRegisterSet().accumulator = 4u

            cpu.decodeInstruction(OpCode.ADC_A_L)

            expectThat(registers.getAccumulatorAndFlagRegisterSet().accumulator).isEqualTo(8u)
        }
    }
}
