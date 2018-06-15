package com.melalex.generator.services.impl

import com.melalex.generator.services.SequenceService
import java.util.concurrent.atomic.AtomicLong

class AtomicSequenceService(initValue: Long = 0) : SequenceService {

    private val counter: AtomicLong = AtomicLong(initValue)

    override fun getNext(): Long = counter.incrementAndGet()
}