package com.lys.baseJava.lamber;

import com.lys.demo.entity.User;

/**
 * Created by Administrator on 2020/5/14.
 */
public class StudentSexImpl implements StudentInterface {

    @Override
    public boolean test(User user){
        return 1==user.getSex();
    }

}
