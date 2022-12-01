package com.beproud.auth

import com.beproud.domain.rds.user.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserPrincipal : UserDetails {
    lateinit var user: User
    private lateinit var authorities: Collection<GrantedAuthority>

    override fun getAuthorities(): Collection<GrantedAuthority> = authorities

    override fun getPassword(): String = ""

    override fun getUsername(): String = user.walletAddress

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true

    companion object {
        fun create(user: User): UserPrincipal {
            val authorities = user.roles.split(",").map { SimpleGrantedAuthority(it) }
            return UserPrincipal().apply {
                this.user = user
                this.authorities = authorities
            }
        }
    }
}