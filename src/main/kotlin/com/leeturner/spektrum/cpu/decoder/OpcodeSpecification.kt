package com.leeturner.spektrum.cpu.decoder

import com.leeturner.spektrum.cpu.decoder.model.CpuAddressingMode
import com.leeturner.spektrum.cpu.decoder.model.CpuOperation
import com.leeturner.spektrum.cpu.decoder.model.Opcode
import com.leeturner.spektrum.cpu.decoder.model.OpcodeMetaData
import com.leeturner.spektrum.exception.UnrecognisedOpcodeException

enum class OpcodeSpecification(val opcode: Opcode) {
    ADC_A_B_IMPLIED(
        opcode {
            cpuOperation = CpuOperation.ADC_A_B
            cpuAddressingMode = CpuAddressingMode.IMPLIED
            metaData {
                hex = 0x88u
                numberOfCycles = 1
            }
        }
    ),
    ADC_A_C_IMPLIED(
        opcode {
            cpuOperation = CpuOperation.ADC_A_C
            cpuAddressingMode = CpuAddressingMode.IMPLIED
            metaData {
                hex = 0x89u
                numberOfCycles = 1
            }
        }
    ),
    ADC_A_D_IMPLIED(
        opcode {
            cpuOperation = CpuOperation.ADC_A_D
            cpuAddressingMode = CpuAddressingMode.IMPLIED
            metaData {
                hex = 0x8Au
                numberOfCycles = 1
            }
        }
    ),
    ADC_A_E_IMPLIED(
        opcode {
            cpuOperation = CpuOperation.ADC_A_E
            cpuAddressingMode = CpuAddressingMode.IMPLIED
            metaData {
                hex = 0x8Bu
                numberOfCycles = 1
            }
        }
    ),
    ADC_A_H_IMPLIED(
        opcode {
            cpuOperation = CpuOperation.ADC_A_H
            cpuAddressingMode = CpuAddressingMode.IMPLIED
            metaData {
                hex = 0x8Cu
                numberOfCycles = 1
            }
        }
    ),
    ADC_A_L_IMPLIED(
        opcode {
            cpuOperation = CpuOperation.ADC_A_L
            cpuAddressingMode = CpuAddressingMode.IMPLIED
            metaData {
                hex = 0x8Du
                numberOfCycles = 1
            }
        }
    );

    companion object {
        private val map = entries.associateBy { it.opcode.metaData.hex }

        fun from(instruction: UByte): OpcodeSpecification {
            return map[instruction]
                ?: throw UnrecognisedOpcodeException("Unable to decode opcode: ${instruction.toHex()}")
        }
    }
}

private fun opcode(opcodeBuilder: OpcodeBuilder.() -> Unit): Opcode =
    OpcodeBuilder().apply(opcodeBuilder).build()

private class OpcodeBuilder {
    var cpuOperation: CpuOperation? = null
    var cpuAddressingMode: CpuAddressingMode? = null
    private var metadata: OpcodeMetaData? = null

    fun metaData(metaData: OpcodeMetaDataBuilder.() -> Unit) {
        metadata = OpcodeMetaDataBuilder().apply(metaData).build()
    }

    fun build(): Opcode { // We know these field will always be non-null
        return Opcode(cpuOperation!!, cpuAddressingMode!!, metadata!!)
    }
}

private class OpcodeMetaDataBuilder {
    var hex: UByte? = null
    var numberOfCycles: Int? = null

    fun build(): OpcodeMetaData =
        OpcodeMetaData(
            // We know these fields will always be non-null
            hex!!,
            numberOfCycles!!,
        )
}

private fun UByte.toHex() = this.toHexString(hexFormat)

private val hexFormat = HexFormat {
    this.upperCase = true
    this.number {
        prefix = "0x"
        removeLeadingZeros = false
        minLength = 2
    }
}