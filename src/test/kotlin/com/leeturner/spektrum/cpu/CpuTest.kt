package com.leeturner.spektrum.cpu

import com.leeturner.spektrum.cpu.FlagRegisterOptions.CARRY_BIT
import com.leeturner.spektrum.cpu.decoder.OpcodeSpecification
import com.leeturner.spektrum.exception.UnrecognisedOpcodeException
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.isEqualTo

@MicronautTest(rebuildContext = true)
class CpuTest(
    private val registers: Registers,
    private val cpu: Cpu,
) {
    @Nested
    inner class AdcAB {
        val opcodeHex = OpcodeSpecification.ADC_A_B_IMPLIED.opcode.metaData.hex

        @Test
        fun `test decode instruction accumulator 0`() {
            registers.getGeneralPurposeRegisterSet().registerB = 3u

            cpu.decodeInstruction(opcodeHex)

            expectThat(registers.getAccumulatorAndFlagRegisterSet().accumulator).isEqualTo(3u)
        }

        @Test
        fun `test decode instruction when accumulator value set`() {
            registers.getGeneralPurposeRegisterSet().registerB = 3u
            registers.getAccumulatorAndFlagRegisterSet().accumulator = 4u

            cpu.decodeInstruction(opcodeHex)

            expectThat(registers.getAccumulatorAndFlagRegisterSet().accumulator).isEqualTo(7u)
        }

        @Test
        fun `test decode instruction accumulator value set with carry`() {
            registers.getAccumulatorAndFlagRegisterSet().flags.set(CARRY_BIT)
            registers.getGeneralPurposeRegisterSet().registerB = 3u
            registers.getAccumulatorAndFlagRegisterSet().accumulator = 4u

            cpu.decodeInstruction(opcodeHex)

            expectThat(registers.getAccumulatorAndFlagRegisterSet().accumulator).isEqualTo(8u)
        }
    }

    @Nested
    inner class AdcAC {
        val opcodeHex = OpcodeSpecification.ADC_A_C_IMPLIED.opcode.metaData.hex

        @Test
        fun `test decode instruction accumulator 0`() {
            registers.getGeneralPurposeRegisterSet().registerC = 3u

            cpu.decodeInstruction(opcodeHex)

            expectThat(registers.getAccumulatorAndFlagRegisterSet().accumulator).isEqualTo(3u)
        }

        @Test
        fun `test decode instruction when accumulator value set`() {
            registers.getGeneralPurposeRegisterSet().registerC = 3u
            registers.getAccumulatorAndFlagRegisterSet().accumulator = 4u

            cpu.decodeInstruction(opcodeHex)

            expectThat(registers.getAccumulatorAndFlagRegisterSet().accumulator).isEqualTo(7u)
        }

        @Test
        fun `test decode instruction accumulator value set with carry`() {
            registers.getAccumulatorAndFlagRegisterSet().flags.set(CARRY_BIT)
            registers.getGeneralPurposeRegisterSet().registerC = 3u
            registers.getAccumulatorAndFlagRegisterSet().accumulator = 4u

            cpu.decodeInstruction(opcodeHex)

            expectThat(registers.getAccumulatorAndFlagRegisterSet().accumulator).isEqualTo(8u)
        }
    }

    @Nested
    inner class AdcAD {
        val opcodeHex = OpcodeSpecification.ADC_A_D_IMPLIED.opcode.metaData.hex

        @Test
        fun `test decode instruction accumulator 0`() {
            registers.getGeneralPurposeRegisterSet().registerD = 3u

            cpu.decodeInstruction(opcodeHex)

            expectThat(registers.getAccumulatorAndFlagRegisterSet().accumulator).isEqualTo(3u)
        }

        @Test
        fun `test decode instruction when accumulator value set`() {
            registers.getGeneralPurposeRegisterSet().registerD = 3u
            registers.getAccumulatorAndFlagRegisterSet().accumulator = 4u

            cpu.decodeInstruction(opcodeHex)

            expectThat(registers.getAccumulatorAndFlagRegisterSet().accumulator).isEqualTo(7u)
        }

        @Test
        fun `test decode instruction accumulator value set with carry`() {
            registers.getAccumulatorAndFlagRegisterSet().flags.set(CARRY_BIT)
            registers.getGeneralPurposeRegisterSet().registerD = 3u
            registers.getAccumulatorAndFlagRegisterSet().accumulator = 4u

            cpu.decodeInstruction(opcodeHex)

            expectThat(registers.getAccumulatorAndFlagRegisterSet().accumulator).isEqualTo(8u)
        }
    }

    @Nested
    inner class AdcAE {
        val opcodeHex = OpcodeSpecification.ADC_A_E_IMPLIED.opcode.metaData.hex

        @Test
        fun `test decode instruction accumulator 0`() {
            registers.getGeneralPurposeRegisterSet().registerE = 3u

            cpu.decodeInstruction(opcodeHex)

            expectThat(registers.getAccumulatorAndFlagRegisterSet().accumulator).isEqualTo(3u)
        }

        @Test
        fun `test decode instruction when accumulator value set`() {
            registers.getGeneralPurposeRegisterSet().registerE = 3u
            registers.getAccumulatorAndFlagRegisterSet().accumulator = 4u

            cpu.decodeInstruction(opcodeHex)

            expectThat(registers.getAccumulatorAndFlagRegisterSet().accumulator).isEqualTo(7u)
        }

        @Test
        fun `test decode instruction accumulator value set with carry`() {
            registers.getAccumulatorAndFlagRegisterSet().flags.set(CARRY_BIT)
            registers.getGeneralPurposeRegisterSet().registerE = 3u
            registers.getAccumulatorAndFlagRegisterSet().accumulator = 4u

            cpu.decodeInstruction(opcodeHex)

            expectThat(registers.getAccumulatorAndFlagRegisterSet().accumulator).isEqualTo(8u)
        }
    }

    @Nested
    inner class AdcAH {
        val opcodeHex = OpcodeSpecification.ADC_A_H_IMPLIED.opcode.metaData.hex

        @Test
        fun `test decode instruction accumulator 0`() {
            registers.getGeneralPurposeRegisterSet().registerH = 3u

            cpu.decodeInstruction(opcodeHex)

            expectThat(registers.getAccumulatorAndFlagRegisterSet().accumulator).isEqualTo(3u)
        }

        @Test
        fun `test decode instruction when accumulator value set`() {
            registers.getGeneralPurposeRegisterSet().registerH = 3u
            registers.getAccumulatorAndFlagRegisterSet().accumulator = 4u

            cpu.decodeInstruction(opcodeHex)

            expectThat(registers.getAccumulatorAndFlagRegisterSet().accumulator).isEqualTo(7u)
        }

        @Test
        fun `test decode instruction accumulator value set with carry`() {
            registers.getAccumulatorAndFlagRegisterSet().flags.set(CARRY_BIT)
            registers.getGeneralPurposeRegisterSet().registerH = 3u
            registers.getAccumulatorAndFlagRegisterSet().accumulator = 4u

            cpu.decodeInstruction(opcodeHex)

            expectThat(registers.getAccumulatorAndFlagRegisterSet().accumulator).isEqualTo(8u)
        }
    }

    @Nested
    inner class AdcAL {
        val opcodeHex = OpcodeSpecification.ADC_A_L_IMPLIED.opcode.metaData.hex

        @Test
        fun `test decode instruction accumulator 0`() {
            registers.getGeneralPurposeRegisterSet().registerL = 3u

            cpu.decodeInstruction(opcodeHex)

            expectThat(registers.getAccumulatorAndFlagRegisterSet().accumulator).isEqualTo(3u)
        }

        @Test
        fun `test decode instruction when accumulator value set`() {
            registers.getGeneralPurposeRegisterSet().registerL = 3u
            registers.getAccumulatorAndFlagRegisterSet().accumulator = 4u

            cpu.decodeInstruction(opcodeHex)

            expectThat(registers.getAccumulatorAndFlagRegisterSet().accumulator).isEqualTo(7u)
        }

        @Test
        fun `test decode instruction accumulator value set with carry`() {
            registers.getAccumulatorAndFlagRegisterSet().flags.set(CARRY_BIT)
            registers.getGeneralPurposeRegisterSet().registerL = 3u
            registers.getAccumulatorAndFlagRegisterSet().accumulator = 4u

            cpu.decodeInstruction(opcodeHex)

            expectThat(registers.getAccumulatorAndFlagRegisterSet().accumulator).isEqualTo(8u)
        }
    }

    @Nested
    inner class Misc {
        @Test
        fun `test unrecognised instruction`() {
            expectThrows<UnrecognisedOpcodeException> {
                cpu.decodeInstruction(0x99u)
            }
        }
    }
}
