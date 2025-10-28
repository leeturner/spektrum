package com.leeturner.spektrum.tmp

import io.micronaut.runtime.event.annotation.EventListener
import jakarta.inject.Singleton

sealed interface EmulatorEvent

data class TickEvent (val name: String): EmulatorEvent
data class TockEvent (val name: String): EmulatorEvent

@Singleton
class EventConsumer {
  @EventListener
  fun onEvent(event: TickEvent) {
    println("Received event: $event")
  }
  @EventListener
  fun onEvent(event: TockEvent) {
    println("Received event: $event")
  }
}
