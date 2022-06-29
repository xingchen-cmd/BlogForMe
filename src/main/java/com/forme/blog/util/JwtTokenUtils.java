package com.forme.blog.util;

import com.forme.blog.common.BlogException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Objects;

import static com.forme.blog.common.BlogException.fail;


@Data
@Component
public class JwtTokenUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtils.class);
    /**
     * jjwt更新之后通过你选择的加密算法它底层可以为你生成一个安全随机密钥
     * 你可以通过下面这段代码查询密钥
     *  String secretString = Encoders.BASE64.encode(key.getEncoded());
     */
    private static final Key key= Keys.secretKeyFor(SignatureAlgorithm.HS512);
    //token 过期时间

    @Value("${jwt.expiration}")
    private Long expiration;



    /**
     * 根据userId生成token
     * @param userId
     * @return
     */
    public String generationToken(String userId){
        return Jwts.builder()
                .setExpiration(generationExcepirationDate())
                .setSubject(userId)
                .signWith(key)
                .compact();
    }


    /**
     * 根据token取出userid
     * @param token
     * @return
     */
    public String getUserIdFromToken(String token){
        return getClaimsFromToken(token).getSubject();
    }

    /**
     * 刷新token
     * @param oldToken
     * @return
     */
    public String refreshToken(String oldToken){
        Claims claims=getClaimsFromToken(oldToken);
        return generationToken(claims.getSubject());
    }

    /**
     * 检查token是否合法
     * @param token
     * @param userId
     * @return
     */

    public boolean validateToken(String token, String userId){

        return !isTokenExpired(token)&& getClaimsFromToken(token).getSubject().equals(userId);
    }

    /**
     *  生成token到期时间
     * @return
     */
    private Date generationExcepirationDate() {
        return new Date(System.currentTimeMillis()+expiration*1000);
    }

    /**
     * 从token中获取claims
     * @param token
     * @return
     */
    private Claims getClaimsFromToken(String token){
        Claims claims=null;
            try {
                claims=Jwts.parserBuilder()
                        .setSigningKey(key)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();
            }catch (Exception e){
                LOGGER.error("JWT格式验证失败{}",token);
            }
            if(Objects.isNull(claims)){
                fail("jwt验证失败");
            }
            return claims;
    }

    /**
     * 从token中获取过期时间
     * @param token
     * @return
     */
    private Date getExpirationFromToken(String token){
        return getClaimsFromToken(token).getExpiration();
    }



    /**
     * 判断token 是否过期
     * @param token
     * @return
     */
    private boolean isTokenExpired(String token){
        return getExpirationFromToken(token).before(new Date());
    }



}
