package com.beproud.domain.rds.user

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport

class UserCustomizedRepositoryImpl : QuerydslRepositorySupport(User::class.java), UserCustomizedRepository {
    override fun getCreators(): List<User> = from(QUser.user).fetch()
}