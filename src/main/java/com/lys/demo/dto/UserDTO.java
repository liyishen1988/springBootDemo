package com.lys.demo.dto;

import com.google.common.base.Converter;
import com.lys.demo.entity.User;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;

/**
 * UserDTO
 */
public class UserDTO {

    @NotNull
    private String name;

    @NotNull
    private int age;

    @NotNull
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User convertToUser() {
        Assemble assemble = new Assemble();
        User user = assemble.convert(this);
        return user;
    }

    public UserDTO convertToDto(User user) {
        Assemble assemble = new Assemble();
        UserDTO dto = assemble.reverse().convert(user);
        return dto;
    }

    private static class Assemble extends Converter<UserDTO, User> {

        @Override
        protected User doForward(UserDTO userDTO) {
            User user = new User();
            BeanUtils.copyProperties(userDTO, user);
            return user;
        }

        /**
         * 很多业务需求的出参和入参的 DTO 对象是不同的，那么你需要更明显地告诉程序：逆向是无法调用的。
         *
         * @param user
         * @return
         */
        @Override
        protected UserDTO doBackward(User user) {
            throw new AssertionError("不支持逆向转化方法!");
        }
    }

}
