package com.tanb.commpt.core.mapper;

import com.tanb.commpt.core.po.DmLog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface DmLogMapper {
    @Delete({
        "delete from DM_LOG",
        "where LOG_TYPE = #{logType,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String logType);

    @Insert({
        "insert into DM_LOG (LOG_TYPE, LOG_DESC, ",
        "STATUS)",
        "values (#{logType,jdbcType=VARCHAR}, #{logDesc,jdbcType=VARCHAR}, ",
        "#{status,jdbcType=CHAR})"
    })
    int insert(DmLog record);

    int insertSelective(DmLog record);

    @Select({
        "select",
        "LOG_TYPE, LOG_DESC, STATUS",
        "from DM_LOG",
        "where LOG_TYPE = #{logType,jdbcType=VARCHAR}"
    })
    @ResultMap("com.tanb.commpt.core.mapper.DmLogMapper.BaseResultMap")
    DmLog selectByPrimaryKey(String logType);

    int updateByPrimaryKeySelective(DmLog record);

    @Update({
        "update DM_LOG",
        "set LOG_DESC = #{logDesc,jdbcType=VARCHAR},",
          "STATUS = #{status,jdbcType=CHAR}",
        "where LOG_TYPE = #{logType,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(DmLog record);
}