package com.beproud.appapi.user

import com.beproud.appapi.user.v1.UserDomainService
import mu.KotlinLogging
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


private val logger = KotlinLogging.logger {}

