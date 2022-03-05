package me.albert.amazingbot.objects.info.ocr;

public class Vector2 {
    protected float x;
    protected float y;

    public Vector2() {
        this.x = 0.0f;
        this.y = 0.0f;
    }

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    // Compare two vectors
    public boolean equals(Vector2 other) {
        return (this.x == other.x && this.y == other.y);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}