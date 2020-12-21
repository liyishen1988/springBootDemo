package com.lys.baseJava.照片旋转;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 旋转图片
 */
public class RotateImage2 {

     static BufferedImage rotateImage(BufferedImage image, int degree) throws IOException {

        BufferedImage rotatedImage = null;
        try {
            // 元イメージ広い
            int originalImWidth = image.getWidth();
            // 元イメージ高い
            int originalImHeight = image.getHeight();
            // 回転広い
            int rotateImWidth = 0;
            // 回転高い
            int rotateImHeight = 0;
            // ドットX
            int x = 0;
            // ドットY
            int y = 0;
            // 角度を計算する
            degree = degree % 360;
            if (degree < 0) {
                degree = 360 + degree;
            }

            // 角度を弧度に変換する
            double radian = Math.toRadians(degree);


// 角度が 0 または 180 または360
            if (degree == 180 || degree == 0 || degree == 360) {
                rotateImWidth = originalImWidth;
                rotateImHeight = originalImHeight;


// 角度が 90 または 270
            } else if (degree == 90 || degree == 270) {
                rotateImWidth = originalImHeight;
                rotateImHeight = originalImWidth;


// 上記以外
            } else {
                double cosVal = Math.abs(Math.cos(radian));
                double sinVal = Math.abs(Math.sin(radian));
                rotateImWidth = (int) (sinVal * originalImHeight) + (int) (cosVal * originalImWidth);
                rotateImHeight = (int) (sinVal * originalImWidth) + (int) (cosVal * originalImHeight);
            }


// ドット座標を計算する
            x = (rotateImWidth / 2) - (originalImWidth / 2);
            y = (rotateImHeight / 2) - (originalImHeight / 2);


// 回転イメージを作成する
            rotatedImage = new BufferedImage(rotateImWidth, rotateImHeight, image.getType());
            Graphics2D gs = (Graphics2D) rotatedImage.getGraphics();
            gs.fillRect(0, 0, rotateImWidth, rotateImHeight);


// イメージを回転する
            AffineTransform at = new AffineTransform();
            at.rotate(radian, rotateImWidth / 2, rotateImHeight / 2);
            at.translate(x, y);
            AffineTransformOp op = new AffineTransformOp(at,
                    AffineTransformOp.TYPE_BICUBIC);
            op.filter(image, rotatedImage);
        } catch (Exception e) {
            throw e;
        }
        return rotatedImage;
    }


}
