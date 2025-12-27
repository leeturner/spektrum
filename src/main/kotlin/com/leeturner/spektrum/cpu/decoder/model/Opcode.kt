package com.leeturner.spektrum.cpu.decoder.model

data class Opcode(
    val cpuOperation: CpuOperation,
    val cpuAddressingMode: CpuAddressingMode,
    val metaData: OpcodeMetaData,
)
