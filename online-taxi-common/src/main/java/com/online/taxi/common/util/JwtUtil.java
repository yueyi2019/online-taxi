package com.online.taxi.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

import java.util.Date;

/**
 * @author yueyi2019
 */
public class JwtUtil {
    /**
     * 密钥，仅服务端存储
     */
    private static String secret = "ko346134h_we]rg3in_yip1!";

    /**
     *
     * @param subject
     * @param issueDate 签发时间
     * @return
     */
    public static String createToken(String subject, Date issueDate) {
        String compactJws = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(issueDate)
                .setExpiration(new Date(System.currentTimeMillis()+10000))
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
    public static String parseToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            if (claims != null){
                return claims.getSubject();
            }
        }catch (ExpiredJwtException e){
            e.printStackTrace();
            System.out.println("jwt过期了");
        }

        return "";
    }

    public static void main(String[] args) {
        String subject = "wo";
        String token = createToken(subject,new Date());
        System.out.println(token);
        try {
//			Thread.sleep(10010);
        	Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("原始值："+parseToken(token));

    }

}