package com.tanb.commpt.core.mapper;

import com.tanb.commpt.core.po.DmArguments;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface DmArgumentsMapper {
    @Delete({
        "delete from DM_ARGUMENTS",
        "where ARG_ID = #{argId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String argId);

    @Insert({
        "insert into DM_ARGUMENTS (ARG_ID, ARG_KEY, ",
        "ARG_VAL1, ARG_VAL2, ",
        "ARG_VAL3, ARG_VAL4, ",
        "ARG_VAL5, ARG_VAL6, ",
        "ARG_VAL7, ARG_VAL8, ",
        "ARG_VAL9, ARG_DESC, ",
        "STATUS)",
        "values (#{argId,jdbcType=VARCHAR}, #{argKey,jdbcType=VARCHAR}, ",
        "#{argVal1,jdbcType=VARCHAR}, #{argVal2,jdbcType=VARCHAR}, ",
        "#{argVal3,jdbcType=VARCHAR}, #{argVal4,jdbcType=VARCHAR}, ",
        "#{argVal5,jdbcType=VARCHAR}, #{argVal6,jdbcType=VARCHAR}, ",
        "#{argVal7,jdbcType=VARCHAR}, #{argVal8,jdbcType=VARCHAR}, ",
        "#{argVal9,jdbcType=VARCHAR}, #{argDesc,jdbcType=VARCHAR}, ",
        "#{status,jdbcType=CHAR})"
    })
    int insert(DmArguments record);

    int insertSelective(DmArguments record);

    @Select({
        "select",
        "ARG_ID, ARG_KEY, ARG_VAL1, ARG_VAL2, ARG_VAL3, ARG_VAL4, ARG_VAL5, ARG_VAL6, ",
        "ARG_VAL7, ARG_VAL8, ARG_VAL9, ARG_DESC, STATUS",
        "from DM_ARGUMENTS",
        "where ARG_ID = #{argId,jdbcType=VARCHAR}"
    })
    @ResultMap("com.tanb.commpt.core.mapper.DmArgumentsMapper.BaseResultMap")
    DmArguments selectByPrimaryKey(String argId);

    int updateByPrimaryKeySelective(DmArguments record);

    @Update({
        "update DM_ARGUMENTS",
        "set ARG_KEY = #{argKey,jdbcType=VARCHAR},",
          "ARG_VAL1 = #{argVal1,jdbcType=VARCHAR},",
          "ARG_VAL2 = #{argVal2,jdbcType=VARCHAR},",
          "ARG_VAL3 = #{argVal3,jdbcType=VARCHAR},",
          "ARG_VAL4 = #{argVal4,jdbcType=VARCHAR},",
          "ARG_VAL5 = #{argVal5,jdbcType=VARCHAR},",
          "ARG_VAL6 = #{argVal6,jdbcType=VARCHAR},",
          "ARG_VAL7 = #{argVal7,jdbcType=VARCHAR},",
          "ARG_VAL8 = #{argVal8,jdbcType=VARCHAR},",
          "ARG_VAL9 = #{argVal9,jdbcType=VARCHAR},",
          "ARG_DESC = #{argDesc,jdbcType=VARCHAR},",
          "STATUS = #{status,jdbcType=CHAR}",
        "where ARG_ID = #{argId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(DmArguments record);
}