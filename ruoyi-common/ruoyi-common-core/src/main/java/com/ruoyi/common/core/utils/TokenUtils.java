package com.ruoyi.common.core.utils;

import com.ruoyi.common.core.constant.TokenConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;

/**
 * 后台与网站用户Token解析工具类
 *
 * @time 2022/11/2
 * @author MoChen
 */
public class TokenUtils {

    private static final Logger log = LoggerFactory.getLogger(TokenUtils.class);

    private static final String MOV_PATH_PREFIX = "^/mov/api[\\s\\S]*";

    /**
     * 根据不同类型的用户获取到对应的token
     * @param request 请求对象
     * @return token字符串
     */
    public static String getToken(ServerHttpRequest request){
        String pathString = request.getPath().toString();
        if (pathString.matches(MOV_PATH_PREFIX)){
            // 当前为网站用户接口
            return getTokenByHeader(request, TokenConstants.MOVTOKEN);
        }else{
            // 当前为后台用户接口
            return getTokenByHeader(request, TokenConstants.AUTHENTICATION);
        }
    }

    /**
     * 使用key获取headers中的指定token
     * @param request 请求对象
     * @param headerKey 需要获取的token的key
     * @return token字符串
     */
    private static String getTokenByHeader(ServerHttpRequest request, String headerKey){
        String token = request.getHeaders().getFirst(headerKey);
        // 如果前端设置了令牌前缀，则裁剪掉前缀
        if (StringUtils.isNotEmpty(token) && token.startsWith(TokenConstants.PREFIX))
        {
            token = token.replaceFirst(TokenConstants.PREFIX, StringUtils.EMPTY);
        }
        return token;
    }
}
