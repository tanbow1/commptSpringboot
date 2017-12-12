package com.tanb.commpt.core.mapper;

import com.tanb.commpt.core.po.XtRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface XtRoleMapper {
    @Delete({
        "delete from XT_ROLE",
        "where ROLE_ID = #{roleId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String roleId);

    @Insert({
        "insert into XT_ROLE (ROLE_ID, ROLE_NAME, ",
        "STATUS)",
        "values (#{roleId,jdbcType=VARCHAR}, #{roleName,jdbcType=VARCHAR}, ",
        "#{status,jdbcType=CHAR})"
    })
    int insert(XtRole record);

    int insertSelective(XtRole record);

    @Select({
        "select",
        "ROLE_ID, ROLE_NAME, STATUS",
        "from XT_ROLE",
        "where ROLE_ID = #{roleId,jdbcType=VARCHAR}"
    })
    @ResultMap("com.tanb.commpt.core.mapper.XtRoleMapper.BaseResultMap")
    XtRole selectByPrimaryKey(String roleId);

    int updateByPrimaryKeySelective(XtRole record);

    @Update({
        "update XT_ROLE",
        "set ROLE_NAME = #{roleName,jdbcType=VARCHAR},",
          "STATUS = #{status,jdbcType=CHAR}",
        "where ROLE_ID = #{roleId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(XtRole record);
}