package com.lys.demo.dao;


import com.lys.demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by admin on 2020/4/27.
 */
@Mapper
@Repository
public interface UserDao {

    User selectUserById(long id);

    List<User> listUsers();
}
