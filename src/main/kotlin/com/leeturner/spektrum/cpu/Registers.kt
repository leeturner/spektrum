package com.leeturner.spektrum.cpu

/**
 * Based on the Zilog Z80 CPU User Manual available here:
 * https://www.zilog.com/docs/z80/um0080.pdf
 */
data class Registers(
    // Special purpose registers
    var programCounter: UShort = 0u,
    var stackPointer: UShort = 0u,
    var indexX: UShort = 0u,
    var indexY: UShort = 0u,
    var interruptPageAddress: UByte = 0u,
    var memoryRefresh: UByte = 0u,

    /**
     * The CPU can switch between two sets of these registers using exchange instructions.
     * There is a separate exchange instruction for the accumulatorAndFlag registers and the generalPurpose registers
     */
    private var accumulatorAndFlagRegisterSetIndex: Int = 0,
    private val accumulatorAndFlagRegisterSets: List<AccumulatorAndFlagRegisterSet> =
        listOf(AccumulatorAndFlagRegisterSet(), AccumulatorAndFlagRegisterSet()),

    private var generalPurposeRegisterSetIndex: Int = 0,
    private val generalPurposeRegisterSets: List<GeneralPurposeRegisterSet> =
        listOf(GeneralPurposeRegisterSet(), GeneralPurposeRegisterSet())
) {
    fun flipAccumulatorAndFlagRegisterSet(): AccumulatorAndFlagRegisterSet {
        accumulatorAndFlagRegisterSetIndex++
        accumulatorAndFlagRegisterSetIndex %= 2

        return accumulatorAndFlagRegisterSets[accumulatorAndFlagRegisterSetIndex]
    }

    fun flipGeneralPurposeRegisterSet(): GeneralPurposeRegisterSet {
        generalPurposeRegisterSetIndex++
        generalPurposeRegisterSetIndex %= 2

        return generalPurposeRegisterSets[generalPurposeRegisterSetIndex]
    }

    fun getAccumulatorAndFlagRegisterSet() : AccumulatorAndFlagRegisterSet {
        return accumulatorAndFlagRegisterSets[accumulatorAndFlagRegisterSetIndex]
    }

    fun getGeneralPurposeRegisterSet() : GeneralPurposeRegisterSet {
        return generalPurposeRegisterSets[generalPurposeRegisterSetIndex]
    }
}

/**
 * The Accumulator holds the results of 8-bit arithmetic or logical operations while the Flag Register indicates
 * specific conditions for 8-bit or 16-bit operations, such as indicating whether or not the result of an operation
 * is equal to 0
 */
data class AccumulatorAndFlagRegisterSet(var accumulator: UByte = 0u, var flags: UByte = 0u)

/**
 * 8 bit General purpose registers
 * Can be combined to form 16 bit registers in this configuration:
 * BC, DE, and HL
 */
data class GeneralPurposeRegisterSet(var registerB: UByte = 0u, var registerC: UByte = 0u, var registerD: UByte = 0u,
                                     var registerE: UByte = 0u, var registerH: UByte = 0u, var registerL: UByte = 0u)