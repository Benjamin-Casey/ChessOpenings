package com.example.chessopenings;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class ChessBoard extends View {

    Paint pain = new Paint();
    Resources res = getContext().getResources();

    Bitmap brook = BitmapFactory.decodeResource(res, R.drawable.brook);
    Bitmap bknight = BitmapFactory.decodeResource(res, R.drawable.bknight);
    Bitmap bbishop = BitmapFactory.decodeResource(res, R.drawable.bbishop);
    Bitmap bqueen = BitmapFactory.decodeResource(res, R.drawable.bqueen);
    Bitmap bking = BitmapFactory.decodeResource(res, R.drawable.bking);
    Bitmap bpawn = BitmapFactory.decodeResource(res, R.drawable.bpawn);
    Bitmap wrook = BitmapFactory.decodeResource(res, R.drawable.wrook);
    Bitmap wknight = BitmapFactory.decodeResource(res, R.drawable.wknight);
    Bitmap wbishop = BitmapFactory.decodeResource(res, R.drawable.wbishop);
    Bitmap wking = BitmapFactory.decodeResource(res, R.drawable.wking);
    Bitmap wqueen = BitmapFactory.decodeResource(res, R.drawable.wqueen);
    Bitmap wpawn = BitmapFactory.decodeResource(res, R.drawable.wpawn);

    private final Rect rect;
    private static final int COLS = 8;
    private static final int ROWS = 8;

//    Tile tile;
//    private final Tile[][] mTiles;
//    private static final int DEF_SQUARE_SIZE = 50;
    private int squareSize = 0;
    private final boolean flipped = false;

    ChessTile tile;
    private ChessTile[][] tiles;

    public ChessBoard(final Context context, AttributeSet attrs) {
        super(context, attrs);
        this.tiles = new ChessTile[COLS][ROWS];
        // TODO: implement this - note: maybe not.
        // buildTiles();
        rect = new Rect();
    }

    // Draw methods
    private int getXCoord(final int x) {
        int x0 = 0;
        return x0 + squareSize * (flipped ? 7 - x : x);
    }

    private int getYCoord(final int y) {
        int y0 = 0;
        return y0 + squareSize * (flipped ? y : 7 - y);
    }

    private void resetBoard(Canvas canvas) {
        // Black pieces
        canvas.drawBitmap(brook, null, tiles[0][7].getSquare(), pain);
        canvas.drawBitmap(bknight, null, tiles[1][7].getSquare(), pain);
        canvas.drawBitmap(bbishop, null, tiles[2][7].getSquare(), pain);
        canvas.drawBitmap(bqueen, null, tiles[3][7].getSquare(), pain);
        canvas.drawBitmap(bking, null, tiles[4][7].getSquare(), pain);
        canvas.drawBitmap(bbishop, null, tiles[5][7].getSquare(), pain);
        canvas.drawBitmap(bknight, null, tiles[6][7].getSquare(), pain);
        canvas.drawBitmap(brook, null, tiles[7][7].getSquare(), pain);
        for(int i = 0; i < 8; i++)
        {
            canvas.drawBitmap(bpawn, null, tiles[i][6].getSquare(), pain);
        }
        // White pieces
        canvas.drawBitmap(wrook, null, tiles[0][0].getSquare(), pain);
        canvas.drawBitmap(wknight, null, tiles[1][0].getSquare(), pain);
        canvas.drawBitmap(wbishop, null, tiles[2][0].getSquare(), pain);
        canvas.drawBitmap(wqueen, null, tiles[3][0].getSquare(), pain);
        canvas.drawBitmap(wking, null, tiles[4][0].getSquare(), pain);
        canvas.drawBitmap(wbishop, null, tiles[5][0].getSquare(), pain);
        canvas.drawBitmap(wknight, null, tiles[6][0].getSquare(), pain);
        canvas.drawBitmap(wrook, null, tiles[7][0].getSquare(), pain);
        for(int i = 0; i < 8; i++)
        {
            canvas.drawBitmap(wpawn, null, tiles[i][1].getSquare(), pain);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();

        squareSize = width / 8;

        Paint paintWhite = new Paint();
        Paint paintBlack = new Paint();
        paintWhite.setColor(Color.LTGRAY);
        paintBlack.setColor(Color.DKGRAY);

        for (int c = 0; c < COLS; c++) {
            for (int r = 0; r < ROWS; r++) {

                final int xCoord = getXCoord(c);
                final int yCoord = getYCoord(r);
                rect.left=xCoord;
                rect.top=yCoord;
                rect.right=rect.left+squareSize;
                rect.bottom=rect.top+squareSize;

                if ((c + r) % 2 == 0) {
                    canvas.drawRect(rect.left, rect.top, rect.right, rect.bottom, paintBlack);
                } else {
                    canvas.drawRect(rect.left, rect.top, rect.right, rect.bottom, paintWhite);
                }
                tiles[c][r] = new ChessTile(c, r, new Rect(rect.left, rect.top, rect.right, rect.bottom), null);
            }
        }

        resetBoard(canvas);

    }
}
