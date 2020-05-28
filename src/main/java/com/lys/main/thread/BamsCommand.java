package com.lys.main.thread;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 *
 */
public class BamsCommand {

    public static void main(String[] args) {
        File rootfile = new File("c:\\Users\\Administrator\\Pictures\\pic");
        Map<String, Object> map = new HashMap<>();
        CloudWalkRequest request = new CloudWalkRequest();
        request.setGetFace(1);
        List<File> sonFiles = Arrays.asList(rootfile.listFiles());
        List<File> totals = new ArrayList<>();

        //遍历读取所有子目录
        for (File sonFile : sonFiles) {
            if (sonFile.getName().lastIndexOf(".") == -1) {
                //每个子目录下的文件
                List<File> sonFileList = Arrays.asList(sonFile.listFiles());
                totals.addAll(sonFileList);
            }
        }

        //数据分批
//        List<List<File>> batchList = splitListToList(totals, 50);
        //分批处理
//        for (List<File> list : batchList) {
            //初始化线程，在加入线程异步处理
//            AsyncThreadPool.getInstance().execute(new Runnable() {
//                @Override
//                public void run() {
                    for (File file : totals) {
                        //如果是jpg图片
                        if (file.getName().lastIndexOf(".jpg") != -1) {
                            //发送识别请求，获取结果
                            request.setImg(base64(file.getPath()));
                            try {
                                String result = sendRequest(request);
                                JSONObject jsonObject = JSONObject.parseObject(result);
                                //如果引擎调用成功，code值放入map中
                                if (jsonObject.get("result").toString().equals("0")) {
                                    String code = jsonObject.get("code").toString();
                                    map.put(file.getName(), code);

                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
//                    }
//                }
//            });
        }
    }

    //直接发送http引擎请求
    private static String sendRequest(CloudWalkRequest request) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://10.0.155.135:7000/ocr");
        CloseableHttpResponse response = null;
        String postString = JSONObject.toJSONString(request);
        StringEntity stringEntity = new StringEntity(postString, "utf-8");
        stringEntity.setContentEncoding("UTF-8");
        stringEntity.setContentType("application/json");
        httpPost.setEntity(stringEntity);
        httpPost.setHeader("Connection", "close");
        try {
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, "UT-8");
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            response.close();
        }
        return null;
    }

    private static String base64(String path) {
        InputStream inputStream = null;
        byte[] buffer = null;
        try {
            inputStream = new FileInputStream(path);
            byte[] img = IOUtils.toByteArray(inputStream);
            return Base64.encodeBase64String(img);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    //数据分批
    private static List<List<File>> splitListToList(List<File> list, int splitSize) {
        List<List<File>> batchList = new ArrayList<>();

        int listSize = list.size();
        int batchListSize = listSize / splitSize;
        if (listSize % splitSize > 0) {
            batchListSize += 1;
        }
        for (int i = 0; i < batchListSize; i++) {
            int start = i * splitSize;
            int end = (i + 1) * splitSize;
            if (end > listSize) {
                end = listSize;
            }
            List<File> batchs = list.subList(start, end);
            batchList.add(batchs);
        }
        return batchList;
    }


}
