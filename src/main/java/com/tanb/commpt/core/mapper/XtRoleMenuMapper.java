package com.tanb.commpt.core.mapper;

import com.tanb.commpt.core.po.DmMenu;
import com.tanb.commpt.core.po.XtRoleMenu;
import com.tanb.commpt.core.po.XtRoleMenuKey;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface XtRoleMenuMapper {
    @Delete({
            "delete from XT_ROLE_MENU",
            "where ROLE_ID = #{roleId,jdbcType=VARCHAR}",
            "and MENU_ID = #{menuId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(XtRoleMenuKey key);

    @Insert({
            "insert into XT_ROLE_MENU (ROLE_ID, MENU_ID, ",
            "STATUS)",
            "values (#{roleId,jdbcType=VARCHAR}, #{menuId,jdbcType=VARCHAR}, ",
            "#{status,jdbcType=CHAR})"
    })
    int insert(XtRoleMenu record);

    int insertSelective(XtRoleMenu record);

    @Select({
            "select",
            "ROLE_ID, MENU_ID, STATUS",
            "from XT_ROLE_MENU",
            "where ROLE_ID = #{roleId,jdbcType=VARCHAR}",
            "and MENU_ID = #{menuId,jdbcType=VARCHAR}"
    })
    @ResultMap("com.tanb.commpt.core.mapper.XtRoleMenuMapper.BaseResultMap")
    XtRoleMenu selectByPrimaryKey(XtRoleMenuKey key);

    int updateByPrimaryKeySelective(XtRoleMenu record);

    @Update({
            "update XT_ROLE_MENU",
            "set STATUS = #{status,jdbcType=CHAR}",
            "where ROLE_ID = #{roleId,jdbcType=VARCHAR}",
            "and MENU_ID = #{menuId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(XtRoleMenu record);

    List<DmMenu> selectMenuByPermissionName(@Param("permissionName") String permissionName);
}