package com.tanb.commpt.core.mapper;

import com.tanb.commpt.core.po.DmCard;
import com.tanb.commpt.core.po.DmCardKey;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface DmCardMapper {
    @Delete({
        "delete from DM_CARD",
        "where CARD_ID = #{cardId,jdbcType=VARCHAR}",
          "and CARD_TYPE = #{cardType,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(DmCardKey key);

    @Insert({
        "insert into DM_CARD (CARD_ID, CARD_TYPE, ",
        "CARD_NAME, STATUS, PARENT_ID, ",
        "CARD_SIMPLE)",
        "values (#{cardId,jdbcType=VARCHAR}, #{cardType,jdbcType=CHAR}, ",
        "#{cardName,jdbcType=VARCHAR}, #{status,jdbcType=CHAR}, #{parentId,jdbcType=VARCHAR}, ",
        "#{cardSimple,jdbcType=VARCHAR})"
    })
    int insert(DmCard record);

    int insertSelective(DmCard record);

    @Select({
        "select",
        "CARD_ID, CARD_TYPE, CARD_NAME, STATUS, PARENT_ID, CARD_SIMPLE",
        "from DM_CARD",
        "where CARD_ID = #{cardId,jdbcType=VARCHAR}",
          "and CARD_TYPE = #{cardType,jdbcType=CHAR}"
    })
    @ResultMap("com.tanb.commpt.core.mapper.DmCardMapper.BaseResultMap")
    DmCard selectByPrimaryKey(DmCardKey key);

    int updateByPrimaryKeySelective(DmCard record);

    @Update({
        "update DM_CARD",
        "set CARD_NAME = #{cardName,jdbcType=VARCHAR},",
          "STATUS = #{status,jdbcType=CHAR},",
          "PARENT_ID = #{parentId,jdbcType=VARCHAR},",
          "CARD_SIMPLE = #{cardSimple,jdbcType=VARCHAR}",
        "where CARD_ID = #{cardId,jdbcType=VARCHAR}",
          "and CARD_TYPE = #{cardType,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(DmCard record);
}