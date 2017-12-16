package com.tanb.commpt.miniapp.mapper;

import com.tanb.commpt.miniapp.po.BzWxUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface BzWxUserMapper {
    @Delete({
        "delete from BZ_WX_USER",
        "where OPEN_ID = #{openId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String openId);

    @Insert({
        "insert into BZ_WX_USER (OPEN_ID, UNION_ID, ",
        "NICK_NAME, AVATAR_URL, ",
        "GENDER, CITY, PROVINCE, ",
        "COUNTRY, LANGUAGE, ",
        "PHONE_NUMBER, COUNTRY_CODE, ",
        "PURE_PHONE_NUMBER, STATUS)",
        "values (#{openId,jdbcType=VARCHAR}, #{unionId,jdbcType=VARCHAR}, ",
        "#{nickName,jdbcType=VARCHAR}, #{avatarUrl,jdbcType=VARCHAR}, ",
        "#{gender,jdbcType=CHAR}, #{city,jdbcType=VARCHAR}, #{province,jdbcType=VARCHAR}, ",
        "#{country,jdbcType=VARCHAR}, #{language,jdbcType=VARCHAR}, ",
        "#{phoneNumber,jdbcType=VARCHAR}, #{countryCode,jdbcType=VARCHAR}, ",
        "#{purePhoneNumber,jdbcType=VARCHAR}, #{status,jdbcType=CHAR})"
    })
    int insert(BzWxUser record);

    int insertSelective(BzWxUser record);

    @Select({
        "select",
        "OPEN_ID, UNION_ID, NICK_NAME, AVATAR_URL, GENDER, CITY, PROVINCE, COUNTRY, LANGUAGE, ",
        "PHONE_NUMBER, COUNTRY_CODE, PURE_PHONE_NUMBER, STATUS",
        "from BZ_WX_USER",
        "where OPEN_ID = #{openId,jdbcType=VARCHAR}"
    })
    @ResultMap("com.tanb.commpt.miniapp.mapper.BzWxUserMapper.BaseResultMap")
    BzWxUser selectByPrimaryKey(String openId);

    int updateByPrimaryKeySelective(BzWxUser record);

    @Update({
        "update BZ_WX_USER",
        "set UNION_ID = #{unionId,jdbcType=VARCHAR},",
          "NICK_NAME = #{nickName,jdbcType=VARCHAR},",
          "AVATAR_URL = #{avatarUrl,jdbcType=VARCHAR},",
          "GENDER = #{gender,jdbcType=CHAR},",
          "CITY = #{city,jdbcType=VARCHAR},",
          "PROVINCE = #{province,jdbcType=VARCHAR},",
          "COUNTRY = #{country,jdbcType=VARCHAR},",
          "LANGUAGE = #{language,jdbcType=VARCHAR},",
          "PHONE_NUMBER = #{phoneNumber,jdbcType=VARCHAR},",
          "COUNTRY_CODE = #{countryCode,jdbcType=VARCHAR},",
          "PURE_PHONE_NUMBER = #{purePhoneNumber,jdbcType=VARCHAR},",
          "STATUS = #{status,jdbcType=CHAR}",
        "where OPEN_ID = #{openId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(BzWxUser record);
}