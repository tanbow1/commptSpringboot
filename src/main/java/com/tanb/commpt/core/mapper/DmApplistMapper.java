package com.tanb.commpt.core.mapper;

import com.tanb.commpt.core.po.DmApplist;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface DmApplistMapper {
    @Delete({
        "delete from DM_APPLIST",
        "where APP_ID = #{appId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String appId);

    @Insert({
        "insert into DM_APPLIST (APP_ID, APP_NAME, ",
        "APP_DESC, APP_OWNER, ",
        "APP_STATUS, CREATE_TIME, ",
        "MODIFY_TIME, CREATE_USER, ",
        "STATUS)",
        "values (#{appId,jdbcType=VARCHAR}, #{appName,jdbcType=VARCHAR}, ",
        "#{appDesc,jdbcType=VARCHAR}, #{appOwner,jdbcType=VARCHAR}, ",
        "#{appStatus,jdbcType=CHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{modifyTime,jdbcType=TIMESTAMP}, #{createUser,jdbcType=VARCHAR}, ",
        "#{status,jdbcType=CHAR})"
    })
    int insert(DmApplist record);

    int insertSelective(DmApplist record);

    @Select({
        "select",
        "APP_ID, APP_NAME, APP_DESC, APP_OWNER, APP_STATUS, CREATE_TIME, MODIFY_TIME, ",
        "CREATE_USER, STATUS",
        "from DM_APPLIST",
        "where APP_ID = #{appId,jdbcType=VARCHAR}"
    })
    @ResultMap("com.tanb.commpt.core.mapper.DmApplistMapper.BaseResultMap")
    DmApplist selectByPrimaryKey(String appId);

    int updateByPrimaryKeySelective(DmApplist record);

    @Update({
        "update DM_APPLIST",
        "set APP_NAME = #{appName,jdbcType=VARCHAR},",
          "APP_DESC = #{appDesc,jdbcType=VARCHAR},",
          "APP_OWNER = #{appOwner,jdbcType=VARCHAR},",
          "APP_STATUS = #{appStatus,jdbcType=CHAR},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
          "MODIFY_TIME = #{modifyTime,jdbcType=TIMESTAMP},",
          "CREATE_USER = #{createUser,jdbcType=VARCHAR},",
          "STATUS = #{status,jdbcType=CHAR}",
        "where APP_ID = #{appId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(DmApplist record);
}