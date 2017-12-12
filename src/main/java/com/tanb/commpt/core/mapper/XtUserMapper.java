package com.tanb.commpt.core.mapper;

import com.tanb.commpt.core.po.XtUser;
import org.apache.ibatis.annotations.*;

public interface XtUserMapper  {
    @Delete({
        "delete from XT_USER",
        "where USER_ID = #{userId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String userId);

    @Insert({
        "insert into XT_USER (USER_ID, USER_NAME, ",
        "USER_ACCOUNT, REAL_NAME, ",
        "SEX, MOBILE, BIRTHDAY, ",
        "NATIONALITY_ID, NATIONALITY, ",
        "CARD_ID, CARD_TYPE, ",
        "CARD_NUMBER, ADDRESS, ",
        "AVATAR, REGIST_TIME, ",
        "PASSWORD, STATUS)",
        "values (#{userId,jdbcType=VARCHAR}, #{userName,jdbcType=VARCHAR}, ",
        "#{userAccount,jdbcType=VARCHAR}, #{realName,jdbcType=VARCHAR}, ",
        "#{sex,jdbcType=CHAR}, #{mobile,jdbcType=VARCHAR}, #{birthday,jdbcType=VARCHAR}, ",
        "#{nationalityId,jdbcType=VARCHAR}, #{nationality,jdbcType=VARCHAR}, ",
        "#{cardId,jdbcType=VARCHAR}, #{cardType,jdbcType=VARCHAR}, ",
        "#{cardNumber,jdbcType=VARCHAR}, #{address,jdbcType=VARCHAR}, ",
        "#{avatar,jdbcType=VARCHAR}, #{registTime,jdbcType=TIMESTAMP}, ",
        "#{password,jdbcType=VARCHAR}, #{status,jdbcType=CHAR})"
    })
    int insert(XtUser record);

    int insertSelective(XtUser record);

    XtUser selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(XtUser record);

    @Update({
        "update XT_USER",
        "set USER_NAME = #{userName,jdbcType=VARCHAR},",
          "USER_ACCOUNT = #{userAccount,jdbcType=VARCHAR},",
          "REAL_NAME = #{realName,jdbcType=VARCHAR},",
          "SEX = #{sex,jdbcType=CHAR},",
          "MOBILE = #{mobile,jdbcType=VARCHAR},",
          "BIRTHDAY = #{birthday,jdbcType=VARCHAR},",
          "NATIONALITY_ID = #{nationalityId,jdbcType=VARCHAR},",
          "NATIONALITY = #{nationality,jdbcType=VARCHAR},",
          "CARD_ID = #{cardId,jdbcType=VARCHAR},",
          "CARD_TYPE = #{cardType,jdbcType=VARCHAR},",
          "CARD_NUMBER = #{cardNumber,jdbcType=VARCHAR},",
          "ADDRESS = #{address,jdbcType=VARCHAR},",
          "AVATAR = #{avatar,jdbcType=VARCHAR},",
          "REGIST_TIME = #{registTime,jdbcType=TIMESTAMP},",
          "PASSWORD = #{password,jdbcType=VARCHAR},",
          "STATUS = #{status,jdbcType=CHAR}",
        "where USER_ID = #{userId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(XtUser record);

    int insert2(XtUser xtUser);

    XtUser selectExistsUser(@Param("username") String username);
}