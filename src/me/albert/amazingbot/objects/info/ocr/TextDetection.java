package me.albert.amazingbot.objects.info.ocr;

import java.util.List;

public class TextDetection {

    protected String text;
    protected int confidence;
    protected List<Vector2> coordinates;


    public String getText() {
        return text;
    }

    public int getConfidence() {
        return confidence;
    }

    public List<Vector2> getCoordinates() {
        return coordinates;
    }
}
