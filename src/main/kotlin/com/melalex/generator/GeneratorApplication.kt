package com.melalex.generator

import com.melalex.generator.services.impl.AtomicSequenceService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.time.Clock

@SpringBootApplication
class GeneratorApplication {

    @Bean
    fun clock() = Clock.systemDefaultZone()!!

    @Bean
    fun sequenceService() = AtomicSequenceService(clock().millis())
}

fun main(args: Array<String>) {
    runApplication<GeneratorApplication>(*args)
}
