package com.beproud.config.filter

import com.beproud.appapi.user.CustomUserDetailsService
import com.beproud.config.auth.JwtTokenProvider
import mu.KotlinLogging
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

private val logger = KotlinLogging.logger {}

class JwtAuthenticationFilter(
    private val customUserDetailsService: CustomUserDetailsService,
    private val jwtTokenProvider: JwtTokenProvider
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain
    ) {
        try {
            val token = jwtTokenProvider.resolveToken(request)
            logger.info("token: $token")

            if (token != null && jwtTokenProvider.validateToken(token)) {
                val walletAddress = jwtTokenProvider.getWalletAddress(token)
                logger.info("walletAddress: $walletAddress")
                val userDetails: UserDetails = customUserDetailsService.loadUserByUsername(walletAddress)
                val authentication =
                    UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authentication
            }
        } catch (ex: Exception) {
            logger.error("Could not set user authentication in security context", ex)
        }

        filterChain.doFilter(request, response)
    }


}