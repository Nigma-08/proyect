package shapes;

import java.awt.*;


/**
 * A rectangle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes (Modified)
 * @version 1.0  (15 July 2000)()
 */


 
public class Rectangle extends shape{

    public static int EDGES = 4;
    
    /**
     * Create a new rectangle at default position with default color.
     */
    public Rectangle(){
        super();
        height = 30;
        width = 40;
    }
    
    /*
     * Draw the rectangle with current specifications on screen.
     */
    @Override
    public void draw() {
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color,
                new java.awt.Rectangle(xPosition, yPosition, 
                                       width, height));
            canvas.wait(10); 
        }
    }

    /*
     * Erase the rectangle on screen.
     */
    @Override
    public void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
}

