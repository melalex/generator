package com.melalex.generator.controller

import com.melalex.generator.dto.SequenceDto
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.annotation.Bean
import org.springframework.test.context.junit4.SpringRunner
import java.time.Clock
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

const val CURRENT_TIME = 333L

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SequenceControllerTest {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Autowired
    lateinit var clock: Clock

    @Test
    fun `should return unique value`() {
        val first = testRestTemplate.getForEntity("/v1/sequence/next", SequenceDto::class.java).body
        val second = testRestTemplate.getForEntity("/v1/sequence/next", SequenceDto::class.java).body

        Assert.assertEquals(expected(1), first)
        Assert.assertEquals(expected(2), second)
    }

    private fun expected(offset: Long) = SequenceDto(LocalDateTime.now(clock), CURRENT_TIME + offset)

    @TestConfiguration
    class SequenceControllerTestConfiguration {

        @Bean
        fun clock() = Clock.fixed(Instant.ofEpochMilli(CURRENT_TIME), ZoneId.systemDefault())!!
    }
}