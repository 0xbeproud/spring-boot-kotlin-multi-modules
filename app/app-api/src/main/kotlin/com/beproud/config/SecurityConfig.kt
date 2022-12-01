package com.beproud.config

import com.beproud.auth.CustomUserDetailsService
import com.beproud.auth.JwtTokenProvider
import com.beproud.config.filter.JwtAuthenticationFilter
import mu.KotlinLogging
import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


private val logger = KotlinLogging.logger {}

@Configuration
//@EnableGlobalMethodSecurity(
//    // securedEnabled = true,
//    // jsr250Enabled = true,
//    prePostEnabled = true)
class SecurityConfig(
    private val userDetailsService: CustomUserDetailsService,
    private val tokenProvider: JwtTokenProvider
) {

    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .httpBasic { it.disable() }
            .csrf { it.disable() }
            .logout { it.disable() }
            .formLogin { it.disable() }
//            .authenticationProvider(authenticationProvider())
//            .addFilterBefore(jwtAuthenticationFilter(userDetailsService,))
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeRequests {
                it.antMatchers("/api/v1/connect/**").permitAll()
                    .anyRequest().authenticated()
            }
            .addFilterBefore(
                JwtAuthenticationFilter(userDetailsService, tokenProvider),
                UsernamePasswordAuthenticationFilter::class.java
            )
//            .oauth2ResourceServer().jwt()
        return http.build()
    }

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer { web: WebSecurity ->
            web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .antMatchers("/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**")

        }
    }

    @Bean
    @Throws(java.lang.Exception::class)
    fun authenticationManager(authConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authConfiguration.authenticationManager
    }
}