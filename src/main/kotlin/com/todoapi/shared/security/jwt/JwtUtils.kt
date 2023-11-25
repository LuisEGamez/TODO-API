package com.todoapi.shared.security.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import lombok.extern.slf4j.Slf4j
import org.hibernate.query.sqm.tree.SqmNode.log
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtUtils {

  @Value("\${jwt.secret.key}")
  private lateinit var secretKey: String

  @Value("\${jwt.time.expiration}")
  private lateinit var timeExpiration: Number

  // Generate access token
  fun generateAccessToken(userName: String): String {
    return Jwts
      .builder()
      .subject(userName)
      .issuedAt(Date(System.currentTimeMillis()))
      .expiration(Date(System.currentTimeMillis() + timeExpiration.toLong()))
      .signWith(getSignatureKey(), Jwts.SIG.HS256)
      .compact()
  }

  // Validate access token
  fun isTokenValid(token: String): Boolean {
    return try {
      Jwts
        .parser()
        .verifyWith(getSignatureKey())
        .build()
        .parseSignedClaims(token)
        .payload
      true
    } catch (e: Exception) {
      println("Invalid token, error: ${e.message}")
      false
    }
  }

  // Get claims form token
  fun getAllClaims(token: String): Claims{
    return Jwts
      .parser()
      .verifyWith(getSignatureKey())
      .build()
      .parseSignedClaims(token)
      .payload
  }

  // Get username from token
  fun getUserName(token: String): String{
    return getClaim(token, Claims::getSubject)
  }

  // Get a claim
  fun <T> getClaim(token: String, function: (Claims) -> T): T {
    val claims = getAllClaims(token)
    return function.invoke(claims)

  }

  // Obtain signature from token
  fun getSignatureKey(): SecretKey {
    val keyBytes = Decoders.BASE64.decode(secretKey)
    return Keys.hmacShaKeyFor(keyBytes)
  }
}