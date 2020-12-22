package com.lys.demo.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lys.demo.dto.UserDTO;
import com.lys.demo.entity.User;
import com.lys.demo.service.DTOTransferInterface;
import com.lys.demo.service.UserService;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *  用于测试后台接口数据是否准确
 */
@RequestMapping("/user/api")
@RestController
public class UserApi {

    @Autowired
    private UserService userService;

    DTOTransferInterface dtoConvertService;

    final DateTime DISTRIBUTION_TIME_SPLIT_TIME = new DateTime().withTime(15, 0, 0, 0);

    @PostMapping
    public void addUser(@Valid UserDTO userDTO) {
        User user = userDTO.convertToUser();
        List<String> list2 = Lists.newArrayList();
        HashMap<String, Object> map = Maps.newHashMap();
        userService.addUser(user);
    }

    private User converUserDTO(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        return user;
    }

    private Date calculateDistributionTimeByOrderCreateTime(Date createOrderTime) {
        DateTime dateTime = new DateTime(createOrderTime);
        Date tomorrow = dateTime.plusDays(1).toDate();
        Date afterTomorrow = dateTime.plusDays(2).toDate();

        return dateTime.isAfter(DISTRIBUTION_TIME_SPLIT_TIME) ? wrapDistributionTime(afterTomorrow) : wrapDistributionTime(tomorrow);
    }

    private Date wrapDistributionTime(Date distributionTime) {
        DateTime currentDistributionDateTime = new DateTime(distributionTime);
        DateTime plusOneDay = currentDistributionDateTime.plusDays(1);

        boolean isSunday = (DateTimeConstants.SUNDAY == currentDistributionDateTime.getDayOfWeek());
        return isSunday ? plusOneDay.toDate() : currentDistributionDateTime.toDate();
    }

}
