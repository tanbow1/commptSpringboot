package com.tanb.commpt.core.service;

import com.tanb.commpt.core.exception.BizLevelException;
import com.tanb.commpt.core.po.DmMenu;
import com.tanb.commpt.core.po.comm.JsonRequest;
import com.tanb.commpt.core.po.comm.JsonResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


public interface IDmService {

    //dmAccount


    //dmMenu
    List<DmMenu> selectMenuByPId(String parentId);

    List<ConcurrentHashMap<String, Object>> getMenuTree(String parentId);

    //dmGjdq
    JsonResponse getGjdqListPagination(int pageStart, int pageEnd);

    JsonResponse deleteGjdqBatch(JsonRequest jsonRequest) throws IOException;

    JsonResponse addGjdqBatch(JsonRequest jsonRequest) throws IOException, BizLevelException;

    JsonResponse importGjdqFromExcel(JsonRequest jsonRequest, MultipartFile[] files);

    JsonResponse exportGjdqToExcel(JsonRequest jsonRequest);

    //dmProductType
    JsonResponse selectProductTypeTreeByParentId(JsonRequest jsonRequest);

    JsonResponse getProductTypeTree(JsonRequest jsonRequest) throws IOException;

    JsonResponse updateSpflBatch(JsonRequest jsonRequest) throws IOException, BizLevelException;

}
