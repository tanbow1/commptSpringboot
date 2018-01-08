package com.tanb.commpt.core.constant;

public class SysConstant {

    // //////////////////////////////////////////////////////////公共参数

    public static final String SYS_PREFIX = "COMMONPT_";//本系统前缀

    public static final String WX_MINI_PREFIX = "wx_m_";//微信小程序前缀

    public static final String UTF8 = "UTF-8";

    public static final String GBK = "GBK";

    public static final String LANGUAGE_LOCAL = "zh-CN";

    public static final String LANGUAGE_EN = "en";

    public static final String DEFAULT_PWD = "123456";


    // //////////////////////////////////////////////////////////分页

    public static final int DEFAULT_PAGE_START = 1;
    public static final int DEFAULT_PAGE_SIZE = 10;


    // //////////////////////////////////////////////////////////公共消息返回

    public static final String SUCCESS_CODE = "1";
    public static final String SUCCESS = "成功";

    public static final String FAILED_CODE = "0";
    public static final String FAILED = "失败";

    public static final String UNKNOW_CODE = "-1";
    public static final String UNKNOW_ERROR = "未知错误";

    // //////////////////////////////////////////////////////////全局异常处理类

    public static final String RUNTIME_EXCEPTION_CODE = "EX001";
    public static final String RUNTIME_EXCEPTION = "程序运行出错";

    public static final String MAXUPLOADSIZE_EXCEPTION_CODE = "EX002";
    public static final String MAXUPLOADSIZE_EXCEPTION = "上传文件过大";

    public static final String IO_EXCEPTION_CODE = "EX003";
    public static final String IO_EXCEPTION = "输入输出异常";

    public static final String NOHANDLERFOUND_EXCEPTION_CODE = "EX404";
    public static final String NOHANDLERFOUND_EXCEPTION = "请求无效";

    public static final String NOACCESS_EXCEPTION_CODE = "EX403";
    public static final String NOACCESS_EXCEPTION = "禁止访问";

    public static final String SERVER_EXCEPTION_CODE = "EX500";
    public static final String SERVER_EXCEPTION = "请求出错";

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

    public static final String WARN_CODE_006 = "W006";
    public static final String WARN_MSG_006 = "token失效";

    public static final String WARN_CODE_007 = "W007";
    public static final String WARN_MSG_007 = "用户名或密码有误";

    public static final String WARN_CODE_008 = "W008";
    public static final String WARN_MSG_008 = "方法不存在，检查方法名是否有误";

    public static final String WARN_CODE_009 = "W009";
    public static final String WARN_MSG_009 = "方法调用权限不足";

    public static final String WARN_CODE_010 = "W010";
    public static final String WARN_MSG_010 = "服务实例化失败，检查方法名是否有误";

    public static final String WARN_CODE_011 = "W011";
    public static final String WARN_MSG_011 = "调用失败，检查方法体是否有误";

    public static final String WARN_CODE_012 = "W012";
    public static final String WARN_MSG_012 = "调用成功，但返回类型有误";

    public static final String WARN_CODE_013 = "W013";
    public static final String WARN_MSG_013 = "未找到该类，检查类名是否有误";

    public static final String WARN_CODE_014 = "W014";
    public static final String WARN_MSG_014 = "调用失败，检查传入参数是否有误";

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
    public static final String WARN_MSG_021 = "记录不存在";

    // //////////////////////////////////////////////////////////jwt

    public static final String JWT_ID = SYS_PREFIX + "_JWT";

    //accessToken2天有效
    public static final long JWT_TTL = 172800000l;  //ms

    //刷新频率每天
    public static final long JWT_REFRESH_INTERVAL = 86400000l;

    //refreshToken1年有效
    public static final long JWT_REFRESH_TTL = 31536000000l;

    public static final String ACCESS_TOKEN = SYS_PREFIX + "ACCESS_TOKEN";

    public static final String REFRESH_TOKEN = SYS_PREFIX + "REFRESH_TOKEN";

}
