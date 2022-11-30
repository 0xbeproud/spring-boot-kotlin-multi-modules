package com.beproud.config.auth

import com.beproud.appapi.user.UserPrincipal
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.security.Keys
import mu.KotlinLogging
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.security.Key
import java.sql.Timestamp
import java.time.LocalDateTime


private val logger = KotlinLogging.logger {}

@Component
class JwtTokenProvider {
    private val AUTHORITIES_KEY = "auth"
    private val BEARER_TYPE = "Bearer"

    private val jwtSecret: String = "secret"
    private val jwtExpirationInSeconds = 816400L

    fun generateToken(authentication: Authentication): String {
        val userPrincipal = authentication.principal as UserPrincipal
        val now = LocalDateTime.now()
        val expiryDate = now.plusSeconds(jwtExpirationInSeconds)
        return Jwts.builder()
            .setSubject(userPrincipal.user.id.toString())
            .setIssuedAt(Timestamp.valueOf(now))
            .setExpiration(Timestamp.valueOf(expiryDate))
            .signWith(getSignKey(), SignatureAlgorithm.HS512)
            .compact()
    }

    private fun getSignKey(): Key {
        return Keys.hmacShaKeyFor(jwtSecret.toByteArray(StandardCharsets.UTF_8))
    }

    fun getUserIdFromJWT(token: String): Long {
        val claims = Jwts.parserBuilder()
            .setSigningKey(getSignKey())
            .build()
            .parseClaimsJws(token)
            .body

        return claims.subject.toLong()
    }

    fun validateToken(authToken: String): Boolean {
        try {
            Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(authToken)
            return true
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

    private fun parseClaims(accessToken: String): Claims {
        return try {
            Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(accessToken).body
        } catch (e: ExpiredJwtException) { // 만료된 토큰이 더라도 일단 파싱을 함
            e.claims
        }
    }
}