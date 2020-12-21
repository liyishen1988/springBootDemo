package com.lys.baseJava.utils;

import org.apache.commons.io.IOUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;

public class FileUtils {

    /**
     * base64字符串转文件
     *
     * @param targetFilePath
     * @param base64
     * @param fileName
     * @return
     */
    public static void base64ToFile(String targetFilePath, String base64, String fileName) {

        //创建文件目录
//        File dir = new File(targetFilePath);
//        if (!dir.exists() && !dir.isDirectory()) {
//            dir.mkdirs();
//        }

        File file = null;
        BufferedOutputStream bos = null;
        java.io.FileOutputStream fos = null;
        try {
            byte[] bytes = Base64.getDecoder().decode(base64);
            file = new File("D:\\Documents\\Pictures\\22-rotate.jpg");
//            file = new File(targetFilePath + "\\" + fileName);
            fos = new java.io.FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 文件转base64字符串
     *
     * @param path
     * @return
     */
    public static String fileToBase64(String path) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(path);
            byte[] bytes = IOUtils.toByteArray(inputStream);
            return Base64.getEncoder().encodeToString(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * base64 编码转换为 BufferedImage
     *
     * @param base64
     * @return
     */
    public static BufferedImage base64ToBufferedImage(String base64) {
//        BASE64Decoder decoder = new sun.misc.BASE64Decoder();
        try {
            byte[] bytes = Base64.getDecoder().decode(base64);

//            byte[] bytes = decoder.decodeBuffer(base64);
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            return ImageIO.read(bais);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * BufferedImage 编码转换为 base64
     *
     * @param bufferedImage
     * @return
     */
    public static String bufferedImageToBase64(BufferedImage bufferedImage) {
        //io流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            //写入流中
            ImageIO.write(bufferedImage, "jpg", baos);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //转换成字节
        byte[] bytes = baos.toByteArray();
        return Base64.getEncoder().encodeToString(bytes);
    }


}
