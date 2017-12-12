package com.tanb.commpt.core.mapper;

import com.tanb.commpt.core.po.XtJwt;
import com.tanb.commpt.core.po.XtJwtKey;
import org.apache.ibatis.annotations.*;

import java.util.concurrent.ConcurrentHashMap;

public interface XtJwtMapper  {
    @Delete({
            "delete from T_XT_JWT",
            "where USER_ID = #{userId,jdbcType=VARCHAR}",
            "and ACCESS_TOKEN = #{accessToken,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(XtJwtKey key);

    @Insert({
            "insert into T_XT_JWT (USER_ID, ACCESS_TOKEN, ",
            "REFRESH_TOKEN, JWT_TTL, ",
            "JWT_REFRESH_TTL, JWT_REFRESH_INTERVAL)",
            "values (#{userId,jdbcType=VARCHAR}, #{accessToken,jdbcType=VARCHAR}, ",
            "#{refreshToken,jdbcType=VARCHAR}, #{jwtTtl,jdbcType=TIMESTAMP}, ",
            "#{jwtRefreshTtl,jdbcType=TIMESTAMP}, #{jwtRefreshInterval,jdbcType=TIMESTAMP})"
    })
    int insert(XtJwt record);

    int insertSelective(XtJwt record);

    XtJwt selectByPrimaryKey(XtJwtKey key);

    int updateByPrimaryKeySelective(XtJwt record);

    @Update({
            "update T_XT_JWT",
            "set REFRESH_TOKEN = #{refreshToken,jdbcType=VARCHAR},",
            "JWT_TTL = #{jwtTtl,jdbcType=TIMESTAMP},",
            "JWT_REFRESH_TTL = #{jwtRefreshTtl,jdbcType=TIMESTAMP},",
            "JWT_REFRESH_INTERVAL = #{jwtRefreshInterval,jdbcType=TIMESTAMP}",
            "where USER_ID = #{userId,jdbcType=VARCHAR}",
            "and ACCESS_TOKEN = #{accessToken,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(XtJwt record);

    @Delete({
            "delete from T_XT_JWT",
            "where USER_ID = #{userId,jdbcType=VARCHAR}"
    })
    int deleteByUserId(String userId);

    int insert2(XtJwt xtJwt);

    ConcurrentHashMap<String, String> selectByAccessToken(String accessToken);

    String selectByRefreshToken(@Param("accessToken") String accessToken, @Param("refreshToken") String refreshToken);
}