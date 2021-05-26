package com.example.chessopenings;

import android.graphics.Bitmap;

public class ChessPiece {
    //private boolean alive = true;
    private Bitmap bmp = null;

    public ChessPiece(Bitmap bmp) 
    {
        this.setBmp(bmp);
    }

    public void setBmp(Bitmap bmp) {
        this.bmp = bmp;
    }

    public Bitmap getBmp() 
    {
        return this.bmp;
    }
}
