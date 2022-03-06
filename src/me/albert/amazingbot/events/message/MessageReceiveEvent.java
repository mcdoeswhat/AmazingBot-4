package me.albert.amazingbot.events.message;


import me.albert.amazingbot.bot.Bot;
import me.albert.amazingbot.events.ABEvent;
import me.albert.amazingbot.objects.contact.Sender;
import me.albert.amazingbot.objects.info.ocr.ImageOCR;
import me.albert.amazingbot.objects.info.ocr.TextDetection;
import me.albert.amazingbot.objects.message.Image;
import me.albert.amazingbot.objects.message.Message;
import me.albert.amazingbot.utils.MsgUtil;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageReceiveEvent extends ABEvent {

    protected String message_type;

    protected String sub_type;

    protected Long message_id;

    protected Long user_id;

    protected String message;

    protected String raw_message;

    protected int font;

    protected Sender sender;

    public String getMsg() {
        return message;
    }


    /**
     * @return 消息中包含的所有纯文本信息
     */
    public String getTextMessage() {
        return MsgUtil.deFormatCQCode(message.replaceAll("(\\[)([\\s\\S]*?)(])", "\n"));
    }

    public List<String> getImageIDList() {
        List<String> imageIds = new ArrayList<>();
        Pattern pattern = Pattern.compile("(?<=\\[CQ:image,file=)([\\s\\S]*?)(?=,)");
        Matcher matcher = pattern.matcher(message);
        while (matcher.find()) {
            imageIds.add(matcher.group());
        }
        return imageIds;
    }

    public String getImgOCR() {
        StringBuilder result = new StringBuilder();
        for (String imageID : getImageIDList()) {
            ImageOCR imageOCR = Bot.getApi().getImageOCR(imageID);
            if (imageOCR == null) {
                continue;
            }
            List<TextDetection> textDetections = imageOCR.getTexts();
            for (TextDetection textDetection : textDetections) {
                result.append(textDetection.getText()).append("\n");
            }
        }
        return result.toString().trim();
    }

    public long response(String message, boolean... auto_escape) {
        return 0;
    }

    public long response(Message message) {
        return response(message.getMessage());
    }

    public long response(Image image) {
        return response("[CQ:image,file=" + image.getUrl() + "]");
    }

    public long response(BufferedImage bufferedImage) {
        return response(MsgUtil.bufferedImgToMsg(bufferedImage));
    }


    public void recall() {
        Bot.getApi().recallMessage(this.message_id);
    }


    public Sender getSender() {
        return sender;
    }

    public int getFont() {
        return font;
    }

    public Long getMessageID() {
        return message_id;
    }

    public String getSubType() {
        return sub_type;
    }

    public String getMessageType() {
        return message_type;
    }

    public Long getUserID() {
        return user_id;
    }

    public String getRawMessage() {
        return raw_message;
    }

    public long response(String message, boolean auto_escape) {
        return 0;
    }
}
