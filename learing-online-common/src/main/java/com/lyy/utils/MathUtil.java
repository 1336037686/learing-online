package com.lyy.utils;

/**
 * 数字数学工具类
 * @author LGX_TvT
 * @date 2019-12-17 23:43
 */
public class MathUtil {
    /**
     * 生成4位数字验证码
     * @return 验证码
     */
    public static String vcode(int length){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((int)(Math.random() * 9) + "");
        }
        return sb.toString();
    }
}
