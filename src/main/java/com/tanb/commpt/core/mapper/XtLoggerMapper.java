package com.tanb.commpt.core.mapper;

import com.tanb.commpt.core.po.XtLogger;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface XtLoggerMapper {
    @Delete({
        "delete from XT_LOGGER",
        "where LOGGER_ID = #{loggerId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String loggerId);

    @Insert({
        "insert into XT_LOGGER (LOGGER_ID, LOGGER_DESC, ",
        "LOG_TYPE, CREATE_TIME, ",
        "CREATE_USER, LOCATION, ",
        "LONGITUDE, LATITUDE, ",
        "IP, STATUS, APP_ID)",
        "values (#{loggerId,jdbcType=VARCHAR}, #{loggerDesc,jdbcType=VARCHAR}, ",
        "#{logType,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{createUser,jdbcType=VARCHAR}, #{location,jdbcType=VARCHAR}, ",
        "#{longitude,jdbcType=VARCHAR}, #{latitude,jdbcType=VARCHAR}, ",
        "#{ip,jdbcType=VARCHAR}, #{status,jdbcType=CHAR}, #{appId,jdbcType=VARCHAR})"
    })
    int insert(XtLogger record);

    int insertSelective(XtLogger record);

    @Select({
        "select",
        "LOGGER_ID, LOGGER_DESC, LOG_TYPE, CREATE_TIME, CREATE_USER, LOCATION, LONGITUDE, ",
        "LATITUDE, IP, STATUS, APP_ID",
        "from XT_LOGGER",
        "where LOGGER_ID = #{loggerId,jdbcType=VARCHAR}"
    })
    @ResultMap("com.tanb.commpt.core.mapper.XtLoggerMapper.BaseResultMap")
    XtLogger selectByPrimaryKey(String loggerId);

    int updateByPrimaryKeySelective(XtLogger record);

    @Update({
        "update XT_LOGGER",
        "set LOGGER_DESC = #{loggerDesc,jdbcType=VARCHAR},",
          "LOG_TYPE = #{logType,jdbcType=VARCHAR},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
          "CREATE_USER = #{createUser,jdbcType=VARCHAR},",
          "LOCATION = #{location,jdbcType=VARCHAR},",
          "LONGITUDE = #{longitude,jdbcType=VARCHAR},",
          "LATITUDE = #{latitude,jdbcType=VARCHAR},",
          "IP = #{ip,jdbcType=VARCHAR},",
          "STATUS = #{status,jdbcType=CHAR},",
          "APP_ID = #{appId,jdbcType=VARCHAR}",
        "where LOGGER_ID = #{loggerId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(XtLogger record);
}