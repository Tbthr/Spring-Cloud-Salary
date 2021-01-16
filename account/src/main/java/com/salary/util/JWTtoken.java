package com.salary.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.Serializable;
import java.util.Date;

public class JWTtoken implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * JWT签名加密key
     */
    private final static String JWT_SIGN_KEY = "fddc153422604359a0b4594dc5sd56a41s";

    /**
     * 获取用户名
     *
     * @return
     */
    public static String getInfoFromToken(String token) throws Exception {
        Claims claims = Jwts.parser().setSigningKey(JWT_SIGN_KEY).parseClaimsJws(token).getBody();
        return claims.get("id").toString();
    }

    /**
     * 生成token
     *
     * @param id
     * @return
     */
    public static String setToken(String id) {
        // 登陆成功生成token
        return Jwts.builder()
                // 主题 放入用户名
                .claim("id", id)
                // 失效时间
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 12 * 1000))
                // 签名算法和密钥
                .signWith(SignatureAlgorithm.HS512, JWT_SIGN_KEY).compact();
    }

    public static void main(String[] args) throws Exception {
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJpZCI6IjEiLCJleHAiOjE2MDYyMzc3MjR9.Jd0aea8JQhbDQ1cH3ESDdhisLuXCjr_8EOSya6SGd-1ZQzQPFJ0trrr_UzcxCBDm68WZQhZMulPgQxJpgdJ7-A";
        System.out.println(getInfoFromToken(token));
    }
}
