package com.melalex.generator.controller

import com.melalex.generator.dto.SequenceDto
import com.melalex.generator.services.SequenceService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Clock
import java.time.LocalDateTime

@RestController
@RequestMapping("/v1/sequence")
class SequenceController(private val sequenceService: SequenceService, private val clock: Clock) {

    @GetMapping("/next")
    fun getNext(): SequenceDto = SequenceDto(LocalDateTime.now(clock), sequenceService.getNext())
}