package com.tanb.commpt.core.mapper;

import com.tanb.commpt.core.po.DmLocation;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface DmLocationMapper {
    @Delete({
        "delete from DM_LOCATION",
        "where LOCATION_ID = #{locationId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String locationId);

    @Insert({
        "insert into DM_LOCATION (LOCATION_ID, LOCATION_NAME, ",
        "LOCATION_SIMPLE, ALPHABETIC, ",
        "STATUS, PARENT_ID, TYPE_ID)",
        "values (#{locationId,jdbcType=VARCHAR}, #{locationName,jdbcType=VARCHAR}, ",
        "#{locationSimple,jdbcType=VARCHAR}, #{alphabetic,jdbcType=CHAR}, ",
        "#{status,jdbcType=CHAR}, #{parentId,jdbcType=VARCHAR}, #{typeId,jdbcType=VARCHAR})"
    })
    int insert(DmLocation record);

    int insertSelective(DmLocation record);

    @Select({
        "select",
        "LOCATION_ID, LOCATION_NAME, LOCATION_SIMPLE, ALPHABETIC, STATUS, PARENT_ID, ",
        "TYPE_ID",
        "from DM_LOCATION",
        "where LOCATION_ID = #{locationId,jdbcType=VARCHAR}"
    })
    @ResultMap("com.tanb.commpt.core.mapper.DmLocationMapper.BaseResultMap")
    DmLocation selectByPrimaryKey(String locationId);

    int updateByPrimaryKeySelective(DmLocation record);

    @Update({
        "update DM_LOCATION",
        "set LOCATION_NAME = #{locationName,jdbcType=VARCHAR},",
          "LOCATION_SIMPLE = #{locationSimple,jdbcType=VARCHAR},",
          "ALPHABETIC = #{alphabetic,jdbcType=CHAR},",
          "STATUS = #{status,jdbcType=CHAR},",
          "PARENT_ID = #{parentId,jdbcType=VARCHAR},",
          "TYPE_ID = #{typeId,jdbcType=VARCHAR}",
        "where LOCATION_ID = #{locationId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(DmLocation record);
}