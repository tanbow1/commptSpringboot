package com.tanb.commpt.core.mapper;

import com.tanb.commpt.core.po.xtRoleMenu;
import com.tanb.commpt.core.po.xtRoleMenuKey;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface xtRoleMenuMapper {
    @Delete({
        "delete from XT_ROLE_MENU",
        "where ROLE_ID = #{roleId,jdbcType=VARCHAR}",
          "and MENU_ID = #{menuId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(xtRoleMenuKey key);

    @Insert({
        "insert into XT_ROLE_MENU (ROLE_ID, MENU_ID, ",
        "STATUS)",
        "values (#{roleId,jdbcType=VARCHAR}, #{menuId,jdbcType=VARCHAR}, ",
        "#{status,jdbcType=CHAR})"
    })
    int insert(xtRoleMenu record);

    int insertSelective(xtRoleMenu record);

    @Select({
        "select",
        "ROLE_ID, MENU_ID, STATUS",
        "from XT_ROLE_MENU",
        "where ROLE_ID = #{roleId,jdbcType=VARCHAR}",
          "and MENU_ID = #{menuId,jdbcType=VARCHAR}"
    })
    @ResultMap("com.tanb.commpt.core.mapper.xtRoleMenuMapper.BaseResultMap")
    xtRoleMenu selectByPrimaryKey(xtRoleMenuKey key);

    int updateByPrimaryKeySelective(xtRoleMenu record);

    @Update({
        "update XT_ROLE_MENU",
        "set STATUS = #{status,jdbcType=CHAR}",
        "where ROLE_ID = #{roleId,jdbcType=VARCHAR}",
          "and MENU_ID = #{menuId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(xtRoleMenu record);
}