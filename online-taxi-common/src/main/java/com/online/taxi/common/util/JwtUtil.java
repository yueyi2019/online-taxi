package com.online.taxi.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.util.Date;

/**
 * @author yueyi2019
 */
public class JwtUtil {

    private static String secret = "ko346134h_we]rg3in_yip1!";

    public static String createToken(String subject, Date issueDate) {
        String compactJws = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(issueDate)
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, secret)
                .compact();
        return compactJws;
    }

    /**
     * 解密 jwt
     * @param token
     * @return
     * @throws Exception
     */
    public static String parseToken(String token) throws Exception {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        if (claims != null){
            return claims.getSubject();
        }
        return "";
    }

    public static void main(String[] args) {
        String subject = "wo";
        String token = createToken(subject,new Date());
        System.out.println(token);
        try {
            System.out.println(parseToken(token));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}