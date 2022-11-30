package com.beproud.config.auth

import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.security.Keys
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import java.nio.charset.StandardCharsets
import java.security.Key
import java.util.*
import javax.servlet.http.HttpServletRequest


private val logger = KotlinLogging.logger {}

@Component
class JwtTokenProvider {
    private val AUTHORITIES_KEY = "auth"
    private val BEARER_TYPE = "Bearer"

    private val jwtSecret: String =
        "secret1234567890abcdefghijklmnopqrstuvsecret1234567890abcdefghijklmnopqrstuvsecret1234567890abcdefghijklmnopqrstuvsecret1234567890abcdefghijklmnopqrstuv"
    private val jwtExpirationInSeconds = 1000L * 60 * 60

    fun generateToken(walletAddress: String): String {
        val now = Date()
        return Jwts.builder()
            .setClaims(Jwts.claims().setSubject(walletAddress))
            .setIssuedAt(now)
            .setExpiration(Date(now.time + jwtExpirationInSeconds))
            .signWith(getSignKey(), SignatureAlgorithm.HS512)
            .compact()
    }

    private fun getSignKey(): Key {
        return Keys.hmacShaKeyFor(jwtSecret.toByteArray(StandardCharsets.UTF_8))
    }

    fun getWalletAddress(token: String): String {
        val claims = Jwts.parserBuilder()
            .setSigningKey(getSignKey())
            .build()
            .parseClaimsJws(token)
            .body

        return claims.subject
    }

    fun validateToken(token: String): Boolean {
        try {
            val claims = Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token)
            return !claims.body.expiration.before(Date())
        } catch (ex: MalformedJwtException) {
            logger.error("Invalid JWT token")
        } catch (ex: ExpiredJwtException) {
            logger.error("Expired JWT token")
        } catch (ex: UnsupportedJwtException) {
            logger.error("Unsupported JWT token")
        } catch (ex: IllegalArgumentException) {
            logger.error("JWT claims string is empty.")
        }
        return false
    }

    fun resolveToken(request: HttpServletRequest): String? = request.getHeader("X-AUTH-TOKEN")
}