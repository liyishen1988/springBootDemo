package com.lys.main.视频压缩;

import com.alibaba.fastjson.JSONObject;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.info.AudioInfo;
import ws.schild.jave.Encoder;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.EncodingAttributes;
import ws.schild.jave.encode.VideoAttributes;
import ws.schild.jave.info.VideoInfo;

import java.io.File;

/**
 * 视频压缩
 * <p>
 * 应用场景一般为：采集端的原始视频过大，需要压缩控制在几M以内[10M左右的视频能压缩到1M左右]
 */
public class CompressVideo {

    public static void main(String[] args) {
        File file = new File("C:\\Users\\Public\\Videos\\Sample Videos\\photo.mp4");
        compressionVideo(file);
    }

    public static void compressionVideo(File source) {
        long time = System.currentTimeMillis();

        try {
            // 根据视频大小来判断是否需要进行压缩,
            int maxSize = 7;
            double mb = Math.ceil(source.length() / 1048576);
            boolean temp = mb > maxSize;
            if (temp) {
                MultimediaObject object = new MultimediaObject(source);

                AudioAttributes audio = new AudioAttributes();
                AudioInfo audioInfo = object.getInfo().getAudio();
                audio.setCodec("aac");  // 设置通用编码格式
                audio.setBitRate(audioInfo.getBitRate() > 128000 ? 128000 : audioInfo.getBitRate());    // 设置音频比特率,单位:b (比特率越高，清晰度/音质越好，当然文件也就越大 128000 = 182kb)
                audio.setChannels(audioInfo.getChannels()); // 设置重新编码的音频流中使用的声道数（1 =单声道，2 = 双声道（立体声））。如果未设置任何声道值，则编码器将选择默认值 0。
                audio.setSamplingRate(audioInfo.getSamplingRate() > 48050 ? 48050 : audioInfo.getSamplingRate());   // 设置音频采样率，单位：赫兹 hz
                audio.setVolume(256);   // 设置编码时候的音量值，未设置为0,如果256，则音量值不会改变

                VideoAttributes video = new VideoAttributes();
                VideoInfo videoInfo = object.getInfo().getVideo();
                video.setCodec("h264"); //video.setCodec("mpeg4");
                video.setBitRate(videoInfo.getBitRate() > 800000 ? 800000 : videoInfo.getBitRate());  //设置音频比特率,单位:b (比特率越高，清晰度/音质越好，当然文件也就越大 800000 = 800kb)
                video.setFrameRate(videoInfo.getFrameRate() > 20 ? 20 : (int) videoInfo.getFrameRate());    // 设置视频帧率（帧率越低，视频会出现断层，越高让人感觉越连续），视频帧率（Frame rate）是用于测量显示帧数的量度。所谓的测量单位为每秒显示帧数(Frames per Second，简：FPS）或“赫兹”（Hz）。

                EncodingAttributes attr = new EncodingAttributes();
                attr.setOutputFormat("mp4");
                attr.setAudioAttributes(audio);
                attr.setVideoAttributes(video);

                // 开始压缩
                Encoder encoder = new Encoder();
                String targetPath = "C:\\Users\\Public\\Videos\\Sample Videos\\yasuo.mp4";
                encoder.encode(new MultimediaObject(source), new File(targetPath), attr);
                System.out.println("压缩总耗时：" + (System.currentTimeMillis() - time) / 1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

