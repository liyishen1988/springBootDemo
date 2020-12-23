package com.lys.demo.service.impl;

import com.lys.demo.dao.ResourceDao;
import com.lys.demo.entity.Resource;
import com.lys.demo.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service("ResourceService")
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    ResourceDao resourceDao;

    @Override
    public Set<Resource> getCurrentUserMenus() {

        return resourceDao.getCurrentUserMenus();
    }
}
