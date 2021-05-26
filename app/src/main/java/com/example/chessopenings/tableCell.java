package com.example.chessopenings;

import android.graphics.Rect;

public class tableCell {
    private Rect outerBounds;
    private String text;

    public tableCell(Rect outerBounds, String text) {
        this.outerBounds = outerBounds;
        this.text = text;
    }

    public Rect getOuterBounds() { return this.outerBounds; }

    public void setOuterBounds(Rect rect) { this.outerBounds = rect; }

    public String getText() { return this.text; }

    public void setText(String str) { this.text = str; }
}
