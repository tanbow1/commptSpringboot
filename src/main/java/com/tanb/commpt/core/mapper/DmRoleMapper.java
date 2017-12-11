package com.tanb.commpt.core.mapper;


import com.tanb.commpt.core.po.DmRole;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface DmRoleMapper {
    @Delete({
        "delete from T_DM_ROLE",
        "where ROLE_ID = #{roleId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String roleId);

    @Insert({
        "insert into T_DM_ROLE (ROLE_ID, ROLE_NAME, ",
        "YXBJ)",
        "values (#{roleId,jdbcType=VARCHAR}, #{roleName,jdbcType=VARCHAR}, ",
        "#{yxbj,jdbcType=CHAR})"
    })
    int insert(DmRole record);

    int insertSelective(DmRole record);

    @Select({
        "select",
        "ROLE_ID, ROLE_NAME, YXBJ",
        "from T_DM_ROLE",
        "where ROLE_ID = #{roleId,jdbcType=VARCHAR}"
    })
    @ResultMap("com.tanb.commpt.core.mapper.DmRoleMapper.BaseResultMap")
    DmRole selectByPrimaryKey(String roleId);

    @Select({
            "select",
            "ROLE_ID, ROLE_NAME, YXBJ",
            "from T_DM_ROLE",
            "where YXBJ = '1'"
    })
    @ResultMap("com.tanb.commpt.core.mapper.DmRoleMapper.BaseResultMap")
    List<DmRole> selectAll();

    int updateByPrimaryKeySelective(DmRole record);

    @Update({
        "update T_DM_ROLE",
        "set ROLE_NAME = #{roleName,jdbcType=VARCHAR},",
          "YXBJ = #{yxbj,jdbcType=CHAR}",
        "where ROLE_ID = #{roleId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(DmRole record);
}