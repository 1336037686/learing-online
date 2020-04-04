package com.lyy.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * Md5加密工具
 * @author LGX_TvT
 * @date 2019-11-28 15:59
 */
public class Md5Util {

    /**
     * 静态方法，便于作为工具类
     *
     * @param str 使用算法前的字符串
     * @return 算法执行后的结果 字符串
     */
    public static String md5(String str) {
        try {
            int i;
            // 得到MessageDigest的对象
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] b = md.digest();
            // 创建StringBuffer对象
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            // 返回结果为32位加密
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println(Md5Util.md5("root"));
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ0ZW5hbnRfVXNlciIsImV4cCI6MTU4NTkwMTk0NiwidXNlck5hbWUiOiJyb290IiwiaWF0IjoxNTg1ODE1NTQ2LCJ1c2VySWQiOiIxMjMifQ.8wpQL36l4uaNs9FqasJ6sE9_Z-kyVC1IvnNy7sLWhq4";
        Map<String, Object> objectMap = TokenUtil.verifyToken(token);
        System.out.println(objectMap);

    }
}
