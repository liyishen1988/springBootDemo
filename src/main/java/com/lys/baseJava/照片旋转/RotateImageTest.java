package com.lys.baseJava.照片旋转;

import com.lys.baseJava.utils.FileUtils;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 图片旋转测试
 */
public class RotateImageTest {

    public static void main(String[] args) {
        String sourceImgBase64 = FileUtils.fileToBase64("D:\\Documents\\Pictures\\一寸蓝底证件照.jpg");
        BufferedImage sourceBufferedImage = FileUtils.base64ToBufferedImage(sourceImgBase64);

        try {
            BufferedImage targetBufferRotateImage = RotateImage2.rotateImage(sourceBufferedImage, -90);
            String targetImageBase64 = FileUtils.bufferedImageToBase64(targetBufferRotateImage);
            String targetPath = "D:\\Documents\\Pictures";
            String targetName = "22-rotate.jpg";
            FileUtils.base64ToFile(targetPath, targetImageBase64, targetName);

        } catch (IOException e) {
            System.out.println("旋转图片出错了");
            e.printStackTrace();
        }

    }


}
