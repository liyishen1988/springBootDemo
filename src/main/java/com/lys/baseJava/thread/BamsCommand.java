package com.lys.baseJava.thread;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.*;
import java.util.*;

/**
 *
 */
public class BamsCommand {

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();

        //云从引擎请求
        CloudWalkRequest request = new CloudWalkRequest();
        request.setGetFace(0);  //不返回头像的base64编码

        //获取指定目录下的所有以.jpg或.png结尾的文件，包含子目录下的文件
        File rootFile = new File("c:\\Users\\Administrator\\Pictures\\pic");
        Collection<File> allFiles = FileUtils.listFiles(rootFile, new String[]{"jpg", "png"}, true);
        List<File> totalImages = new ArrayList<>(allFiles);

        //数据分批
        List<List<File>> batchList = splitListToList(totalImages, totalImages.size() / 2);
        //分批处理
        for (List<File> list : batchList) {
//            初始化线程，在加入线程异步处理
            AsyncThreadPool.getInstance().execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (File file : list) {
                            //发送识别请求，获取结果
                            request.setImg(base64(file.getPath()));
                            String result = sendRequest(request);

                            //如果引擎调用成功，code值放入map中
                            JSONObject jsonObject = JSONObject.parseObject(result);
                            if (jsonObject.getString("result").equals("0")) {
                                String code = jsonObject.getString("code");
                                map.put(file.getName(), code);
                            }
                        }
                        //写入文件
                        writeFile(map, rootFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    //写入文件
    private static void writeFile(Map<String, Object> map, File filePath) throws IOException {
        String ocrFile = filePath + File.separator + "ocr.txt";
        File file = new File(ocrFile);
        if (!file.exists()) {
            file.createNewFile();
        }

        String line = System.getProperty("line.separator");
        StringBuffer buffer = new StringBuffer();
        FileWriter fw = new FileWriter(ocrFile, false);
        Set set = map.entrySet();
        Iterator iter = set.iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            buffer.append(entry.getKey() + " : " + entry.getKey()).append(line);
        }
        fw.write(buffer.toString());
        fw.close();
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

    //图片转成base64编码
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
