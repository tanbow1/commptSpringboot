package com.tanb.commpt.core.mapper;

import com.tanb.commpt.core.po.DmProduct;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface DmProductMapper {
    @Delete({
        "delete from DM_PRODUCT",
        "where PRODUCT_ID = #{productId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String productId);

    @Insert({
        "insert into DM_PRODUCT (PRODUCT_ID, PRODUCT_NAME, ",
        "PARENT_ID, PRODUCT_DESC, ",
        "HASCHILDREN, STATE, ",
        "SORT, STATUS,URL)",
        "values (#{productId,jdbcType=VARCHAR}, #{productName,jdbcType=VARCHAR}, ",
        "#{parentId,jdbcType=VARCHAR}, #{productDesc,jdbcType=VARCHAR}, ",
        "#{haschildren,jdbcType=CHAR}, #{state,jdbcType=VARCHAR}, ",
        "#{sort,jdbcType=DECIMAL}, #{status,jdbcType=CHAR},#{url,jdbcType=VARCHAR})"
    })
    int insert(DmProduct record);

    int insertSelective(DmProduct record);

    @Select({
        "select",
        "PRODUCT_ID, PRODUCT_NAME, PARENT_ID, PRODUCT_DESC, HASCHILDREN, STATE, SORT, ",
        "STATUS,URL",
        "from DM_PRODUCT",
        "where PRODUCT_ID = #{productId,jdbcType=VARCHAR}"
    })
    @ResultMap("com.tanb.commpt.core.mapper.DmProductMapper.BaseResultMap")
    DmProduct selectByPrimaryKey(String productId);

    int updateByPrimaryKeySelective(DmProduct record);

    @Update({
        "update DM_PRODUCT",
        "set PRODUCT_NAME = #{productName,jdbcType=VARCHAR},",
          "PARENT_ID = #{parentId,jdbcType=VARCHAR},",
          "PRODUCT_DESC = #{productDesc,jdbcType=VARCHAR},",
          "HASCHILDREN = #{haschildren,jdbcType=CHAR},",
          "STATE = #{state,jdbcType=VARCHAR},",
          "SORT = #{sort,jdbcType=DECIMAL},",
          "STATUS = #{status,jdbcType=CHAR},",
            "URL = #{url,jdbcType=CHAR}",
        "where PRODUCT_ID = #{productId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(DmProduct record);

    @Select("select PRODUCT_ID, PRODUCT_NAME, STATUS, PARENT_ID, PRODUCT_DESC ,HASCHILDREN,decode(STATE,'0','closed')  state,URL,sort from DM_PRODUCT order by sort")
    @ResultMap("com.tanb.commpt.core.mapper.DmProductMapper.BaseResultMap")
    List<DmProduct> selectAllDmProductTypes();

    @Select("select PRODUCT_ID, PRODUCT_NAME, STATUS, PARENT_ID, PRODUCT_DESC,HASCHILDREN,decode(STATE,'0','closed')  state,URL,sort from DM_PRODUCT where PARENT_ID = #{parentId,jdbcType=VARCHAR} order by sort")
    @ResultMap("com.tanb.commpt.core.mapper.DmProductMapper.BaseResultMap")
    List<DmProduct> selectDmProductTypesByParentId(String parentId);
}