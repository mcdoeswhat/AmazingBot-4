package me.albert.amazingbot.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class MsgUtil {

    public static String imageToBase64(BufferedImage image) {
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", os);
            return Base64.getEncoder().encodeToString(os.toByteArray());
        } catch (final IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }


    /**
     * @param file 绝对路径，例如 file:///C:\\Users\Richard\Pictures\1.png
     *             网络 URL，例如 http://i1.piimg.com/567571/fdd6e7b6d93f1ef0.jpg
     *             Base64 编码，例如 base64://iVBORw0KGgoAAAANSUhEUgAAABQAAAAVCAIA...
     * @return CQ码
     */
    public static String getImageMsg(String file) {
        return "[CQ:image,file=" + file + "]";
    }

    public static String getRecordMsg(String file) {
        return "[CQ:record,file=" + file + "]";
    }

    public static String getVideoMsg(String file) {
        return "[CQ:video,file=" + file + "]";
    }

    public static String getAtMsg(String qq) {
        return "[CQ:at,qq=" + qq + "]";
    }

    public static String getShareMsg(String url) {
        return "[CQ:share,url=" + url + "]";
    }

    /**
     * @param type qq 163 xm 分别表示使用 QQ 音乐、网易云音乐、虾米音乐
     * @param id   歌曲 ID
     */
    public static String getMusicMsg(String type, String id) {
        return "[CQ:music,type=" + type + ",id=" + id + "]";
    }

    /**
     * @param url     点击后跳转目标 URL
     * @param audio   音乐 URL
     * @param title   标题
     * @param content 内容描述
     * @param image   图片 URL
     */
    public static String getCustomMusicMsg(String url, String audio, String title, String content, String image) {
        return String.format("CQ:music,type=custom,url=%s,audio=%s,title=%s,content=%s,image=%s", url, audio, title, content, image);
    }


    /**
     * @param text 自定义回复的信息
     * @param id   回复时所引用的消息id, 必须为本群消息.
     * @param qq   自定义回复时的自定义QQ, 如果使用自定义信息必须指定.
     * @param time 自定义回复时的时间, 格式为Unix时间
     * @param seq  起始消息序号, 可通过 Bot.getApi.getMessage().getMessageSeq 获得
     */
    public static String getReplyMsg(String text, long id, long qq, long time, long seq) {
        return String.format("[CQ:reply,text=%s,id=%s,qq=%s,time=%s,seq=%s]", text, id, qq, time, seq);
    }


    public static String getPokeMsg(long userID) {
        return "[CQ:poke,qq=" + userID + "]";
    }

    /**
     * @param id 礼物的类型
     *           0	甜 Wink
     *           1	快乐肥宅水
     *           2	幸运手链
     *           3	卡布奇诺
     *           4	猫咪手表
     *           5	绒绒手套
     *           6	彩虹糖果
     *           7	坚强
     *           8	告白话筒
     *           9	牵你的手
     *           10	可爱猫咪
     *           11	神秘面具
     *           12	我超忙的
     *           13	爱心口罩
     */
    public static String getGiftMsg(long qq, int id) {
        return "[CQ:gift,qq=" + qq + ",id=" + id + "]";
    }

    public static String getXMLMsg(String xml) {
        return "[CQ:xml,data=" + xml + "]";
    }

    public static String formatCQCode(String str) {
        return str.replace("&", "&amp;")
                .replace("[", "&#91;")
                .replace("]", "&#93;")
                .replace(",", "&#44;");
    }

    public static String deFormatCQCode(String str) {
        return str.replace("&amp;", "&")
                .replace("&#91;", "[")
                .replace("&#93;", "]")
                .replace("&#44;", ",");
    }

    /**
     * @param json  json内容, json的所有字符串记得实体化处理
     *              json中的字符串需要进行转义 :
     *              ","=> &#44;
     *              "&"=> &amp;
     *              "["=> &#91;
     *              "]"=> &#93;
     *              否则无法正确得到解析,使用上方sormatString方法
     * @param resid 建议填0, 走小程序通道, 其他走富文本通道发送
     */
    public static String getJsonMsg(String json, int resid) {
        return "[CQ:json,data=" + json + ",resid=" + resid + "]";
    }

    /**
     * 一种xml的图片消息（装逼大图）
     *
     * @param file      和image的file字段对齐, 支持也是一样的
     * @param minwidth  默认不填为400, 最小width
     * @param minheight 默认不填为400, 最小height
     * @param maxwidth  默认不填为500, 最大width
     * @param maxheight 默认不填为1000, 最大height
     * @param source    分享来源的名称, 可以留空
     * @param icon      分享来源的icon图标url, 可以留空
     *                  xml 接口的消息都存在风控风险, 请自行兼容发送失败后的处理 ( 可以失败后走普通图片模式 )
     */
    public static String getCardImg(String file, int minwidth, int minheight, int maxwidth, int maxheight, String source, String icon) {
        return String.format(
                "[CQ:cardimage,file=%s,minwidth=%s,minheight=%s," +
                        "maxwidth=%s,maxheight=%s,source=%s,icon=%s", file, minwidth, minheight, maxwidth, maxheight, source, icon);
    }

    /**
     * 文本转语音
     * 范围: 仅群聊
     * 通过TX的TTS接口, 采用的音源与登录账号的性别有关
     *
     * @param msg 内容
     */
    public static String getTTSMsg(String msg) {
        return "[CQ:tts,text=" + msg + "]";
    }


    public static String bufferedImgToMsg(BufferedImage image) {
        return getImageMsg("base64://" + imageToBase64(image));
    }

    public static String getFileAsBase64(String filePath) {
        if (filePath == null) {
            return null;
        }
        try {
            byte[] b = Files.readAllBytes(Paths.get(filePath));
            return Base64.getEncoder().encodeToString(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
