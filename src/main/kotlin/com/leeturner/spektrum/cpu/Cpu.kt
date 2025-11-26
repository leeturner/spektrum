package com.leeturner.spektrum.cpu

import com.leeturner.spektrum.cpu.FlagRegisterOptions.CARRY_BIT
import jakarta.inject.Singleton

enum class OpCode(
    val code: UByte,
) {
    ADC_A_B(0x88u.toUByte()),
    ADC_A_C(0x89u.toUByte()),
    ADC_A_D(0x8Au.toUByte()),
    ADC_A_E(0x8Bu.toUByte()),
    ADC_A_H(0x8Cu.toUByte()),
    ADC_A_L(0x8Du.toUByte()),
}

@Singleton
class Cpu(
    private val registers: Registers,
) {
    fun decodeInstruction(instruction: OpCode) {
        // TODO: implement carry, overflow, sign bits, 0 bits, half carry bits - page 30
        when (instruction) {
            OpCode.ADC_A_B -> {
                adcA(registers.getGeneralPurposeRegisterSet().registerB)
            }

            OpCode.ADC_A_C -> {
                adcA(registers.getGeneralPurposeRegisterSet().registerC)
            }

            OpCode.ADC_A_D -> {
                adcA(registers.getGeneralPurposeRegisterSet().registerD)
            }

            OpCode.ADC_A_E -> {
                adcA(registers.getGeneralPurposeRegisterSet().registerE)
            }

            OpCode.ADC_A_H -> {
                adcA(registers.getGeneralPurposeRegisterSet().registerH)
            }

            OpCode.ADC_A_L -> {
                adcA(registers.getGeneralPurposeRegisterSet().registerL)
            }
        }
    }

    fun adcA(register: UByte = 0u) {
        val accumulatorAndFlagRegisterSet = registers.getAccumulatorAndFlagRegisterSet()

        val carry = accumulatorAndFlagRegisterSet.flags.get(CARRY_BIT)
        val result = register + accumulatorAndFlagRegisterSet.accumulator + carry
        accumulatorAndFlagRegisterSet.accumulator = result.toUByte()
    }
}
