package com.beproud.appbatch.creator

import mu.KotlinLogging
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.stereotype.Component


private val logger = KotlinLogging.logger {}

@Component
@StepScope
class StepTasklet : Tasklet {
    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus? {
        logger.info { "step2" }
        logger.debug { "step2" }
        return RepeatStatus.FINISHED
    }
}