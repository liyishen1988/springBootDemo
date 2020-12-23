package com.lys.demo.service;

import com.lys.demo.entity.Resource;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Administrator on 2020/12/23.
 */
public interface ResourceService {

    Set<Resource> getCurrentUserMenus();

}
