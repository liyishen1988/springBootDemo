package com.lys.demo.dao;


import com.lys.demo.entity.Resource;
import com.lys.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created by admin on 2020/4/27.
 */
@Mapper
@Repository
public interface ResourceDao {

    Set<Resource> getCurrentUserMenus();
}
