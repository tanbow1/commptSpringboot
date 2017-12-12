package com.tanb.commpt.core.mapper;

import com.tanb.commpt.core.po.DmUserPayaccount;
import com.tanb.commpt.core.po.DmUserPayaccountKey;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface DmUserPayaccountMapper {
    @Delete({
        "delete from DM_USER_PAYACCOUNT",
        "where USER_ID = #{userId,jdbcType=VARCHAR}",
          "and PAYACCOUNT = #{payaccount,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(DmUserPayaccountKey key);

    @Insert({
        "insert into DM_USER_PAYACCOUNT (USER_ID, PAYACCOUNT, ",
        "ACCOUNT_ID, ACCOUNT_TYPE, ",
        "PAY_KEY, SORT, STATUS)",
        "values (#{userId,jdbcType=VARCHAR}, #{payaccount,jdbcType=VARCHAR}, ",
        "#{accountId,jdbcType=VARCHAR}, #{accountType,jdbcType=VARCHAR}, ",
        "#{payKey,jdbcType=VARCHAR}, #{sort,jdbcType=DECIMAL}, #{status,jdbcType=CHAR})"
    })
    int insert(DmUserPayaccount record);

    int insertSelective(DmUserPayaccount record);

    @Select({
        "select",
        "USER_ID, PAYACCOUNT, ACCOUNT_ID, ACCOUNT_TYPE, PAY_KEY, SORT, STATUS",
        "from DM_USER_PAYACCOUNT",
        "where USER_ID = #{userId,jdbcType=VARCHAR}",
          "and PAYACCOUNT = #{payaccount,jdbcType=VARCHAR}"
    })
    @ResultMap("com.tanb.commpt.core.mapper.DmUserPayaccountMapper.BaseResultMap")
    DmUserPayaccount selectByPrimaryKey(DmUserPayaccountKey key);

    int updateByPrimaryKeySelective(DmUserPayaccount record);

    @Update({
        "update DM_USER_PAYACCOUNT",
        "set ACCOUNT_ID = #{accountId,jdbcType=VARCHAR},",
          "ACCOUNT_TYPE = #{accountType,jdbcType=VARCHAR},",
          "PAY_KEY = #{payKey,jdbcType=VARCHAR},",
          "SORT = #{sort,jdbcType=DECIMAL},",
          "STATUS = #{status,jdbcType=CHAR}",
        "where USER_ID = #{userId,jdbcType=VARCHAR}",
          "and PAYACCOUNT = #{payaccount,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(DmUserPayaccount record);
}