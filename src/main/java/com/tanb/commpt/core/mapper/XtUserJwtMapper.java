package com.tanb.commpt.core.mapper;

import com.tanb.commpt.core.po.XtUserJwt;
import com.tanb.commpt.core.po.XtUserJwtKey;
import org.apache.ibatis.annotations.*;

import java.util.concurrent.ConcurrentHashMap;

public interface XtUserJwtMapper {
    @Delete({
            "delete from XT_USER_JWT",
            "where USER_ID = #{userId,jdbcType=VARCHAR}",
            "and ACCESS_TOKEN = #{accessToken,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(XtUserJwtKey key);

    @Insert({
            "insert into XT_USER_JWT (USER_ID, ACCESS_TOKEN, ",
            "REFRESH_TOKEN, JWT_TTL, ",
            "JWT_REFRESH_TTL, JWT_REFRESH_INTERVAL, ",
            "CREATE_TIME)",
            "values (#{userId,jdbcType=VARCHAR}, #{accessToken,jdbcType=VARCHAR}, ",
            "#{refreshToken,jdbcType=VARCHAR}, #{jwtTtl,jdbcType=TIMESTAMP}, ",
            "#{jwtRefreshTtl,jdbcType=TIMESTAMP}, #{jwtRefreshInterval,jdbcType=DECIMAL}, ",
            "#{createTime,jdbcType=TIMESTAMP})"
    })
    int insert(XtUserJwt record);

    int insertSelective(XtUserJwt record);

    @Select({
            "select",
            "USER_ID, ACCESS_TOKEN, REFRESH_TOKEN, JWT_TTL, JWT_REFRESH_TTL, JWT_REFRESH_INTERVAL, ",
            "CREATE_TIME",
            "from XT_USER_JWT",
            "where USER_ID = #{userId,jdbcType=VARCHAR}",
            "and ACCESS_TOKEN = #{accessToken,jdbcType=VARCHAR}"
    })
    @ResultMap("com.tanb.commpt.core.mapper.XtUserJwtMapper.BaseResultMap")
    XtUserJwt selectByPrimaryKey(XtUserJwtKey key);

    int updateByPrimaryKeySelective(XtUserJwt record);

    @Update({
            "update XT_USER_JWT",
            "set REFRESH_TOKEN = #{refreshToken,jdbcType=VARCHAR},",
            "JWT_TTL = #{jwtTtl,jdbcType=TIMESTAMP},",
            "JWT_REFRESH_TTL = #{jwtRefreshTtl,jdbcType=TIMESTAMP},",
            "JWT_REFRESH_INTERVAL = #{jwtRefreshInterval,jdbcType=DECIMAL},",
            "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP}",
            "where USER_ID = #{userId,jdbcType=VARCHAR}",
            "and ACCESS_TOKEN = #{accessToken,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(XtUserJwt record);

    @Delete({
            "delete from XT_USER_JWT",
            "where USER_ID = #{userId,jdbcType=VARCHAR}"
    })
    int deleteByUserId(String userId);

    int insert2(XtUserJwt xtJwt);

    ConcurrentHashMap<String, String> selectTokenInfoByUserAccessToken(@Param("userId") String userId, @Param("accessToken") String accessToken);

    String selectByAccessToken(@Param("accessToken") String accessToken);

    String selectByUserAndToken(@Param("userId") String userId, @Param("accessToken") String accessToken, @Param("refreshToken") String refreshToken);
}