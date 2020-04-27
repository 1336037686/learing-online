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

    // ################# 管理员院系管理模块 #################
    /**
     * 院系保存错误
     */
    public static final String SERVICE_DEPARTMENT_SAVE_FAIL_ERROR = "212001";

    /**
     * 院系删除错误
     */
    public static final String SERVICE_DEPARTMENT_DELETE_FAIL_ERROR = "212002";

    /**
     * 院系更新错误
     */
    public static final String SERVICE_DEPARTMENT_UPDATE_FAIL_ERROR = "212003";

    /**
     * 院系查询错误
     */
    public static final String SERVICE_DEPARTMENT_QUERY_FAIL_ERROR = "212004";

    // ################# 管理员专业管理模块 #################
    /**
     * 专业保存错误
     */
    public static final String SERVICE_SPECIALTY_SAVE_FAIL_ERROR = "213001";

    /**
     * 专业删除错误
     */
    public static final String SERVICE_SPECIALTY_DELETE_FAIL_ERROR = "213002";

    /**
     * 专业更新错误
     */
    public static final String SERVICE_SPECIALTY_UPDATE_FAIL_ERROR = "213003";

    /**
     * 专业查询错误
     */
    public static final String SERVICE_SPECIALTY_QUERY_FAIL_ERROR = "212304";

    // ################# 管理员学生管理模块 #################
    /**
     * 学生保存错误
     */
    public static final String SERVICE_STUDENT_SAVE_FAIL_ERROR = "214001";

    /**
     * 学生删除错误
     */
    public static final String SERVICE_STUDENT_DELETE_FAIL_ERROR = "214002";

    /**
     * 学生更新错误
     */
    public static final String SERVICE_STUDENT_UPDATE_FAIL_ERROR = "214003";

    /**
     * 学生查询错误
     */
    public static final String SERVICE_STUDENT_QUERY_FAIL_ERROR = "214004";

    // ################# 管理员教师管理模块 #################
    /**
     * 教师保存错误
     */
    public static final String SERVICE_TEACHER_SAVE_FAIL_ERROR = "215001";

    /**
     * 教师删除错误
     */
    public static final String SERVICE_TEACHER_DELETE_FAIL_ERROR = "215002";

    /**
     * 教师更新错误
     */
    public static final String SERVICE_TEACHER_UPDATE_FAIL_ERROR = "215003";

    /**
     * 教师查询错误
     */
    public static final String SERVICE_TEACHER_QUERY_FAIL_ERROR = "215004";

    // ################# 课程信息管理模块 #################
    /**
     * 课程信息保存错误
     */
    public static final String SERVICE_COURSE_SAVE_FAIL_ERROR = "216001";

    /**
     * 课程信息删除错误
     */
    public static final String SERVICE_COURSE_DELETE_FAIL_ERROR = "216002";

    /**
     * 课程信息更新错误
     */
    public static final String SERVICE_COURSE_UPDATE_FAIL_ERROR = "216003";

    /**
     * 课程信息查询错误
     */
    public static final String SERVICE_COURSE_QUERY_FAIL_ERROR = "216004";

    // ################# 课程章节信息管理模块 #################
    /**
     * 课程章节信息保存错误
     */
    public static final String SERVICE_SECTION_SAVE_FAIL_ERROR = "221001";

    /**
     * 课程章节信息删除错误
     */
    public static final String SERVICE_SECTION_DELETE_FAIL_ERROR = "221002";

    /**
     * 课程章节信息更新错误
     */
    public static final String SERVICE_SECTION_UPDATE_FAIL_ERROR = "221003";

    /**
     * 课程章节信息查询错误
     */
    public static final String SERVICE_SECTION_QUERY_FAIL_ERROR = "221004";

    // ################# 章节视频信息管理模块 #################
    /**
     * 课程章节视频信息保存错误
     */
    public static final String SERVICE_VIDEO_SAVE_FAIL_ERROR = "222001";

    /**
     * 课程章节视频信息删除错误
     */
    public static final String SERVICE_VIDEO_DELETE_FAIL_ERROR = "222002";

    /**
     * 课程章节视频信息更新错误
     */
    public static final String SERVICE_VIDEO_UPDATE_FAIL_ERROR = "222003";

    /**
     * 课程章节视频信息查询错误
     */
    public static final String SERVICE_VIDEO_QUERY_FAIL_ERROR = "222004";

    // ################# 章节资源信息管理模块 #################
    /**
     * 课程章节资源信息保存错误
     */
    public static final String SERVICE_RESOURCE_SAVE_FAIL_ERROR = "223001";

    /**
     * 课程章节资源信息删除错误
     */
    public static final String SERVICE_RESOURCE_DELETE_FAIL_ERROR = "223002";

    /**
     * 课程章节资源信息更新错误
     */
    public static final String SERVICE_RESOURCE_UPDATE_FAIL_ERROR = "223003";

    /**
     * 课程章节资源信息查询错误
     */
    public static final String SERVICE_RESOURCE_QUERY_FAIL_ERROR = "223004";

}
