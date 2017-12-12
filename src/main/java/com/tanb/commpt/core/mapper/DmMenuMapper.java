package com.tanb.commpt.core.mapper;

import com.tanb.commpt.core.po.DmMenu;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface DmMenuMapper {
    @Delete({
            "delete from T_DM_MENU",
            "where MENU_ID = #{menuId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String menuId);

    @Insert({
            "insert into T_DM_MENU (MENU_ID, PARENT_ID, ",
            "MENU_NAME, URL, YXBJ, ",
            "OPEN_TYPE)",
            "values (#{menuId,jdbcType=VARCHAR}, #{parentId,jdbcType=VARCHAR}, ",
            "#{menuName,jdbcType=VARCHAR}, #{url,jdbcType=VARCHAR}, #{yxbj,jdbcType=CHAR}, ",
            "#{openType,jdbcType=CHAR})"
    })
    int insert(DmMenu record);

    int insertSelective(DmMenu record);

    DmMenu selectByPrimaryKey(String menuId);

    @Select({
            "select",
            "MENU_ID, PARENT_ID, MENU_NAME, URL, YXBJ, OPEN_TYPE",
            "from T_DM_MENU",
            "where YXBJ = '1'"
    })
    @ResultMap("com.tanb.commpt.core.mapper.DmMenuMapper.BaseResultMap")
    List<DmMenu> selectAll();

    int updateByPrimaryKeySelective(DmMenu record);

    @Update({
            "update T_DM_MENU",
            "set PARENT_ID = #{parentId,jdbcType=VARCHAR},",
            "MENU_NAME = #{menuName,jdbcType=VARCHAR},",
            "URL = #{url,jdbcType=VARCHAR},",
            "YXBJ = #{yxbj,jdbcType=CHAR},",
            "OPEN_TYPE = #{openType,jdbcType=CHAR}",
            "where MENU_ID = #{menuId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(DmMenu record);

    List<DmMenu> selectMenuByPId(String parentId);

}