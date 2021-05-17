Instead of rendering bmps, loop through tiles\[c]\[r] and draw the images that are attached to them.
Attach a piece to each tile.

Resetting the board means setting everything to its original squares, then drawing it.

Consider creating a method in ChessTile that takes another tile and changes the piece on the current tile to the given one.

Need to set chesspiece to null if it moves off of the current tile.


Steps:
- Draw squares
    - On create/startup, loop through rows and cols to draw the squares.
- Draw pieces to the squares
    - On create/startup, set all tiles to have the pieces on their starting locations.
    - Don't necessarily need to DRAW the pieces until in the main loop.

Main loop:
- Draw pieces on their tiles
    - Nested for loop, draw the pieces on each tile
- Wait for a move
    - Generate a table of moves. Move piece according to the clicked move.
- Move piece.
    - Move method on chessTile. Take another tile, set that tiles chesspiece to be the chesspiece on the current tile, then set the chesspiece on the current tile to Null.
        - Note: promotions might upset this since the piece isn't moving anywhere, it's just changing bmp.