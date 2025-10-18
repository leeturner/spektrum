package com.leeturner.spektrum.cpu

import jakarta.inject.Singleton

/**
 * Based on the Zilog Z80 CPU User Manual available here:
 * https://www.zilog.com/docs/z80/um0080.pdf
 */
@Singleton
class Registers {
    // Special purpose registers
    var programCounter: UShort = 0u
    var stackPointer: UShort = 0u
    var indexX: UShort = 0u
    var indexY: UShort = 0u
    var interruptPageAddress: UByte = 0u
    var memoryRefresh: UByte = 0u

    /**
     * The CPU can switch between two sets of these registers using exchange instructions.
     * There is a separate exchange instruction for the accumulatorAndFlag registers and the generalPurpose registers
     */
    private var accumulatorAndFlagRegisterSetIndex: Int = 0
    private val accumulatorAndFlagRegisterSets: List<AccumulatorAndFlagRegisterSet> =
        listOf(AccumulatorAndFlagRegisterSet(), AccumulatorAndFlagRegisterSet())

    private var generalPurposeRegisterSetIndex: Int = 0
    private val generalPurposeRegisterSets: List<GeneralPurposeRegisterSet> =
        listOf(GeneralPurposeRegisterSet(), GeneralPurposeRegisterSet())

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

    fun getAccumulatorAndFlagRegisterSet(): AccumulatorAndFlagRegisterSet =
        accumulatorAndFlagRegisterSets[accumulatorAndFlagRegisterSetIndex]

    fun getGeneralPurposeRegisterSet(): GeneralPurposeRegisterSet = generalPurposeRegisterSets[generalPurposeRegisterSetIndex]

    override fun toString(): String {
        return "Registers(programCounter=$programCounter, stackPointer=$stackPointer, indexX=$indexX, " +
                "indexY=$indexY, interruptPageAddress=$interruptPageAddress, memoryRefresh=$memoryRefresh, " +
                "accumulatorAndFlagRegisterSetIndex=$accumulatorAndFlagRegisterSetIndex, " +
                "accumulatorAndFlagRegisterSets=$accumulatorAndFlagRegisterSets, " +
                "generalPurposeRegisterSetIndex=$generalPurposeRegisterSetIndex, " +
                "generalPurposeRegisterSets=$generalPurposeRegisterSets)"
    }


}

/**
 * 8 bit General purpose registers
 * Can be combined to form 16 bit registers in this configuration:
 * BC, DE, and HL
 */
data class GeneralPurposeRegisterSet(
    var registerB: UByte = 0u,
    var registerC: UByte = 0u,
    var registerD: UByte = 0u,
    var registerE: UByte = 0u,
    var registerH: UByte = 0u,
    var registerL: UByte = 0u,
)

/**
 * The Accumulator holds the results of 8-bit arithmetic or logical operations while the Flag Register indicates
 * specific conditions for 8-bit or 16-bit operations, such as indicating whether or not the result of an operation
 * is equal to 0
 */
data class AccumulatorAndFlagRegisterSet(
    var accumulator: UByte = 0u,
    var flags: FlagRegister = FlagRegister()
)

/**
 *  * The flag (status) register is structured as follows:
 *  *
 *  * [ Sign, Zero, Unused, Half Carry, Unused, Overflow, Subtract, Carry ]
 *  *
 *  *  See page 174 of Rodney Zaks' book: http://www.z80.info/zip/zaks_book.pdf
 */
enum class FlagRegisterOptions(val bitPosition: UByte) {
    /*
     * Reflects the value of the most significant bit of a result or of a byte being transferred (bit seven).
     * In two's complement notation, the most significant bit is used to represent the sign.
     * "0" indicates a positive number and a "1" indicates a negative number.
     * As a result, bit seven is called the sign bit
     * Bit 1000 0000 in binary.
     */
    SIGN_BIT(0x80u),
    /*
     * Used to indicate whether the value of a byte which has been computed, or is being transferred, is zero.
     * It is also used with comparison instructions to indicate a match, and for other miscellaneous functions.
     * Bit 0100 0000 in binary.
     */
    ZERO_BIT(0x40u),
    /*
     * The half-carry flag indicates a possible carry from bit 3 into bit 4 during an arithmetic operation.
     * In other words, it represents the carry from the low-order nibble (group of 4 bits) into the high order one.
     * Bit 0001 0000 in binary.
     */
    HALF_CARRY_BIT(0x10u),
    /*
     * Specific instructions will set or reset this flag depending on the parity of the result; parity is determined
     * by counting the total number of ones in the result. If this number is odd, the parity bit will be set to
     * "0" (odd parity). If it is even, the parity bit will be set to "1" (even parity).
     *
     * It is also used as an overflow flag, and as part of block transfer instructions and two load instructions.
     * See page 176 of Rodney Zaks' book for details.
     * Bit 0000 0100 in binary.
     */
    PARITY_OVERFLOW_BIT(0x4u),
    /*
     * Used by the Z80 during BCD operations and is set to "0" after an addition and to "1" after a subtraction.
     * Bit 0000 0010 in binary.
     */
    SUBTRACT_BIT(0x2u),
    /*
     * Used to indicate whether an addition or subtraction operation has resulted in a carry (or borrow).
     * Also  used as a ninth bit in the case of shift and rotate operations.
     * Bit 0000 0001 in binary.
     */
    CARRY_BIT(0x1u);

    val inverseBitPosition: UByte = bitPosition.inv()
}

data class FlagRegister(var rawValue: UByte = 0u) {
    fun get(option: FlagRegisterOptions) = if ((rawValue and option.bitPosition) >= 1u.toUByte()) 1u else 0u
    fun set(option: FlagRegisterOptions) { rawValue = rawValue or option.bitPosition }
    fun clear(option: FlagRegisterOptions) { rawValue = rawValue and option.inverseBitPosition }
}