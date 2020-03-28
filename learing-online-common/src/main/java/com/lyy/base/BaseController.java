package com.lyy.base;

import com.lyy.common.CommonResponse;
import com.lyy.common.ResponseBody;
import com.lyy.common.ResponseHead;

/**
 * @author LGX_TvT
 * @date 2020-03-25 11:40
 */
public class BaseController<T> {

    private T t;

    public CommonResponse<T> buildCommonResponse(String responseCode, String message, T data) {
        return new CommonResponse<T>(new ResponseHead(responseCode, message), new ResponseBody<T>(data));
    }

}
