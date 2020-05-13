package com.lys.main.jsckJson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lys.demo.model.User;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * JackJson 的使用
 * <p>
 * jackson-core-2.2.3.jar（核心jar包）
 * jackson-annotations-2.2.3.jar（该包提供Json注解支持）
 * jackson-databind-2.2.3.jar
 */
public class JackJson {

    /**
     * ObjectMapper是JSON操作的核心，Jackson的所有JSON操作都是在ObjectMapper中实现。
     * ObjectMapper有多个JSON序列化的方法，可以把JSON字符串保存File、OutputStream等不同的介质中。
     * writeValue(File arg0, Object arg1)把arg1转成json序列，并保存到arg0文件中。
     * writeValue(OutputStream arg0, Object arg1)把arg1转成json序列，并保存到arg0输出流中。
     * writeValueAsBytes(Object arg0)把arg0转成json序列，并把结果输出成字节数组。
     * writeValueAsString(Object arg0)把arg0转成json序列，并把结果输出成字符串。
     */
    public static void main(String[] args) throws ParseException, IOException {
        User user = new User();
        init(user);
        ObjectMapper mapper = new ObjectMapper();

        //User类转JSON
        String json = mapper.writeValueAsString(user);
        System.out.println(json);
        //{"id":0,"email":"xiaomin@sina.com","mobile":null,"username":null,"role":null,"age":20,"name":"小民","birthday":844099200000}

        //Json字符串转化成指定Class类
        User user2 = mapper.readValue(json, User.class);
        System.out.println(user2);

        //List集合转化成json字符串
        List<User> users = new ArrayList<User>();
        users.add(user);
        users.add(user2);
        String jsonList = mapper.writeValueAsString(users);
        System.out.println(jsonList);

        //Json字符串转化成集合List
        JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, User.class);
        //如果是Map类型  mapper.getTypeFactory().constructParametricType(HashMap.class,String.class, Bean.class);
        List<User> beanList = mapper.readValue(jsonList, new TypeReference<List<User>>() {
        });
    }

    private static void init(User user) throws ParseException {
        user.setName("小民");
        user.setEmail("xiaomin@sina.com");
        user.setAge(20);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        user.setBirthday(df.parse("1996-10-01"));
    }

}
