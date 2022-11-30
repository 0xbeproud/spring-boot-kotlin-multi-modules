package com.beproud.domain.rds.user

import com.beproud.domain.rds.BaseLocalDateTime
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "creator")
class User : Serializable, BaseLocalDateTime() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L

    @Column(name = "wallet_address", nullable = false)
    lateinit var walletAddress: String

    @Column(name = "roles", nullable = false)
    lateinit var roles: String

    companion object {
        fun create(walletAddress: String): User {
            return User().apply {
                this.walletAddress = walletAddress
            }
        }
    }
}