package com.tanb.commpt.core.mapper;

import com.tanb.commpt.core.po.DmIndustry;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface DmIndustryMapper {
    @Delete({
        "delete from DM_INDUSTRY",
        "where INDUSTRY_ID = #{industryId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String industryId);

    @Insert({
        "insert into DM_INDUSTRY (INDUSTRY_ID, INDUSTRY_NAME, ",
        "MLBZ, DLBZ, ZLBZ, ",
        "XLBZ, PARENT_ID, STATUS)",
        "values (#{industryId,jdbcType=VARCHAR}, #{industryName,jdbcType=VARCHAR}, ",
        "#{mlbz,jdbcType=CHAR}, #{dlbz,jdbcType=CHAR}, #{zlbz,jdbcType=CHAR}, ",
        "#{xlbz,jdbcType=CHAR}, #{parentId,jdbcType=VARCHAR}, #{status,jdbcType=CHAR})"
    })
    int insert(DmIndustry record);

    int insertSelective(DmIndustry record);

    @Select({
        "select",
        "INDUSTRY_ID, INDUSTRY_NAME, MLBZ, DLBZ, ZLBZ, XLBZ, PARENT_ID, STATUS",
        "from DM_INDUSTRY",
        "where INDUSTRY_ID = #{industryId,jdbcType=VARCHAR}"
    })
    @ResultMap("com.tanb.commpt.core.mapper.DmIndustryMapper.BaseResultMap")
    DmIndustry selectByPrimaryKey(String industryId);

    int updateByPrimaryKeySelective(DmIndustry record);

    @Update({
        "update DM_INDUSTRY",
        "set INDUSTRY_NAME = #{industryName,jdbcType=VARCHAR},",
          "MLBZ = #{mlbz,jdbcType=CHAR},",
          "DLBZ = #{dlbz,jdbcType=CHAR},",
          "ZLBZ = #{zlbz,jdbcType=CHAR},",
          "XLBZ = #{xlbz,jdbcType=CHAR},",
          "PARENT_ID = #{parentId,jdbcType=VARCHAR},",
          "STATUS = #{status,jdbcType=CHAR}",
        "where INDUSTRY_ID = #{industryId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(DmIndustry record);
}