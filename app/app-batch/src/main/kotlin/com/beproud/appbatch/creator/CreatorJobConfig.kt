package com.beproud.appbatch.creator

import mu.KotlinLogging
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

private val logger = KotlinLogging.logger {}

@Configuration
class CreatorJobConfig(
    val jobBuilderFactory: JobBuilderFactory,
    val stepBuilderFactory: StepBuilderFactory,
    val stepTasklet: StepTasklet,
) {
    private val chunkSize = 10

    @Bean
    fun readCreatorJob(): Job {
        return jobBuilderFactory.get("readerCreator")
            .incrementer(RunIdIncrementer())
            .start(step1()).next(step2()).build()
    }


    @Bean
    fun step1(): Step = stepBuilderFactory.get("step1").tasklet { contribution, chunkContext ->
        logger.info { "step1" }
        logger.debug { "step1" }
        RepeatStatus.FINISHED
    }.build()

    @Bean
    fun step2(): Step = stepBuilderFactory.get("step2").tasklet(stepTasklet).build()
}