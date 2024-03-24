package com.threedumbdevs.springapi.services;


import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.crypto.MacProvider;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Service
@AllArgsConstructor
public class TokenService {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass().getName());
    private static final String EMAIL = "email";
    private static final String NAME = "name";
    private static final String ID = "id";
    private static final Key secret = MacProvider.generateKey(SignatureAlgorithm.HS256);
    static final byte[] secretBytes = secret.getEncoded();
    private static final String base64SecretBytes = Base64.getEncoder().encodeToString(secretBytes);
    private static final String TOKEN_PREFIX = "{\"token\":\"";
    private static final String TOKEN_SUFFIX = "\"}";
    private static final Long TTL_MILLIS = 1324800000L;

    public String createToken(final String name, final String email,final Long id) {
        String token = this.createJWT(name, email,id);
        return TOKEN_PREFIX + token + TOKEN_SUFFIX;
    }

    private String createJWT(String name, String email,Long id) {

        // The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        JwtBuilder builder = Jwts.builder()
                .setId(ID).setIssuedAt(now).claim("userid",id).claim(NAME, name).claim(EMAIL, email)
                .signWith(signatureAlgorithm, base64SecretBytes);

        if (TTL_MILLIS >= 0) {
            long expMillis = nowMillis + TTL_MILLIS;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        return builder.compact();
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(base64SecretBytes).parseClaimsJws(token);
            Claims claimsBody = claims.getBody();
            StringBuilder toLog = new StringBuilder();
            logClaims(claimsBody, toLog);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException e) {
            LOG.info("Access denied: " + e.getMessage());
            return false;
        }
    }


    public  String getMail(String token){
        Jws<Claims> claims = Jwts.parser().setSigningKey(base64SecretBytes).parseClaimsJws(token);
        Claims claimsBody = claims.getBody();
        return claimsBody.get(NAME).toString();
    }

    public  String getId(String token){
        Jws<Claims> claims = Jwts.parser().setSigningKey(base64SecretBytes).parseClaimsJws(token);
        Claims claimsBody = claims.getBody();
        return claimsBody.get(ID).toString();
    }

    private void logClaims(Claims claimsBody, StringBuilder toLog) {
        String separator = System.getProperty("line.separator");
        toLog.append(separator);
        toLog.append("ID: ").append(claimsBody.get(ID)).append(separator);
        toLog.append("Name: ").append(claimsBody.get(NAME)).append(separator);
        toLog.append("Expiration: ").append(claimsBody.getExpiration()).append(separator);
        LOG.debug(toLog.toString());
    }

}
