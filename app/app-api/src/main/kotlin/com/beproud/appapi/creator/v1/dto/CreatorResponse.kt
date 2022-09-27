package com.beproud.appapi.creator.v1.dto

import com.beproud.domain.rds.creator.Creator
import java.io.Serializable

data class GetCreatorResponse(val creator: Creator?) : Serializable

data class CreateCreatorResponse(val creator: Creator)