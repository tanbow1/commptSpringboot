package com.tanb.commpt.core.mapper;

import com.tanb.commpt.core.po.DmMenu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface DmMenuMapper {
    @Delete({
        "delete from DM_MENU",
        "where MENU_ID = #{menuId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String menuId);

    @Insert({
        "insert into DM_MENU (MENU_ID, PARENT_ID, ",
        "MENU_NAME, URL, OPEN_TYPE, ",
        "HASCHILDREN, STATE, IS_DEL, ",
        "IS_EDIT, STATUS)",
        "values (#{menuId,jdbcType=VARCHAR}, #{parentId,jdbcType=VARCHAR}, ",
        "#{menuName,jdbcType=VARCHAR}, #{url,jdbcType=VARCHAR}, #{openType,jdbcType=CHAR}, ",
        "#{haschildren,jdbcType=CHAR}, #{state,jdbcType=CHAR}, #{isDel,jdbcType=CHAR}, ",
        "#{isEdit,jdbcType=CHAR}, #{status,jdbcType=CHAR})"
    })
    int insert(DmMenu record);

    int insertSelective(DmMenu record);

    @Select({
        "select",
        "MENU_ID, PARENT_ID, MENU_NAME, URL, OPEN_TYPE, HASCHILDREN, STATE, IS_DEL, IS_EDIT, ",
        "STATUS",
        "from DM_MENU",
        "where MENU_ID = #{menuId,jdbcType=VARCHAR}"
    })
    @ResultMap("com.tanb.commpt.core.mapper.DmMenuMapper.BaseResultMap")
    DmMenu selectByPrimaryKey(String menuId);

    int updateByPrimaryKeySelective(DmMenu record);

    @Update({
        "update DM_MENU",
        "set PARENT_ID = #{parentId,jdbcType=VARCHAR},",
          "MENU_NAME = #{menuName,jdbcType=VARCHAR},",
          "URL = #{url,jdbcType=VARCHAR},",
          "OPEN_TYPE = #{openType,jdbcType=CHAR},",
          "HASCHILDREN = #{haschildren,jdbcType=CHAR},",
          "STATE = #{state,jdbcType=CHAR},",
          "IS_DEL = #{isDel,jdbcType=CHAR},",
          "IS_EDIT = #{isEdit,jdbcType=CHAR},",
          "STATUS = #{status,jdbcType=CHAR}",
        "where MENU_ID = #{menuId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(DmMenu record);

    List<DmMenu> selectMenuByPId(String parentId);
}