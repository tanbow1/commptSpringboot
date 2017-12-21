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
        "NATIONALITY_NAME_EN, NATIONALITY_EN_SIMPLE, ",
        "ALPHABETIC, STATUS, NATIONALITY_ZH_SIMPLE)",
        "values (#{nationalityId,jdbcType=VARCHAR}, #{nationalityNameZh,jdbcType=VARCHAR}, ",
        "#{nationalityNameEn,jdbcType=VARCHAR}, #{nationalityEnSimple,jdbcType=VARCHAR}, ",
        "#{alphabetic,jdbcType=CHAR}, #{status,jdbcType=CHAR}, #{nationalityZhSimple,jdbcType=VARCHAR})"
    })
    int insert(DmNationality record);

    int insertSelective(DmNationality record);

    @Select({
        "select",
        "NATIONALITY_ID, NATIONALITY_NAME_ZH, NATIONALITY_NAME_EN, NATIONALITY_EN_SIMPLE, ",
        "ALPHABETIC, STATUS, NATIONALITY_ZH_SIMPLE",
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
          "NATIONALITY_EN_SIMPLE = #{nationalityEnSimple,jdbcType=VARCHAR},",
          "ALPHABETIC = #{alphabetic,jdbcType=CHAR},",
          "STATUS = #{status,jdbcType=CHAR},",
          "NATIONALITY_ZH_SIMPLE = #{nationalityZhSimple,jdbcType=VARCHAR},",
            "RESERVE_DM = #{reserveDm,jdbcType=VARCHAR},",
            "FORMAL_DM = #{formalDm,jdbcType=VARCHAR}",
        "where NATIONALITY_ID = #{nationalityId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(DmNationality record);

    List<DmNationality> selectNationalityListPagination(@Param("pageStart") int pageStart, @Param("pageEnd") int pageEnd);

    @Select({"select count(1) from DM_NATIONALITY "})
    int selectCount();

    @Select({"select count(1) from DM_NATIONALITY where NATIONALITY_ID = #{nationalityId,jdbcType=VARCHAR} "})
    int selectCountByNationalityId(String nationalityId);

    int insertByBatch(List<DmNationality> gjdqList);

    @Select({"SELECT NATIONALITY_ID, NATIONALITY_NAME_ZH, NATIONALITY_NAME_EN, NATIONALITY_EN_SIMPLE," +
            "    ALPHABETIC, decode(STATUS,'1','有效','0','无效','--' ) as STATUS, NATIONALITY_ZH_SIMPLE,RESERVE_DM,FORMAL_DM  FROM DM_NATIONALITY " })
    List<Map<String, Object>> selectAll();
}