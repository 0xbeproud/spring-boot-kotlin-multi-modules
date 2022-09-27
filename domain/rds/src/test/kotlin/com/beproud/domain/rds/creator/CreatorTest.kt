package com.beproud.domain.rds.creator

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension

@DataJpaTest(showSql = true)
@ExtendWith(SpringExtension::class)
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("Creator 도메인 테스트")
internal class CreatorTest {
    @field:Autowired
    lateinit var entityManager: TestEntityManager

    @field:Autowired
    lateinit var creatorRedisRepository: CreatorRedisRepository

    @BeforeEach
    fun setUp() {
    }

    @Test
    @DisplayName("기본 테스트")
    fun createUser() {
        // given
        val walletAddress = "walletAddress"

        // when
        val creator = this.entityManager.persistAndFlush(Creator.create(walletAddress = walletAddress))

        // then
        with(creator) {
            assertThat(this.walletAddress).isNotNull
            assertThat(this.walletAddress).isEqualTo(walletAddress)
        }
    }

    @AfterEach
    fun tearDown() {
    }
}