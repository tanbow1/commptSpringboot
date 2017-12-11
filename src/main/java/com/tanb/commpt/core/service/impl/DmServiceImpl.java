package com.tanb.commpt.core.service.impl;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tanb.commpt.core.constant.ConsCommon;
import com.tanb.commpt.core.constant.ContentType;
import com.tanb.commpt.core.exception.BizLevelException;
import com.tanb.commpt.core.mapper.DmGjdqMapper;
import com.tanb.commpt.core.mapper.DmMenuMapper;
import com.tanb.commpt.core.mapper.DmProductTypeMapper;
import com.tanb.commpt.core.po.DmGjdq;
import com.tanb.commpt.core.po.DmMenu;
import com.tanb.commpt.core.po.DmProductType;
import com.tanb.commpt.core.po.comm.JsonRequest;
import com.tanb.commpt.core.po.comm.JsonResponse;
import com.tanb.commpt.core.service.IDmService;
import com.tanb.commpt.core.util.CommonUtil;
import com.tanb.commpt.core.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Tanbo on 2017/8/27.
 */

/***
 *
 */
@Service("dmService")
public class DmServiceImpl implements IDmService {

    @Autowired
    private DmMenuMapper dmMenuMapper;

    @Autowired
    private DmGjdqMapper dmGjdqMapper;

    @Autowired
    private DmProductTypeMapper dmProductTypeMapper;

    ObjectMapper objectMapper = new ObjectMapper();



    @Override
    public List<DmMenu> selectMenuByPId(String parentId) {
        return dmMenuMapper.selectMenuByPId(parentId);
    }

    /**
     * 根据父节点获取菜单树
     */
    @Override
    public List<ConcurrentHashMap<String, Object>> getMenuTree(String parentId) {
        List<ConcurrentHashMap<String, Object>> dataList = new ArrayList<>();
        getGns(dataList, parentId);
        return dataList;
    }

    /**
     * 迭代菜单树
     *
     * @param dataList
     * @param parentId
     */
    private void getGns(List<ConcurrentHashMap<String, Object>> dataList,
                        String parentId) {
        List<DmMenu> menuList = dmMenuMapper.selectMenuByPId(parentId);
        DmMenu menu = null;
        ConcurrentHashMap<String, Object> map = null;

        Iterator menuIterator = menuList.iterator();
        while (menuIterator.hasNext()) {
            map = new ConcurrentHashMap<String, Object>();
            menu = (DmMenu) menuIterator.next();
            dataList.add(map);
            map.put("id", menu.getMenuId());
            map.put("text", menu.getMenuName());
            map.put("openType", menu.getOpenType());
            map.put("readonly", menu.getReadonly());
            if (!StringUtils.isEmptyOrWhitespace(menu.getUrl())) {
                map.put("url", menu.getUrl());
            } else {
                map.put("url", "");
            }
            if ("1".equals(menu.getHaschildren())) {
                if ("0".equals(menu.getState())) {
                    map.put("state", "closed");
                }
                List<ConcurrentHashMap<String, Object>> childrenList = new ArrayList<ConcurrentHashMap<String, Object>>();
                map.put("children", childrenList);
                getGns(childrenList, menu.getMenuId());
            }
        }
    }


    /**
     * 获取国籍地区列表（分页）
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public JsonResponse getGjdqListPagination(int pageNum, int pageSize) {
        JsonResponse jsonResponse = new JsonResponse();
        Integer[] pageStartAndEnd = CommonUtil.getPageStartAndEnd(pageNum, pageSize);
        int total = dmGjdqMapper.selectGjdqCount();
        if (total > 0) {
            List<DmGjdq> dmGjdqList = dmGjdqMapper.selectGjdqList(pageStartAndEnd[0], pageStartAndEnd[1]);
            if (null != dmGjdqList)
                jsonResponse.getRepData().put("gjdqList", dmGjdqList);
        }
        jsonResponse.getRepData().put("gjdqCount", total);

        return jsonResponse;
    }

    /**
     * 批量删除国籍地区
     *
     * @param jsonRequest
     * @return
     * @throws IOException
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public JsonResponse deleteGjdqBatch(JsonRequest jsonRequest) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        JavaType javaType = CommonUtil.getCollectionType(ArrayList.class, DmGjdq.class);
        List list = objectMapper.readValue(String.valueOf(jsonRequest.getReqData().get("records")), javaType);
        Iterator it = list.iterator();
        DmGjdq dmGjdq;
        int deleteCount;
        List<DmGjdq> errorList = new ArrayList<DmGjdq>();
        while (it.hasNext()) {
            dmGjdq = (DmGjdq) it.next();
            if (null != dmGjdq.getUuid()) {
                deleteCount = dmGjdqMapper.deleteByPrimaryKey(dmGjdq.getUuid());
                if (deleteCount <= 0) {
                    errorList.add(dmGjdq);
                }
            }
        }
        if (errorList.size() > 0) {
            jsonResponse.setCode(ConsCommon.WARN_CODE_017);
            jsonResponse.setMsg(errorList.size() + "条" + ConsCommon.WARN_MSG_017);
            jsonResponse.getRepData().put("errorList", errorList);
        }
        return jsonResponse;
    }

    /**
     * 批量新增/更新 国家地区
     *
     * @param jsonRequest
     * @return
     * @throws IOException
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public JsonResponse addGjdqBatch(JsonRequest jsonRequest) throws IOException, BizLevelException {
        JsonResponse jsonResponse = new JsonResponse();
        JavaType javaType = CommonUtil.getCollectionType(ArrayList.class, DmGjdq.class);
        List list = objectMapper.readValue(String.valueOf(jsonRequest.getReqData().get("records")), javaType);
        Iterator it = list.iterator();
        DmGjdq dmGjdq;
        int changeCount;
        List<DmGjdq> errorList = new ArrayList<DmGjdq>();
        while (it.hasNext()) {
            dmGjdq = (DmGjdq) it.next();
            if (null == dmGjdq.getUuid()) {
                //add
                changeCount = dmGjdqMapper.insertSelective(dmGjdq);
            } else {
                //update
                changeCount = dmGjdqMapper.updateByPrimaryKey(dmGjdq);
            }
            if (changeCount > 0) {
                int recordCount = dmGjdqMapper.selectCountByGjdqId(dmGjdq.getGjdqId());
                if (recordCount > 1) {
                    throw new BizLevelException(ConsCommon.WARN_MSG_018);
                }
            } else {
                errorList.add(dmGjdq);
                jsonResponse.setCode(ConsCommon.WARN_CODE_016);
                jsonResponse.setMsg(ConsCommon.WARN_MSG_016);
            }
        }
        if (errorList.size() > 0) {
            jsonResponse.getRepData().put("errorList", errorList);
        }
        return jsonResponse;
    }

    /**
     * 导入国家地区excel
     *
     * @param jsonRequest
     * @return
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public JsonResponse importGjdqFromExcel(JsonRequest jsonRequest, MultipartFile[] files) {
        JsonResponse jsonResponse = new JsonResponse();
        List<ArrayList<String>> list = new ArrayList<>();
        if (null != files && files.length > 0) {
            for (int i = 0, len = files.length; i < len; i++) {
                String s = files[i].getContentType();
                if (ContentType.getContentType("xlsx").equals(s)) {
                    list = ExcelUtil.readXlsx(files[i], 0, 0);
                } else if (ContentType.getContentType("xls").equals(s)) {
                    list = ExcelUtil.readXls(files[i], 0, 0);
                }
            }

            if (list.size() <= 0) {
                jsonResponse.setCode(ConsCommon.WARN_CODE_020);
                jsonResponse.setMsg(ConsCommon.WARN_MSG_020);
            } else {
                List<DmGjdq> gjdqList = new ArrayList<DmGjdq>();
                DmGjdq gjdq = null;
                ArrayList<String> listItem = null;
                for (int i = 0, len = list.size(); i < len; i++) {
                    gjdq = new DmGjdq();
                    listItem = list.get(i);
                    gjdq.setGjdqMcE(listItem.get(0));
                    gjdq.setGjdqMcZ(listItem.get(1));
                    gjdq.setGjdqMcdm(listItem.get(2));
                    gjdq.setGjdqDhdm(listItem.get(3));
                    gjdq.setSc(listItem.get(4));
                    gjdq.setGjdqId(listItem.get(5));
                    gjdqList.add(gjdq);
                }
                int insertBatchCount = dmGjdqMapper.insertByBatch(gjdqList);
                jsonResponse.setMsg(jsonResponse.getMsg() + ",本次上传记录：" + insertBatchCount + "条。");
            }
        } else {
            jsonResponse.setCode(ConsCommon.WARN_CODE_019);
            jsonResponse.setMsg(ConsCommon.WARN_MSG_019);
        }

        return jsonResponse;
    }

    /**
     * 导出国家地区excel
     *
     * @param jsonRequest
     * @return
     */
    @Override
    public JsonResponse exportGjdqToExcel(JsonRequest jsonRequest) {
        List<Map<String, Object>> dmGjdqList = dmGjdqMapper.selectAllGjdqList();
        Map<String, String> headMap = new ConcurrentHashMap<String, String>();
        headMap.put("GJDQ_MC_Z", "中文名称");
        headMap.put("GJDQ_MC_E", "英文名称");
        headMap.put("GJDQ_MCDM", "国家地区代码");
        headMap.put("GJDQ_DHDM", "电话代码");
        headMap.put("GJDQ_ID", "自定义编码");
        headMap.put("SC", "与中国时差");
        headMap.put("YXBJ", "有效标记");
        ExcelUtil.downloadExcelFile("国家地区代码", headMap, dmGjdqList, (HttpServletResponse) jsonRequest.getReqData().get("response"), null);
        return null;
    }

    @Override
    public JsonResponse selectProductTypeTreeByParentId(JsonRequest jsonRequest) {
        JsonResponse jsonResponse = new JsonResponse();
        List<DmProductType> dmProductTypeList =
                dmProductTypeMapper.selectDmProductTypesByParentId(String.valueOf(jsonRequest.getReqData().get("parentId")));
        return jsonResponse;
    }

    /**
     * 获取产品类型结构树
     *
     * @param jsonRequest
     * @return
     * @throws IOException
     */
    @Override
    public JsonResponse getProductTypeTree(JsonRequest jsonRequest) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<ConcurrentHashMap<String, Object>> dmProductTypeList = new ArrayList<>();
        getProductTypeTreegrid(dmProductTypeList, "1");
        Map<String, Object> dataMap = new ConcurrentHashMap<String, Object>();
        dataMap.put("rows", dmProductTypeList);
        dataMap.put("total", dmProductTypeList.size());
        dataMap.put("footer", new ArrayList<>());
        HttpServletResponse httpServletResponse = (HttpServletResponse) jsonRequest.getReqData().get("response");
        httpServletResponse.setContentType(ContentType.getContentType("json"));
        httpServletResponse.setCharacterEncoding("utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        out.print(objectMapper.writeValueAsString(dataMap));
        out.flush();
        return null;
    }

    private void getProductTypeTreegrid(List<ConcurrentHashMap<String, Object>> dataList,
                                        String parentId) {
        List<DmProductType> dmProductTypeList = dmProductTypeMapper.selectDmProductTypesByParentId(parentId);
        DmProductType dmProductType = null;
        ConcurrentHashMap<String, Object> map = null;

        Iterator dmProductTypeIterator = dmProductTypeList.iterator();
        while (dmProductTypeIterator.hasNext()) {
            map = new ConcurrentHashMap<String, Object>();
            dmProductType = (DmProductType) dmProductTypeIterator.next();
            dataList.add(map);
            map.put("typeId", dmProductType.getTypeId());
            map.put("typeName", dmProductType.getTypeName());
            map.put("typeDesc", null == dmProductType.getTypeDesc() ? "--" : dmProductType.getTypeDesc());
            map.put("yxbj", dmProductType.getYxbj());
            //   map.put("state", null == dmProductType.getState() ? "" : dmProductType.getState());
            map.put("pId", dmProductType.getpId());
            map.put("haschildren", dmProductType.getHaschildren());
            if ("1".equals(dmProductType.getHaschildren())) {
                map.put("state", "closed");
                //此处state与数据库字段无关，与是否含子节点保持一致
                List<ConcurrentHashMap<String, Object>> childrenList = new ArrayList<ConcurrentHashMap<String, Object>>();
                map.put("children", childrenList);
                getProductTypeTreegrid(childrenList, dmProductType.getTypeId());
            }
        }
    }

    /**
     * 批量更新商品分类
     *
     * @param jsonRequest
     * @return
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public JsonResponse updateSpflBatch(JsonRequest jsonRequest) throws IOException, BizLevelException {
        JsonResponse jsonResponse = new JsonResponse();
        JavaType javaType = CommonUtil.getCollectionType(ArrayList.class, DmProductType.class);
        List list = objectMapper.readValue(String.valueOf(jsonRequest.getReqData().get("records")), javaType);
        Iterator it = list.iterator();
        DmProductType dmProductType;
        int changeCount;
        List<DmProductType> errorList = new ArrayList<DmProductType>();

        while (it.hasNext()) {
            dmProductType = (DmProductType) it.next();
            if (null == dmProductType.getTypeId()) {
                throw new BizLevelException(ConsCommon.WARN_MSG_021);
            } else {
                //update
                changeCount = dmProductTypeMapper.updateByPrimaryKeySelective(dmProductType);
            }
            if (changeCount <= 0) {
                errorList.add(dmProductType);
                jsonResponse.setCode(ConsCommon.WARN_CODE_016);
                jsonResponse.setMsg(ConsCommon.WARN_MSG_016);
            }
        }
        if (errorList.size() > 0) {
            jsonResponse.getRepData().put("errorList", errorList);
        }
        return jsonResponse;
    }
}
