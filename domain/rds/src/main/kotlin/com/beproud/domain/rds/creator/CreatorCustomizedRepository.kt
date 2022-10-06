package com.beproud.domain.rds.creator

interface CreatorCustomizedRepository {
    fun getCreators(): List<Creator>
}