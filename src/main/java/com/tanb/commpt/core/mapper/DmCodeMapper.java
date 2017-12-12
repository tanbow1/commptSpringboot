package com.tanb.commpt.core.mapper;

import com.tanb.commpt.core.po.DmCode;
import com.tanb.commpt.core.po.DmCodeKey;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface DmCodeMapper {
    @Delete({
        "delete from DM_CODE",
        "where CODE = #{code,jdbcType=VARCHAR}",
          "and CODE_TYPE = #{codeType,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(DmCodeKey key);

    @Insert({
        "insert into DM_CODE (CODE, CODE_TYPE, ",
        "CODE_NAME, CODE_DESC, ",
        "CODE_STATUS)",
        "values (#{code,jdbcType=VARCHAR}, #{codeType,jdbcType=VARCHAR}, ",
        "#{codeName,jdbcType=VARCHAR}, #{codeDesc,jdbcType=VARCHAR}, ",
        "#{codeStatus,jdbcType=CHAR})"
    })
    int insert(DmCode record);

    int insertSelective(DmCode record);

    @Select({
        "select",
        "CODE, CODE_TYPE, CODE_NAME, CODE_DESC, CODE_STATUS",
        "from DM_CODE",
        "where CODE = #{code,jdbcType=VARCHAR}",
          "and CODE_TYPE = #{codeType,jdbcType=VARCHAR}"
    })
    @ResultMap("com.tanb.commpt.core.mapper.DmCodeMapper.BaseResultMap")
    DmCode selectByPrimaryKey(DmCodeKey key);

    int updateByPrimaryKeySelective(DmCode record);

    @Update({
        "update DM_CODE",
        "set CODE_NAME = #{codeName,jdbcType=VARCHAR},",
          "CODE_DESC = #{codeDesc,jdbcType=VARCHAR},",
          "CODE_STATUS = #{codeStatus,jdbcType=CHAR}",
        "where CODE = #{code,jdbcType=VARCHAR}",
          "and CODE_TYPE = #{codeType,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(DmCode record);
}