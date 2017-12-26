package com.tanb.commpt.core.mapper;

import com.tanb.commpt.core.po.XtPermission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface XtPermissionMapper {
    @Delete({
        "delete from XT_PERMISSION",
        "where PERMISSON_ID = #{permissonId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String permissonId);

    @Insert({
        "insert into XT_PERMISSION (PERMISSON_ID, PARENT_ID, ",
        "PERMISSION_DESC, URL, ",
        "OPEN_TYPE, HASCHILDREN, ",
        "STATE, IS_DEL, IS_EDIT, ",
        "STATUS, SORT, PERMISSION_NAME, ",
        "METHOD)",
        "values (#{permissonId,jdbcType=VARCHAR}, #{parentId,jdbcType=VARCHAR}, ",
        "#{permissionDesc,jdbcType=VARCHAR}, #{url,jdbcType=VARCHAR}, ",
        "#{openType,jdbcType=CHAR}, #{haschildren,jdbcType=CHAR}, ",
        "#{state,jdbcType=CHAR}, #{isDel,jdbcType=CHAR}, #{isEdit,jdbcType=CHAR}, ",
        "#{status,jdbcType=CHAR}, #{sort,jdbcType=DECIMAL}, #{permissionName,jdbcType=VARCHAR}, ",
        "#{method,jdbcType=VARCHAR})"
    })
    int insert(XtPermission record);

    int insertSelective(XtPermission record);

    @Select({
        "select",
        "PERMISSON_ID, PARENT_ID, PERMISSION_DESC, URL, OPEN_TYPE, HASCHILDREN, STATE, ",
        "IS_DEL, IS_EDIT, STATUS, SORT, PERMISSION_NAME, METHOD",
        "from XT_PERMISSION",
        "where PERMISSON_ID = #{permissonId,jdbcType=VARCHAR}"
    })
    @ResultMap("com.tanb.commpt.core.mapper.XtPermissionMapper.BaseResultMap")
    XtPermission selectByPrimaryKey(String permissonId);

    int updateByPrimaryKeySelective(XtPermission record);

    @Update({
        "update XT_PERMISSION",
        "set PARENT_ID = #{parentId,jdbcType=VARCHAR},",
          "PERMISSION_DESC = #{permissionDesc,jdbcType=VARCHAR},",
          "URL = #{url,jdbcType=VARCHAR},",
          "OPEN_TYPE = #{openType,jdbcType=CHAR},",
          "HASCHILDREN = #{haschildren,jdbcType=CHAR},",
          "STATE = #{state,jdbcType=CHAR},",
          "IS_DEL = #{isDel,jdbcType=CHAR},",
          "IS_EDIT = #{isEdit,jdbcType=CHAR},",
          "STATUS = #{status,jdbcType=CHAR},",
          "SORT = #{sort,jdbcType=DECIMAL},",
          "PERMISSION_NAME = #{permissionName,jdbcType=VARCHAR},",
          "METHOD = #{method,jdbcType=VARCHAR}",
        "where PERMISSON_ID = #{permissonId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(XtPermission record);
}