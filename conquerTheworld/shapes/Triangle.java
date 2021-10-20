package shapes;


import java.awt.*;


/**
 * A triangle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0  (15 July 2000)
 */

public class Triangle extends shape{
    
    public static int VERTICES=3;

    /**
     * Create a new triangle at default position with default color.
     */
    public Triangle(int xPosition, int yPosition){
        super();
        height = 25;
        width = 25;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    /*
     * Draw the triangle with current specifications on screen.
     */
    @Override
    public void draw(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            int[] xpoints = { xPosition, xPosition + (width/2), xPosition - (width/2) };
            int[] ypoints = { yPosition, yPosition + height, yPosition + height };
            canvas.draw(this, color, new Polygon(xpoints, ypoints, 3));
            canvas.wait(10);
        }
    }

    /*
     * Erase the triangle on screen.
     */
    @Override
    public void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
}
