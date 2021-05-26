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
import android.view.MotionEvent;
import android.view.View;

public class ChessBoard extends View {



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

    private final Paint pain = new Paint();
    private final Rect rect;
    private static final int COLS = 8;
    private static final int ROWS = 8;
    private int squareSize = 0;
    private final boolean flipped = false;
    private final ChessTile[][] tiles;
    private final Rect[][] moveTable;
    private int move = 0;
    private final String[][][] moves;

    public ChessBoard(final Context context, AttributeSet attrs) {
        super(context, attrs);
        this.tiles = new ChessTile[COLS][ROWS];
        this.moveTable = new Rect[2][4];
        this.moves = new String[11][2][4];
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

    // Set all pieces to their starting location in tiles array
    private void resetBoard()
    {
        // Black pieces
        tiles[0][7].setPiece(new ChessPiece(brook));
        tiles[1][7].setPiece(new ChessPiece(bknight));
        tiles[2][7].setPiece(new ChessPiece(bbishop));
        tiles[3][7].setPiece(new ChessPiece(bqueen));
        tiles[4][7].setPiece(new ChessPiece(bking));
        tiles[5][7].setPiece(new ChessPiece(bbishop));
        tiles[6][7].setPiece(new ChessPiece(bknight));
        tiles[7][7].setPiece(new ChessPiece(brook));
        // Pawns
        for(int i = 0; i < 8; i++) 
        {
            tiles[i][6].setPiece(new ChessPiece(bpawn));
        }

        // White pieces
        tiles[0][0].setPiece(new ChessPiece(wrook));
        tiles[1][0].setPiece(new ChessPiece(wknight));
        tiles[2][0].setPiece(new ChessPiece(wbishop));
        tiles[3][0].setPiece(new ChessPiece(wqueen));
        tiles[4][0].setPiece(new ChessPiece(wking));
        tiles[5][0].setPiece(new ChessPiece(wbishop));
        tiles[6][0].setPiece(new ChessPiece(wknight));
        tiles[7][0].setPiece(new ChessPiece(wrook));
        // Pawns
        for(int i = 0; i < 8; i++) 
        {
            tiles[i][1].setPiece(new ChessPiece(wpawn));
        }
    }

    // Draws pieces to the canvas
    private void drawPieces(Canvas canvas)
    {
        // For each tile, draw the piece that is on that tile if it has one.
        for(int c = 0; c < COLS; c++){
            for(int r = 0; r < ROWS; r++) {
                if(tiles[c][r].getChessPiece() != null) {
                    canvas.drawBitmap(tiles[c][r].getChessPiece().getBmp(), null, tiles[c][r].getSquare(), null);
                }
            }
        }
    }

    // Draw table
    private void initTableBounds(Canvas canvas)
    {
        // Draw the table - hollow rectangle with a black outline
        pain.setStyle(Paint.Style.STROKE);
        pain.setColor(Color.BLACK);
        pain.setStrokeWidth(3);

        /*
        Top left: new Rect(30, 1100, 525, 1275)
        Top Right: new Rect(525, 1100, 1050, 1275)
        30 - 510 | 510 - 1050
        1100 - 1275 | 1275 - 1450 | 1450 - 1625 | 1625 - 1800
         */
        // Table bounds
        int left = 30;
        int top = 1100;
        int right = 1050;
        int bottom = 1800;
        // Vertical and horizontal gaps
        int verGap = (right-left)/2;
        int horGap = (bottom-top)/4;

        // Set table cells to array and draw table
        for(int c = 0; c < 2; c++) {
            for(int r = 0; r < 4; r++) {
                rect.left = left+(verGap*c);
                rect.top = top+(horGap*r);
                rect.right = left+(verGap*(c+1));
                rect.bottom = top+(horGap*(r+1));

                // Init array
                moveTable[c][r] = new Rect(rect.left, rect.top, rect.right, rect.bottom);
                // Draw table
                canvas.drawRect(moveTable[c][r], pain);
            }
        }
    }

    // Populate table information
    // This is all dummy data/information
    private void updateTable(Canvas canvas)
    {
        // Draw e4 etc for the first move.
        // Move 0, col 0, row 0.
        //TODO: move this
        moves[0][0][0] = "e4";
        moves[0][1][0] = "40%";
        moves[0][0][1] = "d4";
        moves[0][1][1] = "30%";
        moves[0][0][2] = "Nf3";
        moves[0][1][2] = "20%";
        moves[0][0][3] = "c4";
        moves[0][1][3] = "10%";

        moves[1][0][0] = "c5";
        moves[1][1][0] = "45%";
        moves[1][0][1] = "e5";
        moves[1][1][1] = "25%";
        moves[1][0][2] = "e6";
        moves[1][1][2] = "15%";
        moves[1][0][3] = "c6";
        moves[1][1][3] = "10%";

        moves[2][0][0] = "Nf3";
        moves[2][1][0] = "60%";
        moves[2][0][1] = "Nc3";
        moves[2][1][1] = "15%";
        moves[2][0][2] = "Bc4";
        moves[2][1][2] = "15%";
        moves[2][0][3] = "f4";
        moves[2][1][3] = "5%";

        moves[3][0][0] = "Nc6";
        moves[3][1][0] = "70%";
        moves[3][0][1] = "Nf6";
        moves[3][1][1] = "10%";
        moves[3][0][2] = "d6";
        moves[3][1][2] = "5%";
        moves[3][0][3] = "f5";
        moves[3][1][3] = "5%";

        moves[4][0][0] = "Bb5";
        moves[4][1][0] = "60%";
        moves[4][0][1] = "Bc4";
        moves[4][1][1] = "15%";
        moves[4][0][2] = "d4";
        moves[4][1][2] = "15%";
        moves[4][0][3] = "Nc3";
        moves[4][1][3] = "5%";

        moves[5][0][0] = "Bc5";
        moves[5][1][0] = "40%";
        moves[5][0][1] = "Nf6";
        moves[5][1][1] = "30%";
        moves[5][0][2] = "Be7";
        moves[5][1][2] = "10%";
        moves[5][0][3] = "d6";
        moves[5][1][3] = "5%";

        moves[6][0][0] = "d3";
        moves[6][1][0] = "60%";
        moves[6][0][1] = "Ng5";
        moves[6][1][1] = "20%";
        moves[6][0][2] = "d4";
        moves[6][1][2] = "10%";
        moves[6][0][3] = "Nc3";
        moves[6][1][3] = "5%";

        moves[7][0][0] = "Bc5";
        moves[7][1][0] = "40%";
        moves[7][0][1] = "Be7";
        moves[7][1][1] = "35%";
        moves[7][0][2] = "h6";
        moves[7][1][2] = "10%";
        moves[7][0][3] = "d5";
        moves[7][1][3] = "5%";

        moves[8][0][0] = "c3";
        moves[8][1][0] = "60%";
        moves[8][0][1] = "O-O";
        moves[8][1][1] = "25%";
        moves[8][0][2] = "Nc3";
        moves[8][1][2] = "10%";
        moves[8][0][3] = "Bb3";
        moves[8][1][3] = "5%";

        moves[9][0][0] = "d6";
        moves[9][1][0] = "40%";
        moves[9][0][1] = "a6";
        moves[9][1][1] = "35%";
        moves[9][0][2] = "O-O";
        moves[9][1][2] = "15%";
        moves[9][0][3] = "Bb6";
        moves[9][1][3] = "10%";

        moves[10][0][0] = "Bb3";
        moves[10][1][0] = "50%";
        moves[10][0][1] = "O-O";
        moves[10][1][1] = "25%";
        moves[10][0][2] = "Nbd2";
        moves[10][1][2] = "10%";
        moves[10][0][3] = "a4";
        moves[10][1][3] = "10%";

        for(int c = 0; c < 2; c++) {
            for(int r = 0; r < 4; r++) {
                canvas.drawText(moves[move][c][r], moveTable[c][r].left+100, moveTable[c][r].top+100, pain);
            }
        }

        // Make pieces move
        // Check what move we're on. Based on this information, move the piece (Fixed moves)
        switch (move) {
            case 1:
                // Moving e2 to e4
                tiles[4][1].movePiece(tiles[4][3]);
                break;
            case 2:
                tiles[4][6].movePiece(tiles[4][4]);
                break;
            case 3:
                tiles[6][0].movePiece(tiles[5][2]);
                break;
            case 4:
                tiles[1][7].movePiece((tiles[2][5]));
                break;
            case 5:
                tiles[5][0].movePiece((tiles[2][3]));
                break;
            case 6:
                tiles[6][7].movePiece((tiles[5][5]));
                break;
            case 7:
                tiles[3][1].movePiece((tiles[3][2]));
                break;
            case 8:
                tiles[5][7].movePiece((tiles[2][4]));
                break;
            case 9:
                tiles[2][1].movePiece((tiles[2][2]));
                break;
            case 10:
                tiles[0][6].movePiece((tiles[0][5]));
                break;
        }
    }

    private void initSquares() {
        int width = getWidth();
        squareSize = width / 8;

        //Draw squares
        for (int c = 0; c < COLS; c++) {
            for (int r = 0; r < ROWS; r++) {

                final int xCoord = getXCoord(c);
                final int yCoord = getYCoord(r);
                rect.left=xCoord;
                rect.top=yCoord;
                rect.right=rect.left+squareSize;
                rect.bottom=rect.top+squareSize;

                tiles[c][r] = new ChessTile(c, r, new Rect(rect.left, rect.top, rect.right, rect.bottom), null);
            }
        }
    }

    private void drawSquares(Canvas canvas) {
        Paint paintWhite = new Paint();
        Paint paintBlack = new Paint();
        paintWhite.setColor(Color.LTGRAY);
        paintBlack.setColor(Color.DKGRAY);

        for (int c = 0; c < COLS; c++) {
            for (int r = 0; r < ROWS; r++) {
                if ((c+r) % 2 == 0) {
                    canvas.drawRect(tiles[c][r].getSquare(), paintBlack);
                } else {
                    canvas.drawRect(tiles[c][r].getSquare(), paintWhite);
                }
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Make sure squares are initialised and board is reset once at the beginning of the app
        if (move == 0) {
            initSquares();
            resetBoard();
        }

        // Draw necessary information at each loop
        drawSquares(canvas);
        drawPieces(canvas);
        initTableBounds(canvas);
        pain.setTextSize(50);
        pain.setStyle(Paint.Style.FILL);
        updateTable(canvas);

        // Loop
        this.invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Whenever the user clicks on the screen, increment moves.

        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:

                move = move + 1;

                if (move > 10) {
                    move = 0;
                    resetBoard();
                }
        }
        return super.onTouchEvent(event);
    }
}

