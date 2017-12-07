package com.tanb.commpt.core.mapper;

import com.tanb.commpt.core.po.DmZjlx;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface DmZjlxMapper {
    @Delete({
        "delete from T_DM_ZJLX",
        "where CARD_TYPE = #{cardType,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String cardType);

    @Insert({
        "insert into T_DM_ZJLX (CARD_TYPE, CARD_NAME, ",
        "YXBJ)",
        "values (#{cardType,jdbcType=VARCHAR}, #{cardName,jdbcType=VARCHAR}, ",
        "#{yxbj,jdbcType=CHAR})"
    })
    int insert(DmZjlx record);

    int insertSelective(DmZjlx record);

    @Select({
        "select",
        "CARD_TYPE, CARD_NAME, YXBJ",
        "from T_DM_ZJLX",
        "where CARD_TYPE = #{cardType,jdbcType=VARCHAR}"
    })
    @ResultMap("com.tanb.commpt.core.mapper.DmZjlxMapper.BaseResultMap")
    DmZjlx selectByPrimaryKey(String cardType);

    @Select({
            "select",
            "CARD_TYPE, CARD_NAME, YXBJ",
            "from T_DM_ZJLX",
            "where YXBJ = '1'"
    })
    @ResultMap("com.tanb.commpt.core.mapper.DmZjlxMapper.BaseResultMap")
    List<DmZjlx> selectAll();

    int updateByPrimaryKeySelective(DmZjlx record);

    @Update({
        "update T_DM_ZJLX",
        "set CARD_NAME = #{cardName,jdbcType=VARCHAR},",
          "YXBJ = #{yxbj,jdbcType=CHAR}",
        "where CARD_TYPE = #{cardType,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(DmZjlx record);
}