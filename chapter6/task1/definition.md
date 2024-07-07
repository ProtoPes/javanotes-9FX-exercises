In Subsection 6.3.2, the following code was given as an example. It installs a MousePressed
event handler on a canvas. The handler lets the user draw a red rectangle at the point
where the user clicks the mouse, or, by holding the shift key down, a blue oval:

```
    canvas.setOnMousePressed( evt -> {
        GraphicsContext g = canvas.getGraphicsContext2D();
        if ( evt.isShiftDown() ) {
            g.setFill( Color.BLUE );
            g.fillOval( evt.getX() - 30, evt.getY() - 15, 60, 30 )
        }
        else {
            g.setFill( Color.RED );
            g.fillRect( evt.getX() - 30, evt.getY() - 15, 60, 30 );
        }
    } );
```

Write a complete program that does the same, but in addition, the program will continue
to draw figures if the user drags the mouse. That is, the mouse will leave a trail of figures
as the user drags. However, if the user right-clicks the canvas, then the canvas should
simply be cleared and no figures should be drawn even if the user drags the mouse after
right-clicking. See the discussion of dragging in Subsection 6.3.3.

Note that a black border has been added around each shape to make them more distinct.
To make the problem a little more challenging, when drawing shapes during a drag
operation, make sure that the shapes that are drawn are at least, say, 5 pixels apart.
To implement this, you have to keep track of the position where the previous shape was
drawn.
