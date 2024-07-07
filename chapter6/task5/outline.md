1. Draw a checkerboard
2. On click: "select" one of the squares (draw a colored border around it)
if the clicked square is selected: "unselect", otherwise "select".
3. To draw a border I will use utility method which takes x and y as
coordinates of the right left corner of clicked square.
4. To "unselect" a square I will use utility method which will take
x and y as coordinates of the right left corner of selected square and
boolean value isBlack to draw colour accordingly.

# Selection logic

For determine selected square we can use global variables:
```
int selectedColumn;
int selectedRow;
```

At the beginning there is no square selected, so we can assign `-1` to
`selectedRow` and `selectedColumn` to show it.

When user clicks on a canvas program should determine to which row and column
the clicked square relates. We can use: `int clickedColumn = x / 50;`, `int clickedRow = y / 50;`.

Then we can remove selection border from selected square in any circumstances:
```
if ( selectedColumn != -1 && selectedRow != -1 ) {
    drawSquare(selectedColumn, selectedRow);
}
```

Then we can draw selection border if the new square is selected:
```
if ( selectedColumn != clickedColumn || selectedRow != clickedRow ) {
    selectSquare(clickedColumn, clickedRow);
    selectedColumn = clickedColumn;
    selectedRow = clickedRow;
} else {
    selectedColumn = -1;
    selectedRow = -1;
}
```

# Drawing logic

My idea is that we do not need to draw the canvas every time, we just need
to draw initial setup (checkerboard with no squares selected) and then when
the user selecting a square just draw a border for this square. Whenever a
square is unselected we need to draw only that square.

Since we are operating with columns and squares we can use this method to
draw a square:
```
private void drawSquare(GraphicsContext g, int column, int row) {
    if ( ((column % 2) == 0) == ((row % 2) == 0) ) {
        g.setFill(Color.RED);
    } else {
        g.setFill(Color.BLACK);
    }
    g.fillRect(column * rectSide, row * rectSide, rectSide, rectSide);
}
```
Bonus: we can use the same method to draw initial board! Just use it in two
loops.

Drawing selection border is simple:
```
private void selectSquare(GraphicsContext g, int column, int row) {
    g.strokeRect(column * rectSide, row * rectSide, rectSide, rectSide);
}
```
