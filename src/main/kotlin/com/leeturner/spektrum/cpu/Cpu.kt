package com.leeturner.spektrum.cpu

import com.leeturner.spektrum.cpu.FlagRegisterOptions.CARRY_BIT
import com.leeturner.spektrum.cpu.decoder.OpcodeSpecification
import com.leeturner.spektrum.cpu.decoder.OpcodeSpecification.ADC_A_B_IMPLIED
import com.leeturner.spektrum.cpu.decoder.OpcodeSpecification.ADC_A_C_IMPLIED
import com.leeturner.spektrum.cpu.decoder.OpcodeSpecification.ADC_A_D_IMPLIED
import com.leeturner.spektrum.cpu.decoder.OpcodeSpecification.ADC_A_E_IMPLIED
import com.leeturner.spektrum.cpu.decoder.OpcodeSpecification.ADC_A_H_IMPLIED
import com.leeturner.spektrum.cpu.decoder.OpcodeSpecification.ADC_A_L_IMPLIED
import jakarta.inject.Singleton

@Singleton
class Cpu(
    private val registers: Registers,
) {
    fun decodeInstruction(instruction: UByte) {
        when (OpcodeSpecification.from(instruction)) {
            // TODO: implement carry, overflow, sign bits, 0 bits, half carry bits - page 30
            ADC_A_B_IMPLIED -> {
                adcA(registers.getGeneralPurposeRegisterSet().registerB)
            }
            ADC_A_C_IMPLIED -> {
                adcA(registers.getGeneralPurposeRegisterSet().registerC)
            }
            ADC_A_D_IMPLIED -> {
                adcA(registers.getGeneralPurposeRegisterSet().registerD)
            }
            ADC_A_E_IMPLIED -> {
                adcA(registers.getGeneralPurposeRegisterSet().registerE)
            }
            ADC_A_H_IMPLIED -> {
                adcA(registers.getGeneralPurposeRegisterSet().registerH)
            }
            ADC_A_L_IMPLIED -> {
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
