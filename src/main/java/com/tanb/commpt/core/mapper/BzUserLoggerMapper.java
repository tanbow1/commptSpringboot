package com.tanb.commpt.core.mapper;

import com.tanb.commpt.core.po.BzUserLogger;
import com.tanb.commpt.core.po.BzUserLoggerKey;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface BzUserLoggerMapper {
    @Delete({
        "delete from BZ_USER_LOGGER",
        "where LOGGER_ID = #{loggerId,jdbcType=VARCHAR}",
          "and USER_ID = #{userId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(BzUserLoggerKey key);

    @Insert({
        "insert into BZ_USER_LOGGER (LOGGER_ID, USER_ID, ",
        "LOGGER_DESC, LOG_TYPE, ",
        "CREATE_TIME, CREATE_USER, ",
        "LOCATION, LONGITUDE, ",
        "LATITUDE, IP, STATUS, ",
        "APP_ID)",
        "values (#{loggerId,jdbcType=VARCHAR}, #{userId,jdbcType=VARCHAR}, ",
        "#{loggerDesc,jdbcType=VARCHAR}, #{logType,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{createUser,jdbcType=VARCHAR}, ",
        "#{location,jdbcType=VARCHAR}, #{longitude,jdbcType=VARCHAR}, ",
        "#{latitude,jdbcType=VARCHAR}, #{ip,jdbcType=VARCHAR}, #{status,jdbcType=CHAR}, ",
        "#{appId,jdbcType=VARCHAR})"
    })
    int insert(BzUserLogger record);

    int insertSelective(BzUserLogger record);

    @Select({
        "select",
        "LOGGER_ID, USER_ID, LOGGER_DESC, LOG_TYPE, CREATE_TIME, CREATE_USER, LOCATION, ",
        "LONGITUDE, LATITUDE, IP, STATUS, APP_ID",
        "from BZ_USER_LOGGER",
        "where LOGGER_ID = #{loggerId,jdbcType=VARCHAR}",
          "and USER_ID = #{userId,jdbcType=VARCHAR}"
    })
    @ResultMap("com.tanb.commpt.core.mapper.BzUserLoggerMapper.BaseResultMap")
    BzUserLogger selectByPrimaryKey(BzUserLoggerKey key);

    int updateByPrimaryKeySelective(BzUserLogger record);

    @Update({
        "update BZ_USER_LOGGER",
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
        "where LOGGER_ID = #{loggerId,jdbcType=VARCHAR}",
          "and USER_ID = #{userId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(BzUserLogger record);
}