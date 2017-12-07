package com.tanb.commpt.core.mapper;


import com.tanb.commpt.core.po.DmLog;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface DmLogMapper {
    @Delete({
        "delete from T_DM_LOG",
        "where LOG_TYPE = #{logType,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String logType);

    @Insert({
        "insert into T_DM_LOG (LOG_TYPE, LOG_DES, ",
        "YXBJ)",
        "values (#{logType,jdbcType=VARCHAR}, #{logDes,jdbcType=VARCHAR}, ",
        "#{yxbj,jdbcType=CHAR})"
    })
    int insert(DmLog record);

    int insertSelective(DmLog record);

    @Select({
        "select",
        "LOG_TYPE, LOG_DES, YXBJ",
        "from T_DM_LOG",
        "where LOG_TYPE = #{logType,jdbcType=VARCHAR}"
    })
    @ResultMap("com.tb.commpt.mapper.DmLogMapper.BaseResultMap")
    DmLog selectByPrimaryKey(String logType);

    @Select({
            "select",
            "LOG_TYPE, LOG_DES, YXBJ",
            "from T_DM_LOG",
            "where YXBJ = '1'"
    })
    @ResultMap("com.tb.commpt.mapper.DmLogMapper.BaseResultMap")
    List<DmLog> selectAll();

    int updateByPrimaryKeySelective(DmLog record);

    @Update({
        "update T_DM_LOG",
        "set LOG_DES = #{logDes,jdbcType=VARCHAR},",
          "YXBJ = #{yxbj,jdbcType=CHAR}",
        "where LOG_TYPE = #{logType,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(DmLog record);
}