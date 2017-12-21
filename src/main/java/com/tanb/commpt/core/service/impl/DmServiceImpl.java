package com.tanb.commpt.core.service.impl;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tanb.commpt.core.constant.ConsCommon;
import com.tanb.commpt.core.constant.ContentType;
import com.tanb.commpt.core.exception.BizLevelException;
import com.tanb.commpt.core.mapper.DmNationalityMapper;
import com.tanb.commpt.core.mapper.DmMenuMapper;
import com.tanb.commpt.core.mapper.DmProductMapper;
import com.tanb.commpt.core.po.DmNationality;
import com.tanb.commpt.core.po.DmMenu;
import com.tanb.commpt.core.po.DmProduct;
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
    private DmNationalityMapper dmGjdqMapper;

    @Autowired
    private DmProductMapper dmProductMapper;

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
            map.put("openProduct", menu.getOpenType());
            map.put("readonly", menu.getIsEdit());
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
        int total = dmGjdqMapper.selectCount();
        if (total > 0) {
            List<DmNationality> dmGjdqList = dmGjdqMapper.selectNationalityListPagination(pageStartAndEnd[0], pageStartAndEnd[1]);
            if (null != dmGjdqList)
                jsonResponse.getData().put("dmNationalityList", dmGjdqList);
        }
        jsonResponse.getData().put("dmNationalityCount", total);

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
        JavaType javaProduct = CommonUtil.getCollectionType(ArrayList.class, DmNationality.class);
        List list = objectMapper.readValue(String.valueOf(jsonRequest.getReqData().get("records")), javaProduct);
        Iterator it = list.iterator();
        DmNationality dmGjdq;
        int deleteCount;
        List<DmNationality> errorList = new ArrayList<DmNationality>();
        while (it.hasNext()) {
            dmGjdq = (DmNationality) it.next();
            if (null != dmGjdq.getNationalityId()) {
                deleteCount = dmGjdqMapper.deleteByPrimaryKey(dmGjdq.getNationalityId());
                if (deleteCount <= 0) {
                    errorList.add(dmGjdq);
                }
            }
        }
        if (errorList.size() > 0) {
            jsonResponse.setCode(ConsCommon.WARN_CODE_017);
            jsonResponse.setMsg(errorList.size() + "条" + ConsCommon.WARN_MSG_017);
            jsonResponse.getData().put("errorList", errorList);
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
        JavaType javaProduct = CommonUtil.getCollectionType(ArrayList.class, DmNationality.class);
        List list = objectMapper.readValue(String.valueOf(jsonRequest.getReqData().get("records")), javaProduct);
        Iterator it = list.iterator();
        DmNationality dmGjdq;
        int changeCount;
        List<DmNationality> errorList = new ArrayList<DmNationality>();
        while (it.hasNext()) {
            dmGjdq = (DmNationality) it.next();
            if (null == dmGjdq.getNationalityId()) {
                //add
                changeCount = dmGjdqMapper.insertSelective(dmGjdq);
            } else {
                //update
                changeCount = dmGjdqMapper.updateByPrimaryKey(dmGjdq);
            }
            if (changeCount > 0) {
                int recordCount = dmGjdqMapper.selectCountByNationalityId(dmGjdq.getNationalityId());
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
            jsonResponse.getData().put("errorList", errorList);
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
                List<DmNationality> dmNationalityList = new ArrayList<DmNationality>();
                DmNationality dmNationality = null;
                ArrayList<String> listItem = null;
                for (int i = 0, len = list.size(); i < len; i++) {
                    dmNationality = new DmNationality();
                    listItem = list.get(i);
                    dmNationality.setNationalityId(listItem.get(0));
                    dmNationality.setNationalityNameZh(listItem.get(1));
                    dmNationality.setNationalityNameZh(listItem.get(2));
                    dmNationality.setNationalityEnSimple(listItem.get(3));
                    dmNationality.setAlphabetic(listItem.get(4));
                    dmNationality.setStatus(listItem.get(5));
                    dmNationality.setNationalityZhSimple(listItem.get(6));
                    dmNationality.setReserveDm(listItem.get(7));
                    dmNationality.setFormalDm(listItem.get(8));
                    dmNationalityList.add(dmNationality);
                }
                int insertBatchCount = dmGjdqMapper.insertByBatch(dmNationalityList);
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
        List<Map<String, Object>> dmGjdqList = dmGjdqMapper.selectAll();
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
        List<DmProduct> dmProductList =
                dmProductMapper.selectDmProductTypesByParentId(String.valueOf(jsonRequest.getReqData().get("parentId")));
        return jsonResponse;
    }

    /**
     * 获取产品类型结构树
     *
     * @param
     * @return
     * @throws IOException
     */
    @Override
    public JsonResponse getProductTypeTree(JsonRequest jsonRequest) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<ConcurrentHashMap<String, Object>> dmProductList = new ArrayList<>();
        getProductProductTreegrid(dmProductList, "1");
        Map<String, Object> dataMap = new ConcurrentHashMap<String, Object>();
        dataMap.put("rows", dmProductList);
        dataMap.put("total", dmProductList.size());
        dataMap.put("footer", new ArrayList<>());
        HttpServletResponse httpServletResponse = (HttpServletResponse) jsonRequest.getReqData().get("response");
        httpServletResponse.setContentType(ContentType.getContentType("json"));
        httpServletResponse.setCharacterEncoding("utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        out.print(objectMapper.writeValueAsString(dataMap));
        out.flush();
        return null;
    }

    private void getProductProductTreegrid(List<ConcurrentHashMap<String, Object>> dataList,
                                           String parentId) {
        List<DmProduct> dmProductList = dmProductMapper.selectDmProductTypesByParentId(parentId);
        DmProduct dmProduct = null;
        ConcurrentHashMap<String, Object> map = null;

        Iterator dmProductIterator = dmProductList.iterator();
        while (dmProductIterator.hasNext()) {
            map = new ConcurrentHashMap<String, Object>();
            dmProduct = (DmProduct) dmProductIterator.next();
            dataList.add(map);
            map.put("productId", dmProduct.getProductId());
            map.put("productName", dmProduct.getProductName());
            map.put("productDesc", null == dmProduct.getProductDesc() ? "-" : dmProduct.getProductDesc());
            map.put("status", dmProduct.getStatus());
//            map.put("state", null == dmProduct.getState() ? "0" : dmProduct.getState());
            map.put("parentId", dmProduct.getParentId());
            map.put("haschildren", null == dmProduct.getHaschildren() ? "0" : dmProduct.getHaschildren());
            map.put("sort", null == dmProduct.getSort() ? "" : dmProduct.getSort());
            if ("1".equals(dmProduct.getHaschildren())) {
                map.put("state", "closed");
                //此处state与数据库字段无关，与是否含子节点保持一致
                List<ConcurrentHashMap<String, Object>> childrenList = new ArrayList<ConcurrentHashMap<String, Object>>();
                map.put("children", childrenList);
                getProductProductTreegrid(childrenList, dmProduct.getProductId());
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
        JavaType javaProduct = CommonUtil.getCollectionType(ArrayList.class, DmProduct.class);
        List list = objectMapper.readValue(String.valueOf(jsonRequest.getReqData().get("records")), javaProduct);
        Iterator it = list.iterator();
        DmProduct dmProduct;
        int changeCount;
        List<DmProduct> errorList = new ArrayList<DmProduct>();

        while (it.hasNext()) {
            dmProduct = (DmProduct) it.next();
            if (null == dmProduct.getProductId()) {
                throw new BizLevelException(ConsCommon.WARN_MSG_021);
            } else {
                //update
                changeCount = dmProductMapper.updateByPrimaryKeySelective(dmProduct);
            }
            if (changeCount <= 0) {
                errorList.add(dmProduct);
                jsonResponse.setCode(ConsCommon.WARN_CODE_016);
                jsonResponse.setMsg(ConsCommon.WARN_MSG_016);
            }
        }
        if (errorList.size() > 0) {
            jsonResponse.getData().put("errorList", errorList);
        }
        return jsonResponse;
    }
}
