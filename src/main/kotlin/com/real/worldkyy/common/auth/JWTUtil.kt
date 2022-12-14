package com.real.worldkyy.common.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import java.util.*

object JWTUtil {
    private const val ISSUSER = "realworld"
    private const val SUBJECT = "Auth"
    private const val EXPIRE_TIME = 60L * 60 * 2 * 1000 // 2시간
    private const val REFRESH_EXPIRE_TIME = 60L * 60 * 24 * 30 * 1000 // 30일

    private val SECRET = "1234"
    private val algorithm: Algorithm = Algorithm.HMAC256(SECRET)

    private val refreshSecret = "5678"
    private  val refreshAlgorithm = Algorithm.HMAC256(refreshSecret)

    fun verify(token: String): DecodedJWT =
        JWT.require(algorithm)
            .withIssuer(ISSUSER)
            .build()
            .verify(token)

    fun verifyRefresh(token: String): DecodedJWT =
        JWT.require(refreshAlgorithm)
            .withIssuer(ISSUSER)
            .build()
            .verify(token)

    fun extractEamil(jwt: DecodedJWT): String = jwt.getClaim(JWTClaims.EMAIL).asString()

    fun createToken(email: String) = JWT.create()
        .withIssuer(ISSUSER)
        .withSubject(SUBJECT)
        .withIssuedAt(Date())
        .withExpiresAt(Date(Date().time + EXPIRE_TIME))
        .withClaim(JWTClaims.EMAIL, email)
        .sign(algorithm)

    fun createRefreshToken(email: String) = JWT.create()
        .withIssuer(ISSUSER)
        .withSubject(SUBJECT)
        .withIssuedAt(Date())
        .withExpiresAt(Date(Date().time + REFRESH_EXPIRE_TIME))
        .withClaim(JWTClaims.EMAIL, email)
        .sign(refreshAlgorithm)

    object JWTClaims {
        const val EMAIL = "email"
    }
}