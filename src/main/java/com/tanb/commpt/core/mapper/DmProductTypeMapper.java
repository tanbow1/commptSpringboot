package com.tanb.commpt.core.mapper;

import com.tanb.commpt.core.po.DmProductType;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface DmProductTypeMapper {
    @Delete({
            "delete from T_DM_PRODUCTTYPE",
            "where TYPE_ID = #{typeId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String typeId);

    @Insert({
            "insert into T_DM_PRODUCTTYPE (TYPE_ID, TYPE_NAME, ",
            "YXBJ, P_ID, TYPE_DESC, ",
            "PX,HASCHILDREN,STATE)",
            "values (#{typeId,jdbcType=VARCHAR}, #{typeName,jdbcType=VARCHAR}, ",
            "#{yxbj,jdbcType=CHAR}, #{pId,jdbcType=VARCHAR}, #{typeDesc,jdbcType=VARCHAR}, ",
            "#{px,jdbcType=DECIMAL},#{haschildren,jdbcType=VARCHAR},#{state,jdbcType=VARCHAR})"
    })
    int insert(DmProductType record);

    int insertSelective(DmProductType record);

    @Select({
            "select",
            "TYPE_ID, TYPE_NAME, YXBJ, P_ID, TYPE_DESC, PX,HASCHILDREN,STATE",
            "from T_DM_PRODUCTTYPE",
            "where TYPE_ID = #{typeId,jdbcType=VARCHAR}"
    })
    @ResultMap("com.tb.commpt.mapper.DmProductTypeMapper.BaseResultMap")
    DmProductType selectByPrimaryKey(String typeId);

    int updateByPrimaryKeySelective(DmProductType record);

    @Update({
            "update T_DM_PRODUCTTYPE",
            "set TYPE_NAME = #{typeName,jdbcType=VARCHAR},",
            "YXBJ = #{yxbj,jdbcType=CHAR},",
            "P_ID = #{pId,jdbcType=VARCHAR},",
            "TYPE_DESC = #{typeDesc,jdbcType=VARCHAR},",
            "PX = #{px,jdbcType=DECIMAL},HASCHILDREN = #{haschildren,jdbcType=VARCHAR},STATE = #{state,jdbcType=VARCHAR}",
            "where TYPE_ID = #{typeId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(DmProductType record);

    @Select("select TYPE_ID, TYPE_NAME, YXBJ, P_ID, TYPE_DESC ,HASCHILDREN,decode(STATE,'0','closed')  state from T_DM_PRODUCTTYPE order by PX")
    @ResultMap("com.tb.commpt.mapper.DmProductTypeMapper.BaseResultMap")
    List<DmProductType> selectAllDmProductTypes();

    @Select("select TYPE_ID, TYPE_NAME, YXBJ, P_ID, TYPE_DESC,HASCHILDREN,decode(STATE,'0','closed')  state from T_DM_PRODUCTTYPE where P_ID = #{parentId,jdbcType=VARCHAR} order by PX")
    @ResultMap("com.tb.commpt.mapper.DmProductTypeMapper.BaseResultMap")
    List<DmProductType> selectDmProductTypesByParentId(String parentId);
}