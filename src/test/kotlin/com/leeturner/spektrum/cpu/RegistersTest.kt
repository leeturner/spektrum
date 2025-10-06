package com.leeturner.spektrum.cpu

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class RegistersTest {
    @Test
    fun initialState() {
        val registers = Registers()

        expectThat(registers) {
            get { programCounter } isEqualTo 0u
            get { stackPointer } isEqualTo 0u
            get { indexX } isEqualTo 0u
            get { indexY } isEqualTo 0u
            get { interruptPageAddress } isEqualTo 0u
            get { memoryRefresh } isEqualTo 0u
        }

        expectThat(registers.getGeneralPurposeRegisterSet()) {
            get { registerB } isEqualTo 0u
            get { registerC } isEqualTo 0u
            get { registerD } isEqualTo 0u
            get { registerE } isEqualTo 0u
            get { registerH } isEqualTo 0u
            get { registerL } isEqualTo 0u
        }

        expectThat(registers.getAccumulatorAndFlagRegisterSet()) {
            get { accumulator } isEqualTo 0u
            get { flags } isEqualTo 0u
        }

        registers.flipAccumulatorAndFlagRegisterSet()
        registers.flipGeneralPurposeRegisterSet()

        expectThat(registers.getGeneralPurposeRegisterSet()) {
            get { registerB } isEqualTo 0u
            get { registerC } isEqualTo 0u
            get { registerD } isEqualTo 0u
            get { registerE } isEqualTo 0u
            get { registerH } isEqualTo 0u
            get { registerL } isEqualTo 0u
        }

        expectThat(registers.getAccumulatorAndFlagRegisterSet()) {
            get { accumulator } isEqualTo 0u
            get { flags } isEqualTo 0u
        }
    }

    @Test
    fun flipAccumulatorAndFlagRegisters() {
        val registers = Registers()

        // Initially no change from default zeroes
        expectCurrentAccumulatorAndFlagRegisterStates(registers.getAccumulatorAndFlagRegisterSet(), 0u, 0u)

        // Change in values should be reflected when the getter is called
        var accumulatorAndFlagRegisterSet = registers.getAccumulatorAndFlagRegisterSet()
        accumulatorAndFlagRegisterSet.accumulator = 1u
        accumulatorAndFlagRegisterSet.flags = 2u

        expectCurrentAccumulatorAndFlagRegisterStates(registers.getAccumulatorAndFlagRegisterSet(), 1u, 2u)

        registers.flipAccumulatorAndFlagRegisterSet()

        // Now we should have flipped to the other set, which haven't been changed from zeroes.
        expectCurrentAccumulatorAndFlagRegisterStates(registers.getAccumulatorAndFlagRegisterSet(), 0u, 0u)

        // Change this second set too and test it
        accumulatorAndFlagRegisterSet = registers.getAccumulatorAndFlagRegisterSet()
        accumulatorAndFlagRegisterSet.accumulator = 3u
        accumulatorAndFlagRegisterSet.flags = 4u

        expectCurrentAccumulatorAndFlagRegisterStates(registers.getAccumulatorAndFlagRegisterSet(), 3u, 4u)

        // Now flip back and forth to check all is working
        registers.flipAccumulatorAndFlagRegisterSet()
        expectCurrentAccumulatorAndFlagRegisterStates(registers.getAccumulatorAndFlagRegisterSet(), 1u, 2u)

        registers.flipAccumulatorAndFlagRegisterSet()
        expectCurrentAccumulatorAndFlagRegisterStates(registers.getAccumulatorAndFlagRegisterSet(), 3u, 4u)

        registers.flipAccumulatorAndFlagRegisterSet()
        expectCurrentAccumulatorAndFlagRegisterStates(registers.getAccumulatorAndFlagRegisterSet(), 1u, 2u)

        registers.flipAccumulatorAndFlagRegisterSet()
        expectCurrentAccumulatorAndFlagRegisterStates(registers.getAccumulatorAndFlagRegisterSet(), 3u, 4u)

        // Now we check that the return value of the flip method works in the same way
        expectCurrentAccumulatorAndFlagRegisterStates(registers.flipAccumulatorAndFlagRegisterSet(), 1u, 2u)
        expectCurrentAccumulatorAndFlagRegisterStates(registers.flipAccumulatorAndFlagRegisterSet(), 3u, 4u)
        expectCurrentAccumulatorAndFlagRegisterStates(registers.flipAccumulatorAndFlagRegisterSet(), 1u, 2u)
        expectCurrentAccumulatorAndFlagRegisterStates(registers.flipAccumulatorAndFlagRegisterSet(), 3u, 4u)
    }

    @Test
    fun flipGeneralPurposeRegisters() {
        val registers = Registers()

        // Initially no change from default zeroes
        expectGeneralPurposeRegisterStates(registers.getGeneralPurposeRegisterSet(), 0u, 0u, 0u, 0u, 0u, 0u)

        // Change in values should be reflected when the getter is called
        var generalPurposeRegisterSet = registers.getGeneralPurposeRegisterSet()
        generalPurposeRegisterSet.registerB = 1u
        generalPurposeRegisterSet.registerC = 2u
        generalPurposeRegisterSet.registerD = 3u
        generalPurposeRegisterSet.registerE = 4u
        generalPurposeRegisterSet.registerH = 5u
        generalPurposeRegisterSet.registerL = 6u

        expectGeneralPurposeRegisterStates(registers.getGeneralPurposeRegisterSet(), 1u, 2u, 3u, 4u, 5u, 6u)

        registers.flipGeneralPurposeRegisterSet()

        // Now we should have flipped to the other set, which haven't been changed from zeroes.
        expectGeneralPurposeRegisterStates(registers.getGeneralPurposeRegisterSet(), 0u, 0u, 0u, 0u, 0u, 0u)

        // Change this second set too and test it
        generalPurposeRegisterSet = registers.getGeneralPurposeRegisterSet()
        generalPurposeRegisterSet.registerB = 7u
        generalPurposeRegisterSet.registerC = 8u
        generalPurposeRegisterSet.registerD = 9u
        generalPurposeRegisterSet.registerE = 10u
        generalPurposeRegisterSet.registerH = 11u
        generalPurposeRegisterSet.registerL = 12u

        expectGeneralPurposeRegisterStates(registers.getGeneralPurposeRegisterSet(), 7u, 8u, 9u, 10u, 11u, 12u)

        // Now flip back and forth to check all is working
        registers.flipGeneralPurposeRegisterSet()
        expectGeneralPurposeRegisterStates(registers.getGeneralPurposeRegisterSet(), 1u, 2u, 3u, 4u, 5u, 6u)

        registers.flipGeneralPurposeRegisterSet()
        expectGeneralPurposeRegisterStates(registers.getGeneralPurposeRegisterSet(), 7u, 8u, 9u, 10u, 11u, 12u)

        registers.flipGeneralPurposeRegisterSet()
        expectGeneralPurposeRegisterStates(registers.getGeneralPurposeRegisterSet(), 1u, 2u, 3u, 4u, 5u, 6u)

        registers.flipGeneralPurposeRegisterSet()
        expectGeneralPurposeRegisterStates(registers.getGeneralPurposeRegisterSet(), 7u, 8u, 9u, 10u, 11u, 12u)

        // Now we check that the return value of the flip method works in the same way
        expectGeneralPurposeRegisterStates(registers.flipGeneralPurposeRegisterSet(), 1u, 2u, 3u, 4u, 5u, 6u)
        expectGeneralPurposeRegisterStates(registers.flipGeneralPurposeRegisterSet(), 7u, 8u, 9u, 10u, 11u, 12u)
        expectGeneralPurposeRegisterStates(registers.flipGeneralPurposeRegisterSet(), 1u, 2u, 3u, 4u, 5u, 6u)
        expectGeneralPurposeRegisterStates(registers.flipGeneralPurposeRegisterSet(), 7u, 8u, 9u, 10u, 11u, 12u)
    }

    private fun expectCurrentAccumulatorAndFlagRegisterStates(
        registerSet: AccumulatorAndFlagRegisterSet,
        expectedAccumulatorState: UByte,
        expectedFlagsState: UByte,
    ) {
        expectThat(registerSet) {
            get { accumulator } isEqualTo expectedAccumulatorState
            get { flags } isEqualTo expectedFlagsState
        }
    }

    @Suppress("LongParameterList")
    private fun expectGeneralPurposeRegisterStates(
        registerSet: GeneralPurposeRegisterSet,
        expectedBState: UByte,
        expectedCState: UByte,
        expectedDState: UByte,
        expectedEState: UByte,
        expectedHState: UByte,
        expectedLState: UByte,
    ) {
        expectThat(registerSet) {
            get { registerB } isEqualTo expectedBState
            get { registerC } isEqualTo expectedCState
            get { registerD } isEqualTo expectedDState
            get { registerE } isEqualTo expectedEState
            get { registerH } isEqualTo expectedHState
            get { registerL } isEqualTo expectedLState
        }
    }
}
