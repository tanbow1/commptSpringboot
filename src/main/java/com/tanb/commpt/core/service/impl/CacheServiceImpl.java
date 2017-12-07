package com.tanb.commpt.core.service.impl;

import com.tanb.commpt.core.dao.ICacheDao;
import com.tanb.commpt.core.service.ICacheService;
import org.springframework.stereotype.Service;

@Service("cacheService")
public class CacheServiceImpl implements ICacheService {

    private ICacheDao cacheDao;

}
