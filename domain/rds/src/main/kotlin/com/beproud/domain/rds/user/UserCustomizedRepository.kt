package com.beproud.domain.rds.user

interface UserCustomizedRepository {
    fun getCreators(): List<User>
}