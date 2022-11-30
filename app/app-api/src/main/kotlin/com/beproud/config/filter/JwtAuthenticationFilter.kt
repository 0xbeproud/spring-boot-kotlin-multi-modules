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
    private val customUserDetailsService: CustomUserDetailsService, private val tokenProvider: JwtTokenProvider
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain
    ) {
        try {
            logger.info("JwtAuthenticationFilter")

            val jwt = getJwtFromRequest(request) ?: throw java.lang.Exception("error")
            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                val userId: Long = tokenProvider.getUserIdFromJWT(jwt)
                val userDetails: UserDetails = customUserDetailsService.loadUserById(userId)
                val authentication =
                    UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities())
                authentication.setDetails(WebAuthenticationDetailsSource().buildDetails(request))
                SecurityContextHolder.getContext().authentication = authentication
            }
        } catch (ex: Exception) {
            logger.error("Could not set user authentication in security context", ex)
        }

        filterChain.doFilter(request, response)
    }

    private fun getJwtFromRequest(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader("Authorization")
        return if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            bearerToken.substring(7, bearerToken.length)
        } else null
    }
}