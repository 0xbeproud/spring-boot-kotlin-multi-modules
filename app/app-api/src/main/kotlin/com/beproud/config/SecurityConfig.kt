package com.beproud.config

import com.beproud.appapi.user.CustomUserDetailsService
import com.beproud.config.auth.JwtTokenProvider
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

//    @Bean
//    fun passwordEncoder(): PasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()

//    @Bean
//    @Order(0)
//    @Throws(Exception::class)
//    fun resources(http: HttpSecurity): SecurityFilterChain? {
//        return http.requestMatchers { matchers: HttpSecurity.RequestMatcherConfigurer ->
//            matchers.antMatchers(HttpMethod.GET, "/api/v1/creators")
//        }.authorizeHttpRequests { authorize ->
//            authorize.anyRequest().permitAll()
//        }.requestCache { it.disable() }.securityContext { it.disable() }.sessionManagement { it.disable() }.build()
//    }

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
//                it.anyRequest().permitAll()
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
        }
    }

//    @Bean
//    fun authenticationProvider(): DaoAuthenticationProvider {
//        val authProvider = DaoAuthenticationProvider()
//        authProvider.setUserDetailsService(userDetailsService)
//        authProvider.setPasswordEncoder(passwordEncoder())
//        return authProvider
//    }

    @Bean
    @Throws(java.lang.Exception::class)
    fun authenticationManager(authConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authConfiguration.authenticationManager
    }

    //
//    @Bean
//    fun jwtAuthenticationConverter(): JwtAuthenticationConverter? {
//        val grantedAuthoritiesConverter = JwtGrantedAuthoritiesConverter()
//
//        //change the prefix
//        grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_")
//
//        //change the default claim name. default claim is "scope", "scp"
//        grantedAuthoritiesConverter.setAuthoritiesClaimName("name")
//        val jwtAuthenticationConverter = JwtAuthenticationConverter()
//        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter)
//        return jwtAuthenticationConverter
//    }
//
//    /**
//     * Asisgn a custom JwtDecoder with RestOperation instance to provide a custom timeout.
//     */
//    @Bean
//    fun jwtDecoder(restTemplateBuilder: RestTemplateBuilder): JwtDecoder? {
//        val restOperations = restTemplateBuilder
//            .setConnectTimeout(Duration.ofSeconds(90))
//            .setReadTimeout(Duration.ofSeconds(90))
//            .build()
//        val nimbusJwtDecoder = NimbusJwtDecoder
//            .withJwkSetUri(jwkSetUri)
//            .restOperations(restOperations)
//            .build()
//        val clockSkew: OAuth2TokenValidator<Jwt> =
//            DelegatingOAuth2TokenValidator<AbstractOAuth2Token>(JwtTimestampValidator(Duration.ofSeconds(60)))
//        nimbusJwtDecoder.setJwtValidator(clockSkew)
//        return nimbusJwtDecoder
//    }

}