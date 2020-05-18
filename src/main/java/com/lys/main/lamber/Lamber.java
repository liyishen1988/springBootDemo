package com.lys.main.lamber;

import com.lys.demo.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/5/14.
 */
public class Lamber {

    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();
        userList.add(new User("peter", 18, 1));
        userList.add(new User("wangsf", 28, 2));
        userList.add(new User("joebg", 38, 1));

        List<User> filterList = new ArrayList<>();
        filterList = filterStudentByAge(userList, new StudentInterface() {
            @Override
            public boolean test(User user) {
                return 20>user.getAge();
            }
        });

        List<User> filterList2 = new ArrayList<>();
        filterList2 = filterStudentBySex(userList,new StudentSexImpl());

    }

    private static List<User> filterStudentBySex(List<User> userList, StudentSexImpl studentAge) {
        List<User> list = new ArrayList<>();
        for (User user : userList) {
            if (studentAge.test(user)) {
                list.add(user);
            }
        }
        return list;
    }

    private static List<User> filterStudentByAge(List<User> userList, StudentInterface studentAge) {
        List<User> list = new ArrayList<>();
        for (User user : userList) {
            if (studentAge.test(user)) {
                list.add(user);
            }
        }
        return list;
    }

}
