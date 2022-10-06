package com.beproud.domain.rds.creator

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport

class CreatorCustomizedRepositoryImpl() : QuerydslRepositorySupport(Creator::class.java), CreatorCustomizedRepository {
    override fun getCreators(): List<Creator> = from(QCreator.creator).fetch()
}