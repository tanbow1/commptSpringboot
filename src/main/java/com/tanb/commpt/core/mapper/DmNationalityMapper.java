package com.tanb.commpt.core.mapper;

import com.tanb.commpt.core.po.DmNationality;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface DmNationalityMapper {
    @Delete({
        "delete from DM_NATIONALITY",
        "where NATIONALITY_ID = #{nationalityId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String nationalityId);

    @Insert({
        "insert into DM_NATIONALITY (NATIONALITY_ID, NATIONALITY_NAME_ZH, ",
        "NATIONALITY_NAME_EN, NATIONALITY_EN_TAG, ",
        "ALPHABETIC, STATUS)",
        "values (#{nationalityId,jdbcType=VARCHAR}, #{nationalityNameZh,jdbcType=VARCHAR}, ",
        "#{nationalityNameEn,jdbcType=VARCHAR}, #{nationalityEnTag,jdbcType=VARCHAR}, ",
        "#{alphabetic,jdbcType=CHAR}, #{status,jdbcType=CHAR})"
    })
    int insert(DmNationality record);

    int insertSelective(DmNationality record);

    @Select({
        "select",
        "NATIONALITY_ID, NATIONALITY_NAME_ZH, NATIONALITY_NAME_EN, NATIONALITY_EN_TAG, ",
        "ALPHABETIC, STATUS",
        "from DM_NATIONALITY",
        "where NATIONALITY_ID = #{nationalityId,jdbcType=VARCHAR}"
    })
    @ResultMap("com.tanb.commpt.core.mapper.DmNationalityMapper.BaseResultMap")
    DmNationality selectByPrimaryKey(String nationalityId);

    int updateByPrimaryKeySelective(DmNationality record);

    @Update({
        "update DM_NATIONALITY",
        "set NATIONALITY_NAME_ZH = #{nationalityNameZh,jdbcType=VARCHAR},",
          "NATIONALITY_NAME_EN = #{nationalityNameEn,jdbcType=VARCHAR},",
          "NATIONALITY_EN_TAG = #{nationalityEnTag,jdbcType=VARCHAR},",
          "ALPHABETIC = #{alphabetic,jdbcType=CHAR},",
          "STATUS = #{status,jdbcType=CHAR}",
        "where NATIONALITY_ID = #{nationalityId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(DmNationality record);

    List<DmNationality> selectGjdqList(@Param("pageStart") int pageStart, @Param("pageEnd") int pageEnd);

    @Select({"select count(*) from DM_NATIONALITY "})
    int selectGjdqCount();

    @Select({"select count(1) from DM_NATIONALITY where NATIONALITY_ID = #{nationalityId,jdbcType=VARCHAR} "})
    int selectCountByGjdqId(String gjdqId);

    int insertByBatch(List<DmNationality> gjdqList);

    @Select("SELECT *,decode(STATUS,'1','有效','0','无效','--' ) STATUS_MC FROM DM_NATIONALITY")
    List<Map<String, Object>> selectAllGjdqList();
}