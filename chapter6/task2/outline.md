- variables:
    - selectedFigure: int, 0 - no figure, 1 or 2 figure selected
    - redX, redY, blueX, blueY: current coordinates of figures
    - figureHeight, figureWidth: size of figures
    - prevX, prevY: previous coordinates of the mouse
- Draw initial setup: White background, two figures of different color
- Add event listeners:
    - Mouse pressed: if clicked on figure: change selectedFigure var.
    - Mouse dragged: if selectedFigure not 0 drag figure with cursor.
    - Mouse released: if selectedFigure not 0 set it to 0.
    - Escape key pressed: redraw initial setup.

So we can create drawScreen(double redX, double redY, double blueX, double blueY)
that will be called in start(...), mouseDragged(...), keyPressed(...)

