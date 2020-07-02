package com.lys.demo.service.impl;

import com.lys.demo.dto.UserDTO;
import com.lys.demo.entity.User;
import com.lys.demo.service.DTOTransferInterface;

/**
 * 接口实现
 */
public class UserInputDTOConvertImpl implements DTOTransferInterface {
    @Override
    public User convert(Object o) {
        UserDTO dto = (UserDTO)o;

        return null;
    }

//    @Override
//    public User convert(UserDTO o) {
//        User user = new User();
//
//        return user;
//    }


}
