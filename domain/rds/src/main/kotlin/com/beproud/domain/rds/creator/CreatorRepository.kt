package com.beproud.domain.rds.creator

import org.springframework.data.jpa.repository.JpaRepository

interface CreatorRepository : JpaRepository<Creator, Long> {
}