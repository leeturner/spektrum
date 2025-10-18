package com.leeturner.spektrum.cpu

import jakarta.inject.Singleton

enum class OpCode(
    val code: UByte,
) {
    ADC_A_B(0x88u.toUByte()),
}

fun carryBit(value: UByte): UByte = (value and 0x1u)

@Singleton
class Cpu(
    private val registers: Registers,
) {
    fun decodeInstruction(instruction: OpCode) {
        when (instruction) {
            OpCode.ADC_A_B -> {
                val accumulatorAndFlagRegisterSet = registers.getAccumulatorAndFlagRegisterSet()
                val generalPurposeRegisterSet = registers.getGeneralPurposeRegisterSet()

                val carry = carryBit(accumulatorAndFlagRegisterSet.flags)
                val result = generalPurposeRegisterSet.registerB + accumulatorAndFlagRegisterSet.accumulator + carry
                accumulatorAndFlagRegisterSet.accumulator = result.toUByte()
            }
//      0x89u.toUByte() -> {
//        // ADC A, C
//      }
//      0x8Au.toUByte() -> {
//        // ADC A, D
//      }
//      0x8Bu.toUByte() -> {
//        // ADC A, E
//      }
//      0x8Cu.toUByte() -> {
//        // ADC A, H
//      }
//      0x8Du.toUByte() -> {
//        // ADC A, L
//      }
        }
    }
}
