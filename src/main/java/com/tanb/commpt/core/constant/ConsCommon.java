package com.tanb.commpt.core.constant;

public class ConsCommon {

    // //////////////////////////////////////////////////////////公共参数

    public static final String SYS_PREFIX = "COMMONPT_";

    public static final String UTF8 = "UTF-8";

    public static final String GBK = "GBK";

    public static final String LANGUAGE_LOCAL = "zh-CN";

    public static final String LANGUAGE_EN = "en";


    // //////////////////////////////////////////////////////////


    public static final int BATCH_COUNT = 100;//批量提交数

    // //////////////////////////////////////////////////////////用户

    public static final String ROLE_ID_SUPER = "001";// 超级用户roleId

    // //////////////////////////////////////////////////////////分页

    public static final int DEFAULT_PAGE_START = 1;

    public static final int DEFAULT_PAGE_SIZE = 10;


    // //////////////////////////////////////////////////////////公共消息返回

    public static final String SUCCESS_CODE = "1";

    public static final String SUCCESS_MSG = "success";

    public static final String ERROR_CODE = "0";

    public static final String ERROR_MSG = "fail";

    public static final String ERROR_CODE_UNKNOW = "-1";

    public static final String ERROR_MSG_UNKNOW = "unknow error";

    // //////////////////////////////////////////////////////////异常

    public static final String RUNTIME_EXCEPTION_CODE = "EX001";
    public static final String RUNTIME_EXCEPTION_MSG = "运行时异常";

    // //////////////////////////////////////////////////////////错误

    public static final String MAXUPLOADSIZE_EXCEPTION_CODE = "E002";
    public static final String MAXUPLOADSIZE_EXCEPTION_MSG = "上传文件过大";

    // //////////////////////////////////////////////////////////警告

    public static final String WARN_CODE_001 = "W001";
    public static final String WARN_MSG_001 = "账户已存在";

    public static final String WARN_CODE_002 = "W002";
    public static final String WARN_MSG_002 = "该证件号码已经被注册";

    public static final String WARN_CODE_003 = "W003";
    public static final String WARN_MSG_003 = "该手机号已经被注册";

    public static final String WARN_CODE_004 = "W004";
    public static final String WARN_MSG_004 = "accessToken为空";

    public static final String WARN_CODE_005 = "W005";
    public static final String WARN_MSG_005 = "refreshToken为空";

    public static final String WARN_CODE_006 = "W0006";
    public static final String WARN_MSG_006 = "token失效";

    public static final String WARN_CODE_007 = "W0007";
    public static final String WARN_MSG_007 = "用户名或密码有误";

    public static final String WARN_CODE_008 = "W008";
    public static final String WARN_MSG_008 = "方法不存在，检查methodName是否有误";

    public static final String WARN_CODE_009 = "W009";
    public static final String WARN_MSG_009 = "方法调用权限不足，检查是否为private";

    public static final String WARN_CODE_010 = "W010";
    public static final String WARN_MSG_010 = "服务实例化失败，检查serviceName是否有误";

    public static final String WARN_CODE_011 = "W011";
    public static final String WARN_MSG_011 = "调用失败，检查方法体是否有误";

    public static final String WARN_CODE_012 = "W012";
    public static final String WARN_MSG_012 = "调用成功，返回类型有误";

    public static final String WARN_CODE_013 = "W013";
    public static final String WARN_MSG_013 = "未找到该类，检查className是否有误";

    public static final String WARN_CODE_014 = "W014";
    public static final String WARN_MSG_014 = "调用失败，检查传入参数";

    public static final String WARN_CODE_015 = "W015";
    public static final String WARN_MSG_015 = "方法参数类型不支持";

    public static final String WARN_CODE_016 = "W016";
    public static final String WARN_MSG_016 = "数据更新或新增失败";

    public static final String WARN_CODE_017 = "W017";
    public static final String WARN_MSG_017 = "数据删除失败";

    public static final String WARN_CODE_018 = "W0018";
    public static final String WARN_MSG_018 = "存在重复数据";

    public static final String WARN_CODE_019 = "W019";
    public static final String WARN_MSG_019 = "未获取文件";

    public static final String WARN_CODE_020 = "W020";
    public static final String WARN_MSG_020 = "未获取文件内容，请检查内容合法性";

    public static final String WARN_CODE_021 = "W021";
    public static final String WARN_MSG_021 = "主键不存在，无法获取记录";

    // //////////////////////////////////////////////////////////jwt

    public static final String JWT_ID = "jwt";

    //accessToken2天有效
    public static final int JWT_TTL = 48 * 60 * 60 * 1000;  //millisecond

    //refreshToken每天刷新
    public static final int JWT_REFRESH_INTERVAL = 24 * 60 * 60 * 1000;  //millisecond

    //refreshToken1年有效
    public static final int JWT_REFRESH_TTL = 356 * 24 * 60 * 60 * 1000;  //millisecond

    public static final String ACCESS_TOKEN = SYS_PREFIX + "ACCESS_TOKEN";

    public static final String REFRESH_TOKEN = SYS_PREFIX + "REFRESH_TOKEN";

}
