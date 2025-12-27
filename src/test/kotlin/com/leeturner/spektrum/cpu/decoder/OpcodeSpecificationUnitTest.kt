package com.leeturner.spektrum.cpu.decoder

import com.leeturner.spektrum.cpu.decoder.model.CpuAddressingMode
import com.leeturner.spektrum.cpu.decoder.model.CpuOperation
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class OpcodeSpecificationUnitTest {

    @Test
    fun `adcABImpliedOpcode specification`() {
        assertOpcodeSpecification(
            OpcodeSpecification.ADC_A_B_IMPLIED,
            CpuOperation.ADC_A_B,
            CpuAddressingMode.IMPLIED,
            0x88u,
            1
        )
    }

    @Test
    fun `adcACImpliedOpcode specification`() {
        assertOpcodeSpecification(
            OpcodeSpecification.ADC_A_C_IMPLIED,
            CpuOperation.ADC_A_C,
            CpuAddressingMode.IMPLIED,
            0x89u,
            1
        )
    }

    @Test
    fun `adcADImpliedOpcode specification`() {
        assertOpcodeSpecification(
            OpcodeSpecification.ADC_A_D_IMPLIED,
            CpuOperation.ADC_A_D,
            CpuAddressingMode.IMPLIED,
            0x8Au,
            1
        )
    }

    @Test
    fun `adcAEImpliedOpcode specification`() {
        assertOpcodeSpecification(
            OpcodeSpecification.ADC_A_E_IMPLIED,
            CpuOperation.ADC_A_E,
            CpuAddressingMode.IMPLIED,
            0x8Bu,
            1
        )
    }

    @Test
    fun `adcAHImpliedOpcode specification`() {
        assertOpcodeSpecification(
            OpcodeSpecification.ADC_A_H_IMPLIED,
            CpuOperation.ADC_A_H,
            CpuAddressingMode.IMPLIED,
            0x8Cu,
            1
        )
    }

    @Test
    fun `adcALImpliedOpcode specification`() {
        assertOpcodeSpecification(
            OpcodeSpecification.ADC_A_L_IMPLIED,
            CpuOperation.ADC_A_L,
            CpuAddressingMode.IMPLIED,
            0x8Du,
            1
        )
    }

    private fun assertOpcodeSpecification(
        opcodeSpecification: OpcodeSpecification,
        cpuOperation: CpuOperation,
        cpuAddressingMode: CpuAddressingMode,
        hexCode: UByte,
        numberOfMCycles: Int
    ) {
        expectThat(opcodeSpecification.opcode.cpuOperation) isEqualTo cpuOperation
        expectThat(opcodeSpecification.opcode.cpuAddressingMode) isEqualTo cpuAddressingMode
        expectThat(opcodeSpecification.opcode.metaData.hex) isEqualTo hexCode
        expectThat(opcodeSpecification.opcode.metaData.numberOfMCycles) isEqualTo numberOfMCycles
    }
}