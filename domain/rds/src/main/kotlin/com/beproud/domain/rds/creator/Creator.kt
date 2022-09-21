package com.beproud.domain.rds.creator

import com.beproud.domain.rds.BaseLocalDateTime
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "creator")
class Creator : Serializable, BaseLocalDateTime() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L

    @Column(name = "wallet_address", nullable = false)
    lateinit var walletAddress: String

    companion object {
        fun create(walletAddress: String): Creator {
            return Creator().apply {
                this.walletAddress = walletAddress
            }
        }
    }
}