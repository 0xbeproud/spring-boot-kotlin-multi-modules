package com.beproud.appapi.user.v1.dto

import com.beproud.domain.rds.user.User
import java.io.Serializable

data class GetUserResponse(val user: User?) : Serializable

data class CreateUserResponse(val user: User)