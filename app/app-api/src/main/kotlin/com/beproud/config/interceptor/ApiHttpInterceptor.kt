package com.beproud.config.interceptor

import mu.KotlinLogging
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

private val logger = KotlinLogging.logger {}

@Component
class ApiHttpInterceptor(
    private val environment: Environment
) : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val method = request.method
        val requestPath = request.contextPath + request.servletPath
//        val userAgent = request.getHeader("User-Agent")
//        MDC.put("environment", environment.activeProfiles[0])

        logger.info { "$method $requestPath" }
        return true
    }
}