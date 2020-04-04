package com.lyy.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**token生成与解密
 * @author wulei
 */
public class TokenUtil {
    private static final String  SECRET = "session_secret";

    //发布者 后面一块去校验
    private static final String  ISSUER = "tenant_User";
    private static  final Long expire=24L * 3600L *1000L;

    /**生成token
     * @param claims token中payload部分
     * @return token
     */
    public static String getToken(Map<String, Object> claims){
        try {
            //签名算法
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            long nowMills= System.currentTimeMillis();
            Date now= new Date(nowMills);
            long ttlMillis= nowMills+expire;
            Date expireTime=new Date(ttlMillis);
            JWTCreator.Builder builder = JWT.create()
                    .withIssuer(ISSUER)
                    .withIssuedAt(now)
                    .withExpiresAt(expireTime);
            //相当于将claims存储在token中
            claims.forEach((k,v) -> builder.withClaim(k, String.valueOf(v)));
            return builder.sign(algorithm).toString();
        } catch (IllegalArgumentException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getToken(String userId, String userName) {
        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("userId", userId);
        tokenMap.put("userName", userName);
        return getToken(tokenMap);
    }

    /**解析token字符串
     * @param token token
     * @return token中payload的部分以map形式返回
     */
    public static Map<String, Object> verifyToken(String token)  throws Exception{
        Algorithm algorithm = null;
        algorithm = Algorithm.HMAC256(SECRET);
        JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
        DecodedJWT jwt =  verifier.verify(token);
        Map<String, Claim> map = jwt.getClaims();
        Map<String, Object> resultMap = new HashMap<String,Object>();
        map.forEach((k,v) -> resultMap.put(k, v.asString()));
        return resultMap;
    }

    /**从token中获取用户id
     * @param token token
     * @return 用户id
     */
    public static String getUserIdFromToken(String token) throws  Exception{
        Map<String,Object> maps=verifyToken(token);
        return maps.get("userId").toString();
    }
}
