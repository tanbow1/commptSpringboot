package com.tanb.commpt.core.mapper;

import com.tanb.commpt.core.po.DmUserLocation;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface DmUserLocationMapper {
    @Delete({
        "delete from DM_USER_LOCATION",
        "where USER_ID = #{userId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String userId);

    @Insert({
        "insert into DM_USER_LOCATION (USER_ID, LOCATION, ",
        "COUNTRY, PROVINCE, ",
        "CITY, STREET, PHONE, ",
        "RECIPIENTS, STATUS)",
        "values (#{userId,jdbcType=VARCHAR}, #{location,jdbcType=VARCHAR}, ",
        "#{country,jdbcType=VARCHAR}, #{province,jdbcType=VARCHAR}, ",
        "#{city,jdbcType=VARCHAR}, #{street,jdbcType=VARCHAR}, #{phone,jdbcType=VARCHAR}, ",
        "#{recipients,jdbcType=VARCHAR}, #{status,jdbcType=CHAR})"
    })
    int insert(DmUserLocation record);

    int insertSelective(DmUserLocation record);

    @Select({
        "select",
        "USER_ID, LOCATION, COUNTRY, PROVINCE, CITY, STREET, PHONE, RECIPIENTS, STATUS",
        "from DM_USER_LOCATION",
        "where USER_ID = #{userId,jdbcType=VARCHAR}"
    })
    @ResultMap("com.tanb.commpt.core.mapper.DmUserLocationMapper.BaseResultMap")
    DmUserLocation selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(DmUserLocation record);

    @Update({
        "update DM_USER_LOCATION",
        "set LOCATION = #{location,jdbcType=VARCHAR},",
          "COUNTRY = #{country,jdbcType=VARCHAR},",
          "PROVINCE = #{province,jdbcType=VARCHAR},",
          "CITY = #{city,jdbcType=VARCHAR},",
          "STREET = #{street,jdbcType=VARCHAR},",
          "PHONE = #{phone,jdbcType=VARCHAR},",
          "RECIPIENTS = #{recipients,jdbcType=VARCHAR},",
          "STATUS = #{status,jdbcType=CHAR}",
        "where USER_ID = #{userId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(DmUserLocation record);
}