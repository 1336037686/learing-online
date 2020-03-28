package com.lyy.exception;

/**
 * 错误状态码
 * @author LGX_TvT
 * @date 2019-11-29 10:56
 */
public final class ErrorCode {

    // ################# 子模块 任务 #################

    /**
     * 系统版本错误
     */
    public static final String SYSTEM_VERSION_ERROR = "100008";

    /**
     * 系统缓存错误
     */
    public static final String SYSTEM_CACHE_ERROR = "100009";

    /**
     * 类型转换错误
     */
    public static final String SYSTEM_CONVERTER_ERROR = "100010";

    /**
     * 网关错误
     */
    public static final String SYSTEM_GATEWAY_ERROR = "100011";

    /**
     * 认证/授权错误
     */
    public static final String SYSTEM_AUTHORIZE_ERROR = "100012";

    /**
     * CDN错误
     */
    public static final String SYSTEM_CDN_ERROR = "130101";

    /**
     * SMS错误
     */
    public static final String SYSTEM_SMS_ERROR = "100014";

    /**
     * Email错误
     */
    public static final String SYSTEM_EMAIL_ERROR = "100015";

    /**
     * 基础数据服务错误
     */
    public static final String SYSTEM_BASIC_DATA_ERROR = "100016";

    /**
     * 数据库创建错误
     */
    public static final String SYSTEM_DB_CREATE_ERROR = "100017";

    /**
     * 数据库表创建错误
     */
    public static final String SYSTEM_DB_TABLE_CREATE_ERROR = "100018";

    /**
     * 数据库连接错误
     */
    public static final String SYSTEM_DB_CONNECTION_ERROR = "100019";

    /**
     * 数据库SQL语句执行错误
     */
    public static final String SYSTEM_DB_SQL_ERROR = "100020";

    /**
     * Exception其他异常
     */
    public static final String SYSTEM_EXCEPTION_ERROR = "100999";


    // ################# 服务 错误码 #################

    // ################# 类别管理模块 #################
    /**
     * 类别保存错误
     */
    public static final String SERVICE_CATEGORY_SAVE_FAIL_ERROR = "210001";

    /**
     * 类别删除错误
     */
    public static final String SERVICE_CATEGORY_DELETE_FAIL_ERROR = "210002";

    /**
     * 类别更新错误
     */
    public static final String SERVICE_CATEGORY_UPDATE_FAIL_ERROR = "210003";

    /**
     * 类别查询错误
     */
    public static final String SERVICE_CATEGORY_QUERY_FAIL_ERROR = "210004";

    // ################# 管理员公告管理模块 #################
    /**
     * 公告保存错误
     */
    public static final String SERVICE_ADMIN_ANNOUNCEMENT_SAVE_FAIL_ERROR = "211001";

    /**
     * 公告删除错误
     */
    public static final String SERVICE_ADMIN_ANNOUNCEMENT_DELETE_FAIL_ERROR = "211002";

    /**
     * 公告更新错误
     */
    public static final String SERVICE_ADMIN_ANNOUNCEMENT_UPDATE_FAIL_ERROR = "211003";

    /**
     * 公告查询错误
     */
    public static final String SERVICE_ADMIN_ANNOUNCEMENT_QUERY_FAIL_ERROR = "211004";

}
