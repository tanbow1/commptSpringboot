package com.tanb.commpt.core.mapper;

import com.tanb.commpt.core.po.XtUserRole;
import com.tanb.commpt.core.po.XtUserRoleKey;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface XtUserRoleMapper  {
    @Delete({
        "delete from XT_USER_ROLE",
        "where USER_ID = #{userId,jdbcType=VARCHAR}",
          "and ROLE_ID = #{roleId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(XtUserRoleKey key);

    @Insert({
        "insert into XT_USER_ROLE (USER_ID, ROLE_ID, ",
        "STATUS)",
        "values (#{userId,jdbcType=VARCHAR}, #{roleId,jdbcType=VARCHAR}, ",
        "#{status,jdbcType=CHAR})"
    })
    int insert(XtUserRole record);

    int insertSelective(XtUserRole record);

    @Select({
        "select",
        "USER_ID, ROLE_ID, YXBJ",
        "from XT_USER_ROLE",
        "where USER_ID = #{userId,jdbcType=VARCHAR}",
          "and ROLE_ID = #{roleId,jdbcType=VARCHAR}"
    })
    @ResultMap("com.tanb.commpt.core.mapper.XtUserRoleMapper.BaseResultMap")
    XtUserRole selectByPrimaryKey(XtUserRoleKey key);

    int updateByPrimaryKeySelective(XtUserRole record);

    @Update({
        "update XT_USER_ROLE",
        "set YXBJ = #{yxbj,jdbcType=CHAR}",
        "where USER_ID = #{userId,jdbcType=VARCHAR}",
          "and ROLE_ID = #{roleId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(XtUserRole record);

    List<Map<String,String>> selectRolePermissionByUserId(String userId);
}