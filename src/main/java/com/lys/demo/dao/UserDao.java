package com.lys.demo.dao;


import com.lys.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {

    User selectUserById(long id);

    List<User> listUsers();

    void addUser(User user);

    User selectByNameAndPassword(@Param("name") String name, @Param("password") String password);
}
